package be.commit_wealth.constants;

public final class ConstantValue {

    public static final String USERNAME_PATTERN = "^[A-Za-z0-9-_.]+$";
    public static final String ALLOWED_SPECIAL_CHARACTERS = "!@#$%^&*()?/\\+-=<>~[]{}|_";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\Q" + ALLOWED_SPECIAL_CHARACTERS + "\\E]).*$";
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String USERNAME_CONSTRAINT_ERROR_MESSAGE = "Username must contain only A-Z, a-z, 0-9, -, _, .";
    public static final String USERNAME_BELOW_MINIMUM_ERROR_MESSAGE = "Username is required";
    public static final String USERNAME_EXISTS_ERROR_MESSAGE = "Username already exists";
    public static final String USERNAME_NOT_FOUND_ERROR_MESSAGE = "User not found with username: ";
    public static final String USER_ERROR_MESSAGE = "User not found";
    public static final String USERNAME_OR_PASSWORD_ERROR_MESSAGE = "Username or password is incorrect";

    public static final String PASSWORD_BLANK_ERROR_MESSAGE = "Password is required";
    public static final String PASSWORD_BELOW_MINIMUM_ERROR_MESSAGE = "Password must be at least " + PASSWORD_MIN_LENGTH + " characters long";
    public static final String PASSWORD_CONSTRAIN_ERROR_MESSAGE = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character";

    public static final String EMAIL_CONSTRAIN_ERROR_MESSAGE = "Email should be like username@email.com";
    public static final String EMAIL_EXISTS_ERROR_MESSAGE = "Email already exists";

    public static final String DATE_TIME_CONSTRAIN_ERROR_MESSAGE = "Datetime must be YYYY-MM-DDTHH:mm:ss format";
}