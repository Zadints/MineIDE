package org.example.mineide.core.application.usecase;

import org.example.mineide.core.domain.enums.JdkEnum;
import org.example.mineide.core.application.dto.VersionDTO;

import java.util.regex.Pattern;

public class EvalueVersionUseCase {

    public static VersionDTO evalueJDK(JdkEnum jdk,String version){
        String jdkVersion = jdk.name();
        String maxVersion = jdk.getmax();
        String separator = Pattern.quote(".");

        String[] versionParts = version.split(separator);
        String[] MaxJdkParts = maxVersion.split(separator);

        VersionDTO Data = new VersionDTO(maxVersion, "Please select another version supported for the selected jdk \\n Recommend Minecraft Version:\" + maxVersion");

        if (Integer.parseInt(versionParts[0]) > Integer.parseInt(MaxJdkParts[0])){
            return Data;}
        else if (versionParts[0] == "1" && Integer.parseInt(versionParts[1]) > Integer.parseInt(MaxJdkParts[1])){
            return Data;
        }else {
            Data.setStatus(true);
            Data.setMessage("ok");
            return Data;
        }
    }
}
