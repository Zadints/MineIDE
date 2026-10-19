package org.example.mineide.domain.utils;

import java.util.UUID;

public class IdGenerate {
    public static String getNewId(){
        return UUID.randomUUID().toString();
    }
}
