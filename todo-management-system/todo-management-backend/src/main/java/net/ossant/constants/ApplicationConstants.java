package net.ossant.constants;

public class ApplicationConstants {

    public static final String REACT_APP_ORIGIN = "http://localhost:3000";

    public static final String ALL = "*";

    public final static String TODO_BASE_URL = "api/todos";

    public final static String AUTHORIZATION_BASE_URL = "api/auth";

    public static final Long MAX_AGE = 3600L;

    public static final String BEARER = "Bearer ";

    public static final Integer BEARER_PREFIX_LENGTH = 7;

    public static final String JWT_KEY = "fdc46a23a4dff99a8c9b4cb41ba271864b3307f5d7e643213159cf224a60b9ed";

    public static final String JWT_ISSUER = "Todo Management";

    public static final String USERNAME_OR_EMAIL_CLAIM = "username";

    public static final String AUTHORITIES_CLAIM = "authorities";

    public static final String JWT_SUBJECT = "JWT Token";

    public static final Long JWT_EXPIRATION = 604800000L;

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
