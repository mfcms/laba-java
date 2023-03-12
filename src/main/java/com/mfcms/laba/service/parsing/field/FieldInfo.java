package com.mfcms.laba.service.parsing.field;

import com.mfcms.laba.exceptions.ValidationException;

public abstract class FieldInfo<V> {
    private String name;

    public FieldInfo(String name) {
        this.name = name;
    }

    public void validate(V value) throws ValidationException {}

    public abstract V parseValue(String source) throws ValidationException;

    public String getName() {
        return name;
    }
}