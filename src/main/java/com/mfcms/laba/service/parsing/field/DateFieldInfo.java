package com.mfcms.laba.service.parsing.field;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mfcms.laba.exceptions.ValidationException;

public class DateFieldInfo extends FieldInfo<Date> {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    public DateFieldInfo(String name) {
        super(name);
    }

    @Override
    public Date parseValue(String source) throws ValidationException {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            throw new ValidationException(
                    "could not parse date '" + source + "'; expected format " + dateFormat.toString(), e);
        }
    }
}