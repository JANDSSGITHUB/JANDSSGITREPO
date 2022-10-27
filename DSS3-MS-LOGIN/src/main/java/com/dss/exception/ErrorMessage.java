package com.dss.exception;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ErrorMessage {
    private String errorMessage;
    private int errorCode;
    private String documentation;




    public ErrorMessage(String errorMessage, int errorCode , String documentation){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.documentation = documentation;
    }

    public ErrorMessage() {

    }
}
