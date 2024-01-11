package com.todo.controller;

public enum TaskOrder {
    ID("id"), // default
    DESCRIPTION("description"),
    STATUS("status"),

    TIME("time");

    private final String fieldName;

    TaskOrder(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
