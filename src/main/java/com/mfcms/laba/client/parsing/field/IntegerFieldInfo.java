package com.mfcms.laba.client.parsing.field;

import com.mfcms.laba.common.exceptions.ValidationException;

public class IntegerFieldInfo extends FieldInfo<Integer> {
    public IntegerFieldInfo(String name) {
        super(name);
    }

    @Override
    public Integer parseValue(String source) throws ValidationException {
        try {
            return Integer.decode(source);
        } catch (NumberFormatException e) {
            throw new ValidationException(e);
        }
    }
}