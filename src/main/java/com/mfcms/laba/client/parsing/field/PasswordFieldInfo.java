package com.mfcms.laba.client.parsing.field;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mfcms.laba.common.exceptions.ValidationException;

public class PasswordFieldInfo extends StringFieldInfo {
    public PasswordFieldInfo() {
        super("password");
    }

    @Override
    public String parseValue(String source) {
        try {
            source = super.parseValue(source);
            var digestor = MessageDigest.getInstance("MD2");
            byte[] digest = digestor.digest(source.getBytes(StandardCharsets.UTF_8));
            var num = new BigInteger(1, digest);
            var hash = num.toString(16);
            if(hash.length() < 32) {
                StringBuilder hashBuilder = new StringBuilder();
                for(int i = 32 - hash.length(); i >= 0; i--) {
                    hashBuilder.append("0");
                }
                hashBuilder.append(hash);
                hash = hashBuilder.toString();
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void validate(String value) throws ValidationException {
        super.validate(value);
        if(value.length() < 32) {
            throw new ValidationException("Hash must be longer than 32 symbols");
        }
    }
}
