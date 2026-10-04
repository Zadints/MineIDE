package org.example.mineide.core.application.dto;




public class VersionDTO {
    boolean Status = false;
    String Recomend;
    String Message;
    public VersionDTO(String recomend, String Message){
        this.Recomend = recomend;
        this.Message = Message;
    }
    public void setStatus(boolean status) {this.Status = status;}
    public boolean getStatus(){return Status;}

    public void setMessage(String message) {this.Message = message;}
    public String getMessage() {return Message;}

    public void setRecomend(String recomend){this.Recomend = recomend;}
    public String getRecomend(){return Recomend;}
}