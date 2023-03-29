package com.mfcms.laba.client.parsing;

import java.io.IOException;

import com.mfcms.laba.client.parsing.field.FieldInfo;
import com.mfcms.laba.client.parsing.field.PasswordFieldInfo;
import com.mfcms.laba.common.exceptions.ValidationException;
import com.mfcms.laba.common.services.IOManager;

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
        String source;
        if (fieldInfo instanceof PasswordFieldInfo) {
            var console = System.console();
            char[] pwd = console.readPassword("Введите %s%s (secure): ", promptPrefix, fieldInfo.getName());
            source = new String(pwd);
        } else {
            source = promptString(promptPrefix + fieldInfo.getName());
        }
        V result = !source.isEmpty() ? fieldInfo.parseValue(source) : null;
        fieldInfo.validate(result);
        return result;
    }
}
