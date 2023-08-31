package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private final static String LOGIN_REGEX = "^[a-zA-Z0-9]{4,20}$";
    public boolean validateLogin(String login) {

        // Accepts only letters, numbers, and length between 4 - 20 characters

        Pattern loginPattern = Pattern.compile(LOGIN_REGEX);
        Matcher loginMatcher = loginPattern.matcher(login);
        return loginMatcher.matches();
    }

    public boolean validatePassword(String password) {

        // Requires a minimum of 8 characters, including at least one lowercase letter,
        // one uppercase letter, and one digit.

        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();
    }
}
