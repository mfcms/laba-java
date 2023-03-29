package com.mfcms.laba.client.parsing.field;

import com.mfcms.laba.common.exceptions.ValidationException;

public class FloatFieldInfo extends FieldInfo<Float> {
    public FloatFieldInfo(String name) {
        super(name);
    }

    @Override
    public Float parseValue(String source) throws ValidationException {
        try {
            return Float.parseFloat(source);
        } catch (NumberFormatException e) {
            throw new ValidationException(e);
        }
    }
}