package com.javarush.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GlobalExceptionHandler {
    public static final String BAD_REQUEST_MESSAGE = "Given ID is invalid: ";
    public static final String NOT_FOUND_REQUEST_MESSAGE = "ID is not found: ";
    public static final String NUMBER_FORMAT_EXCEPTION = "Please enter number: ";

    public static int catchException(String sID) {
        try {
            int id = Integer.parseInt(sID);
            if (id < 0 || id == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + sID);
            }
            return id;
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, NUMBER_FORMAT_EXCEPTION + sID);
        }
    }
}
