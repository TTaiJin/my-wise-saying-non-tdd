package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Command {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Command(String cmd) {
        this.paramsMap = new HashMap<>();
        String[] cmdBits = cmd.trim().split("\\?",2);
        this.actionName = cmdBits[0].trim();

        if (cmdBits.length > 1 && !cmdBits[1].trim().isEmpty()) {
            parseParams(cmdBits[1].trim());
        }

    }

    private void parseParams(String queryString) {
        String[] paramsArray = queryString.trim().split("&");
        for (String param : paramsArray) {
            String[] paramBits = param.trim().split("=");
            this.paramsMap.put(paramBits[0], paramBits.length > 1 ? paramBits[1] : "");
        }
    }

    public String getActionName() {
        return actionName;
    }

    public int getParamAsInt(String key, int defaultValue) {
        String value = paramsMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
