package com.mfcms.laba.service.parsing.field;

import com.mfcms.laba.exceptions.ValidationException;

public class StringFieldInfo extends FieldInfo<String> {
    public StringFieldInfo(String name) {
        super(name);
    }

    @Override
    public String parseValue(String source) {
        return source;
    }

    @Override
    public void validate(String value) throws ValidationException {
        super.validate(value);
        if (value.isEmpty()) {
            throw new ValidationException("must not be empty");
        }
    }
}