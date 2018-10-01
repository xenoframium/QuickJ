package com.xenoframium.quickj.symbols;

public class InvalidSyntaxException extends RuntimeException {
    public final String message;
    public InvalidSyntaxException() {
        super();
        message = "";
    }
    public InvalidSyntaxException(String s) {
        super(s);
        message = s;
    }
}
