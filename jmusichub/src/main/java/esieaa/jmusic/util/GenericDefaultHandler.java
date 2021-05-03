package esieaa.jmusic.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p></p>This class is the base {@link org.xml.sax.helpers.DefaultHandler}</p>
 * @param <T> Generic targeted return type of unmarshalling xml to pojo
 * @author D.Djebarri
 */
public abstract class GenericDefaultHandler<T> extends DefaultHandler {
  private T targetedObject;

  private Option current;

  private boolean fromEnd = false;

  // internal properties

  // handles nested collections with this attribute
  private List<HierarchicalCtx> context = new ArrayList<>();

  private static final String DATE_FORMAT = "dd/MM/yyyy";

  public GenericDefaultHandler() {
    this.targetedObject = this.instantiate();
  }
  // protected methods

  /**
   * this method instantiate the return type
   * @return {T} generic type of {@code this} class
   */
  protected abstract T instantiate();

  /**
   *<p>this method give the fields that are sub class of {@link java.util.Collection} class</p>
   * @return an array of {@link String} which contains name of iterable fields
   */
  protected abstract String[] collectionFields();

  protected abstract Object map(String qName);

  /**
   * <p>this method gives the implementation of {@link List} class to use<br/>.
   * by default it uses {@link ArrayList} but it can be redefined in subclasses
   * </p>
   * @return {@link Collection} instance
   */
  protected List<Object> listImplementation() {
    return new ArrayList<>();
  }

  public T content() {
    return this.targetedObject;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    current = buildOpt(qName);
    if (current.iterable) {
      push(null, current);
    }
    else {
      Object obj = map(current.actualQName);
      if (obj != null) {
        current.primitive = false;
        push(obj, current);
      }
      else {
        current.primitive = true;
      }
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    String value = new String(ch, start, length);
    if (!current.iterable && current.primitive && !fromEnd) {
      try {
        HierarchicalCtx ctx = latestHierarchicalCtx();
        if (ctx != null) {
          set(ctx.field, value, current.actualQName);
        }
        else {
          set(targetedObject, value, current.actualQName);
        }
        current.primitive = true;
      } catch (Exception e) {
        System.out.println("haha !");
        e.printStackTrace();
        // do nothing
      }
    }
    fromEnd = false;
  }

  @SuppressWarnings(value = { "unchecked", "rawtypes" })
  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    HierarchicalCtx ctx = latestHierarchicalCtx();

    if (!current.primitive || (ctx != null && qName.equalsIgnoreCase(ctx.linkedOpt.actualQName))) {
      HierarchicalCtx parentCtx = parent();
      try {
        if (parentCtx != null) {
          if (List.class.isAssignableFrom(parentCtx.field.getClass())) {
            ((List) parentCtx.field).add(ctx.field);
          }
          else {
            set(parentCtx.field, ctx.field, ctx.linkedOpt.actualQName);
          }
        }
        else {
          targetedObject = (T) ctx.field;
        }
        this.removeLast();
      } catch (Exception e) {
        // do nothing
      }

    }
    fromEnd = true;
  }

  //## internal method's ##

  /**
   * <p>method to check if a property is iterable</p>
   * @param qname name of xml attribute
   * @return true if the field name appears at {@link this#collectionFields()}
   */
  private boolean isIterable(String qname) {
    return Stream.of(collectionFields()).anyMatch(qname::equalsIgnoreCase);
  }

  /**
   * method that build option properties of xml field
   * @param qname xml field name
   * @return {@link Option} built properties for that field
   */
  private Option buildOpt(String qname) {
    return Option.of(qname, isIterable(qname));
  }

  /**
   * <p>this method save actual object / collection inside adequate collection of
   * {@link HierarchicalCtx} and act as mapping context</p>
   * @param obj parent object / collection to persist
   * @param linkedOpt linked option
   */
  private void push(Object obj, Option linkedOpt) {
    if (linkedOpt.iterable) {
      this.context.add(HierarchicalCtx.of(this.listImplementation(), linkedOpt));
    }
    else {
      this.context.add(HierarchicalCtx.of(obj, linkedOpt));
    }
  }

