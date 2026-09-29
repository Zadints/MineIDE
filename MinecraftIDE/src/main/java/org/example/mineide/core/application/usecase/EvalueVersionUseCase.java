package org.example.mineide.core.application.usecase;

import org.example.mineide.core.domain.enums.JdkEnum;

import java.util.regex.Pattern;

public class EvalueVersionUseCase {
    public String evalueJDKversion(JdkEnum jdk,String version){
        String jdkVersion = jdk.name();
        String maxVersion = jdk.getmax();
        String separator = Pattern.quote(".");

        String[] versionParts = version.split(separator);
        String[] jdkParts = maxVersion.split(separator);

        if (versionParts[0] == jdkParts[0]){

        }

        return "el panadero con el pan";
    }
}
