package com.mfcms.laba.service.parsing.object;

import java.io.IOException;

import com.mfcms.laba.service.parsing.FieldReader;

public abstract class ObjectReader<T> {
    protected FieldReader fieldReader;

    public ObjectReader(FieldReader fieldReader) {
        this.fieldReader = fieldReader;
    }

    public T read() throws IOException { return read(""); }
    public abstract T read(String promptPrefix) throws IOException;
}
