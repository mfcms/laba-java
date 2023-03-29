package com.mfcms.laba.client.parsing.field;

import com.mfcms.laba.common.exceptions.ValidationException;

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