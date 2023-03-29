package com.mfcms.laba.client.parsing.field;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mfcms.laba.common.exceptions.ValidationException;
import com.mfcms.laba.common.model.objects.MusicGenre;

public class MusicGenreFieldInfo extends FieldInfo<MusicGenre> {
    public MusicGenreFieldInfo(String name) {
        super(name);
    }

    public static String optionsString = Stream.of(MusicGenre.values())
            .map(MusicGenre::toString)
            .collect(Collectors.joining(", "));

    @Override
    public MusicGenre parseValue(String value) throws ValidationException {
        return parseFromString(value);
    }

    public static MusicGenre parseFromString(String value) throws ValidationException {
        for (MusicGenre genre : MusicGenre.values()) {
            if (genre.toString().equalsIgnoreCase(value)) {
                return genre;
            }
        }
        throw new ValidationException("Unknown genre; try one of these: " + optionsString);
    }
}