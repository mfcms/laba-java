package com.mfcms.laba.server.database;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.model.Credentials;
import com.mfcms.laba.common.model.SecuredHolder;
import com.mfcms.laba.common.model.objects.Label;
import com.mfcms.laba.common.model.objects.MusicBand;
import com.mfcms.laba.common.model.objects.MusicGenre;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MusicBands")
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicBandManager {
    private Map<Integer, SecuredHolder<MusicBand>> bands = Collections.synchronizedMap(new HashMap<>());
    private Map<String, Credentials> users = Collections.synchronizedMap(new HashMap<>());
    private int nextId = 1;

    public MusicBand get(int id) {
        return bands.get(id).getContent();
    }

    public Collection<SecuredHolder<MusicBand>> collection() {
        return bands.values();
    }

    public int getNextId() {
        return nextId;
    }

    public void authorize(Credentials creds) throws UnauthorizedException {
        if (creds == null) {
            throw new UnauthorizedException("empty credentials");
        }
        Credentials user;
        synchronized (users) {
            user = users.get(creds.getUsername());
        }
        if (user == null) {
            throw new UnauthorizedException("no user with this username");
        }
        if (!user.equals(creds)) {
            throw new UnauthorizedException("invalid credentials");
        }
    }

    public boolean put(Credentials creds, MusicBand band) throws UnauthorizedException {
        synchronized (bands) {
            var old = bands.get(band.getId());
            if (old != null) {
                if (creds.getUsername() != old.getOwnerName()) {
                    throw new UnauthorizedException("Does not belong to your account");
                }
                old.setContent(band);
            }
            if (band.getId() >= nextId) {
                nextId = band.getId() + 1;
            }
            return bands.put(band.getId(), new SecuredHolder<MusicBand>(creds, band)) != null;
        }
    }

    public boolean remove(Credentials creds, int id) throws UnauthorizedException {
        synchronized (bands) {
            var holder = bands.get(id);
            if (creds.getUsername() != holder.getOwnerName()) {
                throw new UnauthorizedException("Does not belong to your account");
            }
            return bands.remove(id) != null;
        }
    }

    public void clear(Credentials creds) {
        synchronized (bands) {
            bands.values().removeIf(bandHolder -> bandHolder.getOwnerName() == creds.getUsername());
        }
    }

    public void removebygenre(Credentials creds, MusicGenre genre) {
        synchronized (bands) {
            bands.values().removeIf(bandHolder -> bandHolder.getOwnerName() == creds.getUsername()
                    && bandHolder.getContent().getGenre() == genre);
        }
    }

    public Optional<Label> getmaxlbl() {
        return bands.values().stream().map(band -> band.getContent().getLabel()).max(Label::compareTo);
    }

    public boolean register(Credentials creds) {
        synchronized (users) {
            if (users.containsKey(creds.getUsername())) {
                return false;
            }
            users.put(creds.getUsername(), creds);
        }
        return true;
    }
}
