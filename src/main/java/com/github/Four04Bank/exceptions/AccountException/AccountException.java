package com.github.Four04Bank.exceptions.AccountException;

import java.io.Serializable;

public class AccountException extends RuntimeException implements Serializable {
    private final static long serialVersionUID = 1l;

    public AccountException(String message) {
        super(message);
    }
}
