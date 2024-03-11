package net.ossant.utils;

import static net.ossant.constants.ApplicationConstants.*;

public class ErrorBuilderUtil {

    public static String todoNotFoundErrorMessage(Long todoId) {
        return String.format(TODO_BY_ID_NOT_FOUND_ERROR_MESSAGE, todoId);
    }

    public static String userNotFoundErrorMessage(String usernameOrEmail) {
        return String.format(USER_NOT_FOUND_ERROR_MESSAGE, usernameOrEmail);
    }

    public static String badCredentialsErrorMessage() {
        return String.format(BAD_CREDENTIALS_ERROR_MESSAGE);
    }

    public static String todoApiUsernameErrorMessage(String username) {
        return String.format(TODO_API_USERNAME_ERROR_MESSAGE, username);
    }

    public static String todoApiEmailErrorMessage(String email) {
        return String.format(TODO_API_EMAIL_ERROR_MESSAGE, email);
    }

}
