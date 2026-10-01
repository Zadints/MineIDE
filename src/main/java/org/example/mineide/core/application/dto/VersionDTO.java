package org.example.mineide.core.application.dto;

import org.example.mineide.core.domain.annotations.Optional;

import javax.print.DocFlavor;

public class VersionDTO {
    boolean status = false;
    String Recomend;
    String Message;
    public void VersionDTO(String recomend, String Message){
        this.Recomend = recomend;
        this.Message = Message;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
>}