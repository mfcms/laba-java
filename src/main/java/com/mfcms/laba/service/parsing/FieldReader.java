package com.mfcms.laba.service.parsing;

import java.io.IOException;

import com.mfcms.laba.exceptions.ValidationException;
import com.mfcms.laba.service.IOManager;
import com.mfcms.laba.service.parsing.field.FieldInfo;

public abstract class FieldReader {
    protected final IOManager io;

    public FieldReader(final IOManager ioManager) {
        this.io = ioManager;
    }

    public <V> V prompt(final FieldInfo<V> promptInfo) {
        return prompt("", promptInfo);
    }

    public abstract <V> V prompt(String promptPrefix, final FieldInfo<V> promptInfo);

    protected String promptString(final String prompt) throws IOException {
        io.out().append(prompt);
        io.out().append(": ");
        io.out().flush();
        return io.in().nextLine().trim();
    }

    protected <V> V promptField(String promptPrefix, final FieldInfo<V> fieldInfo)
            throws IOException, ValidationException {
        String src = promptString(promptPrefix + fieldInfo.getName());
        V r = src == "" ? null : fieldInfo.parseValue(src);
        fieldInfo.validate(r);
        return r;
    }
}
