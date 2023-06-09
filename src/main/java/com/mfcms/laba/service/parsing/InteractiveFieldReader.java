package com.mfcms.laba.service.parsing;

import java.io.IOException;

import com.mfcms.laba.exceptions.ValidationException;
import com.mfcms.laba.service.IOManager;
import com.mfcms.laba.service.parsing.field.FieldInfo;

public class InteractiveFieldReader extends FieldReader {

    public InteractiveFieldReader(final IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected String promptString(final String prompt) throws IOException {
        io.out().append("Введите ");
        return super.promptString(prompt);
    }

    @Override
    public <V> V prompt(String promptPrefix, final FieldInfo<V> promptInfo) {
        while (true) {
            try {
                try {
                    V v = promptField(promptPrefix, promptInfo);
                    return v;
                } catch (ValidationException e) {
                    io.out().append("Ошибка ввода: ");
                    io.out().append(promptInfo.getName());
                    io.out().append(" ");
                    io.out().append(e.getMessage());
                    io.out().append("!\n");
                    // Read again
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            } 
        }
    }

}
