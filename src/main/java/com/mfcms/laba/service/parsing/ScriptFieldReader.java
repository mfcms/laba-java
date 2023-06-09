package com.mfcms.laba.service.parsing;

import java.io.IOException;

import com.mfcms.laba.exceptions.ValidationException;
import com.mfcms.laba.service.IOManager;
import com.mfcms.laba.service.parsing.field.FieldInfo;

public class ScriptFieldReader extends FieldReader {

    public ScriptFieldReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected String promptString(final String prompt) throws IOException {
        String r = super.promptString(prompt);
        io.out().append(r);
        io.out().append("\n");
        return r;
    }

    @Override
    public <V> V prompt(String promptPrefix, FieldInfo<V> promptInfo) {
        try {
            V v = promptField(promptPrefix, promptInfo);
            return v;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        System.exit(1);
        return null;
    }

}
