package com.github.astutesparrow.jersey.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 智深
 * @since: 2013-05-11
 */
public class ParamUtil {

    public static Map<String,String> parse(String paramString) {
        Map<String,String> params = new HashMap<String,String>();
        String[] paramPairs = paramString.split("&");
        for(String param : paramPairs) {
            String[] key_value = param.split("=");
            params.put(key_value[0], key_value[1]);
        }
        return params;
    }

}
