package com.mfcms.laba;

// import java.util.Date;

// import com.mfcms.laba.model.objects.Coordinates;
// import com.mfcms.laba.model.objects.Label;
// import com.mfcms.laba.model.objects.MusicBand;
// import com.mfcms.laba.model.objects.MusicGenre;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ConsoleApplication app = new ConsoleApplication();
        app.run();

        // app.getDatabaseService().loadDatabase();
        // app.getMusicBandManager().add(new MusicBand(
        //         app.getMusicBandManager().getNextId(),
        //         "test1",
        //         new Coordinates(1.0f, 2.0f),
        //         4,
        //         5,
        //         new Date(),
        //         new Date(),
        //         MusicGenre.POP,
        //         new Label("ttlabel")));
        // app.getDatabaseService().saveDatabase();
    }
}
