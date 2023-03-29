package com.mfcms.laba.client.parsing.object;

import java.io.IOException;
import java.util.Date;

import com.mfcms.laba.client.parsing.FieldReader;
import com.mfcms.laba.client.parsing.field.DateFieldInfo;
import com.mfcms.laba.client.parsing.field.IntegerFieldInfo;
import com.mfcms.laba.client.parsing.field.StringFieldInfo;
import com.mfcms.laba.common.exceptions.ValidationException;
import com.mfcms.laba.common.model.objects.MusicBand;

public class MusicBandReader extends ObjectReader<MusicBand> {

    private LabelReader labelParser;
    private CoordinatesReader coordinatesParser;
    private MusicGenreReader musicGenreParser;

    public MusicBandReader(FieldReader fieldReader,
            LabelReader labelParser, CoordinatesReader coordinatesParser, MusicGenreReader musicGenreParser) {
        super(fieldReader);
        this.labelParser = labelParser;
        this.coordinatesParser = coordinatesParser;
        this.musicGenreParser = musicGenreParser;
    }

    
    @Override
    public MusicBand read(String promptPrefix) throws IOException {
        return read(promptPrefix, -1, new Date());
    }
    
    public MusicBand read(int id, Date creationDate) throws IOException {
        return read("", id, creationDate);
    }

    public MusicBand read(String promptPrefix, int id, Date creationDate) throws IOException {
        return new MusicBand(
                id,
                fieldReader.prompt(promptPrefix, nameInfo),
                coordinatesParser.read(promptPrefix + "coordinates."),
                fieldReader.prompt(promptPrefix, numberOfParticipantsInfo),
                fieldReader.prompt(promptPrefix, albumCountInfo),
                fieldReader.prompt(promptPrefix, establishmentDateInfo),
                creationDate,
                musicGenreParser.read(promptPrefix + "music."),
                labelParser.read(promptPrefix + "label."));
    }

    static StringFieldInfo nameInfo = new StringFieldInfo("name") {
        @Override
        public void validate(String value) throws ValidationException {
            if (value == null) {
                throw new ValidationException("must not be null");
            }
        }
    };

    static DateFieldInfo establishmentDateInfo = new DateFieldInfo("establishmentDate");

    static IntegerFieldInfo numberOfParticipantsInfo = new IntegerFieldInfo(
            "numberOfParticipants") {
        @Override
        public void validate(Integer value) throws ValidationException {
            super.validate(value);
            if (value == null) {
                throw new ValidationException("must not be null");
            }
            if (value < 0) {
                throw new ValidationException("must be greater than zero");
            }
        }
    };
    static IntegerFieldInfo albumCountInfo = new IntegerFieldInfo("albumCount") {
        @Override
        public void validate(Integer value) throws ValidationException {
            super.validate(value);
            if (value == null) {
                throw new ValidationException("must not be null");
            }
            if (value < 0) {
                throw new ValidationException("must be greater than zero");
            }
        }
    };
}
