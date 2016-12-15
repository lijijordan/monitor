package com.monitor.common.define;

import java.util.HashMap;
import java.util.Map;

public enum DataTypeEnum {
    Temperature(1, "Temperature"), PH(2, "PH"), Salinity(3, "Salinity"), TDS(4,
            "TDS"), Light(5, "Light"), HCHO(6, "HCHO"), PM2_5(7, "PM2_5"), PM10(8, "PM10");
    // Implementing a fromString method on an enum type
    private static final Map<String, DataTypeEnum> stringToEnum = new HashMap<String, DataTypeEnum>();

    static {
        // Initialize map from constant name to enum constant
        for (DataTypeEnum DataTypeEnum : values()) {
            stringToEnum.put(DataTypeEnum.toString(), DataTypeEnum);
        }
    }

    private int key;
    private String val;

    DataTypeEnum(int key, String val) {
        this.key = key;
        this.val = val;
    }

    // Returns DataTypeEnum for string, or null if string is invalid
    public static DataTypeEnum fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
