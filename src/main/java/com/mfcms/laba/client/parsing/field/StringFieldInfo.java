package com.mfcms.laba.client.parsing.field;

import com.mfcms.laba.common.exceptions.ValidationException;

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