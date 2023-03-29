package com.mfcms.laba.client.parsing.object;

import java.io.IOException;

import com.mfcms.laba.client.parsing.FieldReader;
import com.mfcms.laba.client.parsing.field.MusicGenreFieldInfo;
import com.mfcms.laba.common.model.objects.MusicGenre;

public class MusicGenreReader extends ObjectReader<MusicGenre> {
    public MusicGenreReader(FieldReader fieldReader) {
        super(fieldReader);
    }

    @Override
    public MusicGenre read(String promptPrefix) throws IOException {
        return fieldReader.prompt(promptPrefix, genreStringInfo);
    }

    static MusicGenreFieldInfo genreStringInfo = new MusicGenreFieldInfo(String.format("Жанр (варианты: %s)", MusicGenreFieldInfo.optionsString));
}
