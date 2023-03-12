package com.mfcms.laba.service.parsing.object;

import java.io.IOException;

import com.mfcms.laba.model.objects.MusicGenre;
import com.mfcms.laba.service.parsing.FieldReader;
import com.mfcms.laba.service.parsing.field.FieldInfo;
import com.mfcms.laba.service.parsing.field.MusicGenreFieldInfo;

public class MusicGenreReader extends ObjectReader<MusicGenre> {

    public MusicGenreReader(FieldReader fieldReader) {
        super(fieldReader);
    }

    @Override
    public MusicGenre read(String promptPrefix) throws IOException {
        return fieldReader.prompt(promptPrefix, genreStringInfo);
    }

    static FieldInfo<MusicGenre> genreStringInfo = new MusicGenreFieldInfo(String.format("Жанр (варианты: %s)", MusicGenreFieldInfo.optionsString));
}