  /**
   * <p>set targeted object with targeted value by using reflection</p>
   * @param obj targeted object
   * @param value value to set
   * @param fieldname the field name of the attribute in targeted object
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  private void set(Object obj, Object value, String fieldname) throws NoSuchFieldException, IllegalAccessException {

    List<Field> fields = new ArrayList<>(Arrays.asList(obj.getClass().getDeclaredFields()));
    if (obj.getClass().getSuperclass() != null) { // has super class;
      fields.addAll(Arrays.asList(obj.getClass().getSuperclass().getDeclaredFields()));
    }
    Field field = fields.stream().filter(f -> f.getName().equalsIgnoreCase(fieldname)).findFirst().orElse(null);
    if (field != null) { // set only when field found
      if (!field.canAccess(obj)) { // if object cannot access field via reflection, force it !
        field.setAccessible(true);
      }

      if ((field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(int.class))
          && value.getClass().isAssignableFrom(String.class)) { // field is integer
        field.set(obj, Integer.parseInt((String) value));
      }
      else if (field.getType().getSuperclass() != null && Enum.class.isAssignableFrom(field.getType().getSuperclass()) &&
          String.class.isAssignableFrom(value.getClass())) { // field is enum
        field.set(obj, ConversionUtils.convertToEnumVal(field.getType(), (String) value));
      }
      else if (Date.class.isAssignableFrom(field.getType()) && String.class.isAssignableFrom(value.getClass())) { // field is date
        field.set(obj, ConversionUtils.tryConvertDate((String) value));
      }
      else { // treat as object
        field.set(obj, value); // set the value on the reflected object
      }
    }
  }

  /**
   * <p>
   *   retrieve parent of actual object<br/> parent correspond to context[size - 2]
   *   if it doesn't exist return null
   * </p>
   * @return {@link HierarchicalCtx} if found or null
   */
  private HierarchicalCtx parent() {
    if (this.context.isEmpty()) {
      throw new RuntimeException("impossible de retrouver l'élément parent à partir du contexte");
    }
    if (this.context.size() >= 2) { // parent in context
      return this.context.get(this.context.size() - 2);
    }
    return null;
  }

  /**
   * get the latest object pushed in hierarchical context
   * @return {@link Object}
   */
  private HierarchicalCtx latestHierarchicalCtx() {
    if (this.context.isEmpty()) {
      return null;
    }
    return this.context.get(context.size() - 1);
  }

  /**
   * <p>removes the last items from hierarchical context</p>
   */
  private void removeLast() {
    if (!this.context.isEmpty()) {
      this.context.remove(this.context.size() - 1);
    }
  }

  // static classes

  /**
   * <p>
   *   class that hold's mapping attribute for each element
   * <b>Warning</b> option should refer to only field and should be 
   * cleared after {@link DefaultHandler#endElement(String, String, String)}
   * reached for element with name {@link #actualQName}
   * </p>
   */
  public static class Option {
    public String actualQName;

    public boolean iterable;

    public boolean primitive;

    private Option() {
      // private constructor
    }

    /**
     * static method for building xml mapping properties
     * @param qname xml field name
     * @param iterable is field a sub class of {@link Collection}
     * @return {@link Option}
     */
    public static Option of(String qname, boolean iterable) {
      Option instance = new Option();
      instance.actualQName = qname;
      instance.iterable = iterable;
      return instance;
    }
  }

  /**
   * <p>static class that holds a context for hierarchical collection / object <br/></p>
   * for a given class:
   * <code>
   *   public class Foo {
   *     private List<Object> props1;
   *     private List<Object> props2;
   *   }
   * </code>
   * <p>
   * {@link GenericDefaultHandler} class will hold an array with
   * two entries of {@link HierarchicalCtx}<br/>
   * this hierarchical context will allow to map correctly nested object / collection
   * </p>
   */
  private static class HierarchicalCtx {
    public Object field;

    public Option linkedOpt;

    private HierarchicalCtx() {
      // private constructor
    }

    public static HierarchicalCtx of(Object o, Option opt) {
      HierarchicalCtx ctx = new HierarchicalCtx();
      ctx.field = o;
      ctx.linkedOpt = opt;
      return ctx;
    }
  }
}
