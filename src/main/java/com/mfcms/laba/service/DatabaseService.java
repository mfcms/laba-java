package com.mfcms.laba.service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import jakarta.xml.bind.Unmarshaller;

import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.model.objects.MusicBand;

public class DatabaseService {
    private String path;
    private MusicBandManager manager;
    private JAXBContext context;

    public DatabaseService(String dbFilepath, MusicBandManager manager) {
        this.path = dbFilepath;
        this.manager = manager;
        try {
            this.context = JAXBContext.newInstance(MusicBandManager.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void loadDatabase() {
        try {
            FileReader reader = new FileReader(path);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MusicBandManager bands = (MusicBandManager) unmarshaller.unmarshal(reader);
            for (MusicBand band : bands.getMusicBands()) {
                manager.add(band);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveDatabase() {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}