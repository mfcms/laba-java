package com.mfcms.laba.server.database;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.mfcms.laba.common.model.Credentials;
import com.mfcms.laba.common.model.SecuredHolder;
import com.mfcms.laba.common.model.objects.MusicBand;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import jakarta.xml.bind.Unmarshaller;

public class DatabaseManager {
    private String path;
    private JAXBContext context;

    public interface DatabasePathProvider {
        String getDatabasePath();
    }

    public DatabaseManager(DatabasePathProvider pathProvider) {
        this.path = pathProvider.getDatabasePath();
        try {
            this.context = JAXBContext.newInstance(MusicBandManager.class,
                    SecuredHolder.class, Credentials.class, MusicBand.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public MusicBandManager loadDatabase() {
        try {
            FileReader reader = new FileReader(path);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MusicBandManager database = (MusicBandManager) unmarshaller.unmarshal(reader);
            reader.close();
            return database;
        } catch (FileNotFoundException e) {
            return new MusicBandManager();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public void saveDatabase(MusicBandManager manager) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            BufferedOutputStream outStream = new BufferedOutputStream(fileOutputStream);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // List<MusicBand> bands = new ArrayList<MusicBand>();
            // manager.getMusicBands().forEach(bands::add);
            // JAXBElement<MusicBand[]> root = new JAXBElement<MusicBand[]>(
            //         new QName("MusicBands"),
            //         MusicBand[].class,
            //         bands.toArray(new MusicBand[bands.size()]));

            marshaller.marshal(manager, outStream);
            outStream.flush();
            outStream.close();
        } catch (FileNotFoundException e) {
            // if the file exists but is a directory rather than a regular file, does not
            // exist but cannot be created, or cannot be opened for any other reason
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}