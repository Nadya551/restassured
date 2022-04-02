package constants;

import utils.UtilsMethod;

/**
 * Класс констант для составления URL
 */
public class Constants {


    /**
     * URALSIB_SERVER_INTERNAL используется для smoke тестов
     */
    public static class ServerName {
        public static String SERVER_GATEWAY = UtilsMethod.getValue("url.gateway");
    }

    /**
     * Paths до микросервисов
     */
    public static class Path {
        public static String USER_PATH = "user/";
    }

    /**
     * Классы с ENDPOINTS для каждого микросервиса
     * ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
     */

    public static class UserEndpoints {
        public static String CREATE_ACCOUNT = "create/account";

    }

}
