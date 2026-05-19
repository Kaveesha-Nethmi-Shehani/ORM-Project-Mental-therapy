package org.example.mental_therapy_orm.exception;

public class SchedulingConflictException extends RuntimeException {
    public SchedulingConflictException(String message) {
        super(message);
    }
}
