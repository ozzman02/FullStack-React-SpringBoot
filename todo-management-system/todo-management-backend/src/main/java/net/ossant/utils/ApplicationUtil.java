package net.ossant.utils;

import static net.ossant.constants.ApplicationConstants.*;

public class ApplicationUtil {

    public static String todoNotFoundErrorMessage(Long value) {
        return String.format(TODO_BY_ID_NOT_FOUND_ERROR_MESSAGE, value);
    }

    public static String userNotFoundErrorMessage(String usernameOrEmail) {
        return String.format(USER_NOT_FOUND_ERROR_MESSAGE, usernameOrEmail);
    }

    public static String badCredentialsErrorMessage() {
        return String.format(BAD_CREDENTIALS_ERROR_MESSAGE);
    }

}
