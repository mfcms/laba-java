package com.mfcms.laba.service.parsing.object;

import java.io.IOException;

import com.mfcms.laba.model.objects.Label;
import com.mfcms.laba.service.parsing.FieldReader;
import com.mfcms.laba.service.parsing.field.StringFieldInfo;

public class LabelReader extends ObjectReader<Label> {

    public LabelReader(FieldReader fieldReader) {
        super(fieldReader);
    }

    @Override
    public Label read(String promptPrefix) throws IOException {
        String name = fieldReader.prompt(promptPrefix, nameFieldInfo);
        return name == null ? null : new Label(name);
    }

    static StringFieldInfo nameFieldInfo = new StringFieldInfo("name");
}
