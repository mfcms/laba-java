package com.mfcms.laba.client.parsing.object;

import java.io.IOException;

import com.mfcms.laba.client.parsing.FieldReader;
import com.mfcms.laba.client.parsing.field.PasswordFieldInfo;
import com.mfcms.laba.client.parsing.field.StringFieldInfo;
import com.mfcms.laba.common.exceptions.ValidationException;
import com.mfcms.laba.common.model.Credentials;

public class CredentialsReader extends ObjectReader<Credentials> {

    public CredentialsReader(FieldReader fieldReader) {
        super(fieldReader);
    }

    @Override
    public Credentials read(String promptPrefix) throws IOException {
        return new Credentials(
            fieldReader.prompt(usernameFieldInfo),
            fieldReader.prompt(passwordFieldInfo)
        );
    }
    
    static StringFieldInfo usernameFieldInfo = new StringFieldInfo("Username") {
        @Override public void validate(String value) throws ValidationException {
            if(value == null) {
                throw new ValidationException("Username cannot be null");
            }
        }; 
    };

    static StringFieldInfo passwordFieldInfo = new PasswordFieldInfo();
}
