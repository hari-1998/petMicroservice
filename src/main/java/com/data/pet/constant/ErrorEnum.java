package com.data.pet.constant;

public enum ErrorEnum {

    ERROR404("The records not found !!! Please ensure requested values are correct !!! "),
    ERROR500("Sorry! This service is temporarily unavailable. Please try again later."),
    ERROR503("DB Service Temporarily down !!! Try again after some time..."),
    ERROR400(" Invalid/Bad Request.. please ensure request body/params are correct"),
    ERROR401("Unauthorized request..method not allowed.."),
    ERROR405("Unauthorized request..method not allowed..");


    private String message;

    public String getMessage() {
        return message;
    }

    private ErrorEnum(String message) {
        this.message = message;
    }
}
