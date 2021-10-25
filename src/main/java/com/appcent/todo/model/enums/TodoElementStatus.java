package com.appcent.todo.model.enums;

public enum TodoElementStatus {
    DONE("Done"),
    PENDING("Pending"),
    IN_PROGRESS("In Progress");


    private final String text;

    TodoElementStatus(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }

}
