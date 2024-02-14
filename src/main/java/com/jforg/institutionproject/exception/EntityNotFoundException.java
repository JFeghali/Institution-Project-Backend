package com.jforg.institutionproject.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }
    public EntityNotFoundException(Class<?> entity) {
        super("No records found for Entity " + entity.getSimpleName().toLowerCase());
    }

}