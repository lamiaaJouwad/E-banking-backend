package com.example.ebankingbackend.Exceptions;

public class BalanceNotSufficientException extends Exception {
    public BalanceNotSufficientException(String message) {
    super(message);
    }
}
