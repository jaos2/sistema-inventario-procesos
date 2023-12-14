package com.sistema.inventario.util;

import lombok.Getter;

@Getter
public enum Constants {


    USER_NOT_FOUND( "User not found"),
    USER_IS_NULL("User is null"),
    ADDRESS_NOT_FOUND("Address not found"),
    CREDENTIAL_INVALID("Invalid username or password"),
    DOCUMENT_ALREADY_EXISTS("Document already exists");
    private final String message;
    Constants(String s){
        this.message = s;
    }

}
