package esieaa.jmusic.business;

import esieaa.jmusic.exception.FormatInconnuException;
import esieaa.jmusic.util.ConversionUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * parent class for each commands
 * offer some common method for interaction with user and validation
 * each command shoul extends this class
 */
public abstract class AbstractCommand implements ICommand {
  protected Scanner scanner = new Scanner(System.in);

  /**
   * if true confirmation is needed before perform the action
   */
  protected boolean askForConfirmation;

  private static final String PATTERN_YES_NO = "^[ynYN].*";

  protected abstract String greetingMessage();

  protected AbstractCommand(boolean askForConfirmation) {
    this.askForConfirmation = askForConfirmation;
  }

  public void execute() throws IOException {

    String response = "";
    if (askForConfirmation) {
      System.out.println(greetingMessage());
      System.out.println("Voulez vous continuer ? y/n");
      boolean firstIter = true;
      do {
        if (!firstIter) {
          System.out.println("R�ponse invalide, r�pondez : y/n");
        }
        response = scanner.nextLine();
        firstIter = false;
      } while (!response.matches(PATTERN_YES_NO));
    }

    if (!askForConfirmation || response.toUpperCase().startsWith("Y")) {
      this.executeInternal();
    }
    else {
      System.out.println("Ok, sortie du mode '" + alias() + "'");
    }
  }

  protected String ask(String message) {
    System.out.println(message);
    return this.scanner.nextLine();
  }

  /**
   * <p>
   *   method that as user for restricted (in list) value<br/>
   *   the response is validated until having an adequate response
   * </p>
   * @param message initial message including the statement
   * @param authorizedValues list of authorized values
   * @return the user response (which exists in input list)
   */
  protected String askWithAuthorizedValueList(String message, List<String> authorizedValues) {
    boolean firstIter = true;
    String response = "";
    do {
      if (!firstIter) {
        System.out.println("valeur invalide, valeurs autoris�es : " + authorizedValues.toString());
      }
      else {
        System.out.println(message + " valeurs autoris�es : " + authorizedValues.toString());
        firstIter = false;
      }
      response = scanner.nextLine();
    } while (!this.isInList(response, authorizedValues));
    return handleCaseDiff(response, authorizedValues);
  }

  /**
   * <p>ask user for a strictly positive integer parsable string value </p>
   * @param message statement to print
   * @return an integer > 0
   */
  protected int askForInteger(String message) {
    boolean firstIter = true;
    Integer res = null;
    do {
      if (!firstIter) {
        if (res == null) {
          System.out.println("La valeur entr�e n'est pas un entier valide ! ");
        }
        else {
          System.out.println("La valeur entr�e n'est pas un entier positif ! ");
        }
        System.out.println("Veuillez r�essayer, (rappel: la valeur doit �tre un entier positif)");
      }
      else {
        System.out.println(message + " (la valeur doit �tre un entier positif)");
        firstIter = false;
      }
      String response = scanner.nextLine();
      res = ConversionUtils.tryParseToInteger(response);
    } while (res == null || res <= 0);
    return res;
  }

  protected Date askForDate(String message) {
    String response = "";
    Date date = new Date();
    boolean firstIter = true;
    do {
      try {
        if (!firstIter) {
          System.out.println("Le format de la date ne correspond pas � " + ConversionUtils.DATE_FORMAT);
        }
        else {
          System.out.println(message + " (le fromat attendu est : " + ConversionUtils.DATE_FORMAT + ")");
          firstIter = false;
        }
        response = scanner.nextLine();
        date = ConversionUtils.tryConvertDate(response);
      } catch (FormatInconnuException e) {
        // do nothing
      }
    } while (date == null);
    return date;
  }

  /**
   * find if a given element exist in list regardless of the case
   * @param val value to search
   * @param list list to search in
   * @return true if exists (regardless case), false else
   */
  private boolean isInList(String val, List<String> list) {
    return list.stream().anyMatch(v -> v.equalsIgnoreCase(val));
  }

  /**
   *
   * @param val
   * @param list
   * @return
   */
  private String handleCaseDiff(String val, List<String> list) {
    return list.stream().filter(v -> v.equalsIgnoreCase(val))
        .findFirst().orElseThrow(() -> new RuntimeException("Une valeur doit forc�ment exister"));
  }
}
