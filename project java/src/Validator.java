import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean valEmail(String input){
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"; // code regex pour definir un mail valide
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE); // indique que les Majuscule et miniscule n'influence pas trop
        Matcher matcher = emailPat.matcher(input); // regarde si le mail match avec le regex
        return matcher.find();
    }
}
