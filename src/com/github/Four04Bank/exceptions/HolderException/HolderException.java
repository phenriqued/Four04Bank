package com.github.Four04Bank.exceptions.HolderException;

import java.io.Serializable;

public class HolderException extends RuntimeException implements Serializable {
    private final static long serialVersionUID = 1l;

    public HolderException(String message) {
        super(message);
    }
}
