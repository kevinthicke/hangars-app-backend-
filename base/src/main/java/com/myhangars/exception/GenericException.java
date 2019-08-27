package com.myhangars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GenericException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFound extends RuntimeException {

        public NotFound() {
            super("Items not found");
        }

        public NotFound(String name) {
            super(String.format("There is no element with name %s", name));
        }

        public NotFound(long id) {
            super(String.format("There are no element with id %d", id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class AlreadyExists extends RuntimeException {

        public AlreadyExists(String name) {
            super(String.format("Element %s already exists in DB", name));
        }
    }
}
