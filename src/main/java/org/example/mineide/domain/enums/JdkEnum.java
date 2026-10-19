package org.example.mineide.domain.enums;

public enum JdkEnum {
    JDK_8("1.16.5"),
    JDK_16("1.17.1"),
    JDK_17("1.20.4"),
    JDK_21("1.21.4"),

    JDK_25("26.9"),
    JDK_26("26.9");


    private final String value;

    JdkEnum(String value){
        this.value = value;
    }


    public String getmax() {
        return value;
    }
}

