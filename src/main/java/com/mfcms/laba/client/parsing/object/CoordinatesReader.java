package com.mfcms.laba.client.parsing.object;

import java.io.IOException;

import com.mfcms.laba.client.parsing.FieldReader;
import com.mfcms.laba.client.parsing.field.FloatFieldInfo;
import com.mfcms.laba.common.exceptions.ValidationException;
import com.mfcms.laba.common.model.objects.Coordinates;

public class CoordinatesReader extends ObjectReader<Coordinates> {
    public CoordinatesReader(FieldReader fieldReader) {
        super(fieldReader);
    }

    @Override
    public Coordinates read(String promptPrefix) throws IOException {
        return new Coordinates(
            fieldReader.prompt(promptPrefix, xFieldInfo),
            fieldReader.prompt(promptPrefix, yFieldInfo)
        );
    }

    static FloatFieldInfo xFieldInfo = new FloatFieldInfo("X") {
        @Override
        public void validate(Float value) throws ValidationException {
            if (value == null) {
                throw new ValidationException("must not be null");
            }
        }
    };

    static FloatFieldInfo yFieldInfo = new FloatFieldInfo("Y") {
        @Override
        public void validate(Float value) throws ValidationException {
            if (value == null) {
                throw new ValidationException("must not be null");
            }
            if (value > 808) {
                throw new ValidationException("must be less than 808");
            }
        }
    };

}
