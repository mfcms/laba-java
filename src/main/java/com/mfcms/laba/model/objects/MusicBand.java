package com.mfcms.laba.model.objects;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MusicBand {
    // Поле не может быть null, Значение поля должно быть больше 0, Значение этого
    // поля должно быть уникальным, Значение этого поля должно генерироваться
    // автоматически
    private Integer id;

    // Поле не может быть null, Строка не может быть пустой
    private String name;

    // Поле не может быть null
    private Coordinates coordinates;

    // Поле не может быть null, Значение этого поля должно генерироваться
    // автоматически
    private Date creationDate;

    // Значение поля должно быть больше 0
    private int numberOfParticipants;

    // Значение поля должно быть больше 0
    private int albumsCount;

    // Поле может быть null
    private Date establishmentDate;

    // Поле не может быть null
    private MusicGenre genre;

    // Поле может быть null
    private Label label;

    /**
     * Only for JAXB bindings.
     */
    private MusicBand() {
        this.id = -1;
        this.name = null;
        this.coordinates = null;
        this.creationDate = null;
        this.numberOfParticipants = -1;
        this.albumsCount = -1;
        this.establishmentDate = null;
        this.genre = null;
        this.label = null;
    }

    public MusicBand(int id, String name, Coordinates coordinates, int numberOfParticipants,
            int albumsCount, Date establishmentDate, Date creationDate, MusicGenre genre, Label label) {
        // assert id != null && id > 0;
        assert name != null && !name.isEmpty() : "name must not be null";
        assert coordinates != null : "coordinates must not be null";
        // assert creationDate != null;
        assert numberOfParticipants > 0 : "numberOfParticipants must be greater than 0";
        assert albumsCount > 0 : "albumsCount must be greater than 0";
        assert establishmentDate != null : "establishmentDate must not be null";
        assert genre != null : "genre must not be null";
        assert label != null : "label must not be null";
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.albumsCount = albumsCount;
        this.establishmentDate = establishmentDate;
        this.genre = genre;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public int getAlbumsCount() {
        return albumsCount;
    }

    public java.util.Date getEstablishmentDate() {
        return establishmentDate;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Label getLabel() {
        return label;
    }
}
