package org.com.MSBank.login_service.login_service.utils;

import java.util.UUID;

public class Utils {

    public static String creatToken() {
        return UUID.randomUUID().toString();
    }
}
