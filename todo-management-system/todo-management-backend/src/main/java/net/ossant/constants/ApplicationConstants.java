package net.ossant.constants;

public class ApplicationConstants {

    public final static String ALLOWED_CROSS_ORIGINS = "*";

    public final static String TODO_BASE_URL = "api/todos";

    public final static String AUTHORIZATION_BASE_URL = "api/auth";

    public final static String REGISTRATION_URL = "/register";

    public final static String LOGIN_URL = "/login";

    public final static String TODO_ID = "{id}";

    public final static String COMPLETE_TODO_URL = TODO_ID + "/complete";

    public final static String INCOMPLETE_TODO_URL = TODO_ID + "/incomplete";

    public final static String TODO_BY_ID_NOT_FOUND_ERROR_MESSAGE = "Todo with id %s does not exist";

    public final static String USER_NOT_FOUND_ERROR_MESSAGE = "User with username or email %s does not exist";

    public final static String BAD_CREDENTIALS_ERROR_MESSAGE = "Invalid Credentials";

    public final static String TODO_API_USERNAME_ERROR_MESSAGE = "User with username %s already exists";

    public final static String TODO_API_EMAIL_ERROR_MESSAGE = "User with email %s already exists";

}
