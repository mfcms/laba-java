package com.mfcms.laba.client.parsing.object;

import java.io.IOException;

import com.mfcms.laba.client.parsing.FieldReader;
import com.mfcms.laba.client.parsing.field.StringFieldInfo;
import com.mfcms.laba.common.model.objects.Label;

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
