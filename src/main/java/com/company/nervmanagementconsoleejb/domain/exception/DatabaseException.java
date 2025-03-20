package com.company.nervmanagementconsoleejb.domain.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
