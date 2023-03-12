package com.mfcms.laba.model;

import java.util.HashMap;
import java.util.Optional;

import com.mfcms.laba.model.objects.Label;
import com.mfcms.laba.model.objects.MusicBand;
import com.mfcms.laba.model.objects.MusicGenre;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MusicBands")
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicBandManager {
    private HashMap<Integer, MusicBand> bands = new HashMap<>();
    private int nextId = 0;

    public MusicBand get(int id) {
        return bands.get(id);
    }

    public Iterable<MusicBand> getMusicBands() {
        return bands.values();
    }

    public int getNextId() {
        return nextId;
    }

    public boolean add(MusicBand band) {
        if (band.getId() >= nextId) {
            nextId = band.getId() + 1;
        }
        return bands.put(band.getId(), band) != null;
    }

    public boolean update(int id, MusicBand band) {
        return false;
    }

    public boolean remove( int id){
        return bands.remove(id) != null;

    }

    public void clear() {
        bands.clear();
    }

    public void removebygenre(MusicGenre genre){
        bands.values().removeIf(band -> band.getGenre()==genre);
        }

    public Optional<Label> getmaxlbl(){
        return bands.values().stream().map(band -> band.getLabel()).max(Label::compareTo);

    }

}
