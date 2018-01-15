package com.mpls.v2.service.exceptions;

public class SaveException extends RuntimeException{
    private static String prefix = "SaveException:[";
    private String message;

    public SaveException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
