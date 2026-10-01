package org.example.mineide.core.application.usecase;

import org.example.mineide.core.domain.enums.JdkEnum;
import org.example.mineide.core.application.dto.VersionDTO;

import java.util.regex.Pattern;

public class EvalueVersionUseCase {

    public VersionDTO evalueJDKversion(JdkEnum jdk,String version){
        String jdkVersion = jdk.name();
        String maxVersion = jdk.getmax();
        String separator = Pattern.quote(".");

        String[] versionParts = version.split(separator);
        String[] jdkParts = maxVersion.split(separator);

        if (versionParts[0] == "26"){
            VersionDTO Data = VersionDTO(null, null);
            Data.setStatus(true);
            return Data;}
        else if (versionParts[1] > jdkParts[1])

        return ;
    }
}
