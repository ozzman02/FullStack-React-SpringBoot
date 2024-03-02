package net.ossant.constants;

public class ApplicationConstants {

    public final static String ALLOWED_CROSS_ORIGINS = "*";

    public final static String TODO_BASE_URL = "api/todos";

    public final static String TODO_ID = "{id}";

    public final static String COMPLETE_TODO_URL = TODO_ID + "/complete";

    public final static String INCOMPLETE_TODO_URL = TODO_ID + "/incomplete";

    public final static String TODO_RESOURCE_BY_ID_NOT_FOUND = "Todo with id %s does not exist";

}
