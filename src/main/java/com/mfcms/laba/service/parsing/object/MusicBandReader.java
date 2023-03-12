package com.mfcms.laba.service.parsing.object;

import java.io.IOException;
import java.util.Date;

import com.mfcms.laba.exceptions.ValidationException;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.model.objects.Coordinates;
import com.mfcms.laba.model.objects.Label;
import com.mfcms.laba.model.objects.MusicBand;
import com.mfcms.laba.model.objects.MusicGenre;
import com.mfcms.laba.service.parsing.FieldReader;
import com.mfcms.laba.service.parsing.field.DateFieldInfo;
import com.mfcms.laba.service.parsing.field.IntegerFieldInfo;
import com.mfcms.laba.service.parsing.field.StringFieldInfo;

public class MusicBandReader extends ObjectReader<MusicBand> {

    private ObjectReader<Label> labelParser;
    private ObjectReader<Coordinates> coordinatesParser;
    private ObjectReader<MusicGenre> musicGenreParser;
    private MusicBandManager musicBandManager;

    public MusicBandReader(final MusicBandManager musicBandManager, FieldReader fieldReader, ObjectReader<Label> labelParser,
            ObjectReader<Coordinates> coordinatesParser, ObjectReader<MusicGenre> musicGenreParser) {
        super(fieldReader);
        this.musicBandManager = musicBandManager;
        this.labelParser = labelParser;
        this.coordinatesParser = coordinatesParser;
        this.musicGenreParser = musicGenreParser;
    }

    @Override
    public MusicBand read(String promptPrefix) throws IOException {
        return new MusicBand(
                musicBandManager.getNextId(),
                fieldReader.prompt(promptPrefix, nameInfo),
                coordinatesParser.read(promptPrefix + "coordinates."),
                fieldReader.prompt(promptPrefix, numberOfParticipantsInfo),
                fieldReader.prompt(promptPrefix, albumCountInfo),
                fieldReader.prompt(promptPrefix, establishmentDateInfo),
                new Date(),
                musicGenreParser.read(promptPrefix + "music."),
                labelParser.read(promptPrefix + "label.")
        );
    }

    public MusicBand update(String promptPrefix, MusicBand band) throws IOException {
        
        return new MusicBand(
                
                band.getId(),
                fieldReader.prompt(promptPrefix, nameInfo),
                coordinatesParser.read(promptPrefix + "coordinates."),
                fieldReader.prompt(promptPrefix, numberOfParticipantsInfo),
                fieldReader.prompt(promptPrefix, albumCountInfo),
                fieldReader.prompt(promptPrefix, establishmentDateInfo),
                band.getCreationDate(),
                musicGenreParser.read(promptPrefix + "music."),
                labelParser.read(promptPrefix + "label.")
        );
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
