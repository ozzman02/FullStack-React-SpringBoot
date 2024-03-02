package net.ossant.utils;

import static net.ossant.constants.ApplicationConstants.TODO_RESOURCE_BY_ID_NOT_FOUND;

public class ApplicationUtil {

    public static String addErrorMessage(Long value) {
        return String.format(TODO_RESOURCE_BY_ID_NOT_FOUND, value);
    }
}
