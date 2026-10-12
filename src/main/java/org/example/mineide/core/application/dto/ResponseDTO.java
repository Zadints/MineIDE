package org.example.mineide.core.application.dto;

import javafx.stage.Stage;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ResponseDTO {
    private boolean Status = false;
    private String Ip;

    private String Link;

    private String Version;
    private String Software;

    public ResponseDTO(){}

    public ResponseDTO(String Ip, String Version, String Software){
        this.Ip = Ip;
        this.Version = Version;
        this.Software = Software;
    }

    //-------------------------------------/ S&G /-----------------------------------------------------------

    public String Ip(){return Ip;}
    public void setIp(String Ip){this.Ip = Ip;}

    public String Link(){return Link;}
    public void setLink(String Link) {this.Link = Link;}

    public String Version(){return Version;}
    public void setVers(String Version) {this.Version = Version;}

    public String Software(){return Software;}
    public void setSoft(String Software){this.Software = Software;}

    public Boolean Status(){return Status;}
    public void setStatus(Boolean Status){this.Status = Status;}




}
