package backend;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * class Filereader
 * @author (Gerjan)
 * @version (09-08-2019)
 */

public class Filereader {

    private ArrayList<String> databaseregelsinhoud;
    private ArrayList<String> databaseregels;
    private String karakter_scheidingsteken;
    private HashMap<Integer, String> karakers;

    private String operatingSystem = OsCheck.OS;
    //private String operatingSystem = "";
    //private String location = "";
    private String location = OsCheck.LocalPath;
    private String filenameAndextensie = "";

    /**
     * Constructor voor objects van class Filereader
     * aanmaken arrayList en methode starten
     */
    public Filereader()
    {
        databaseregelsinhoud = new ArrayList<>();
        databaseregels = new ArrayList<>(databaseregelsinhoud);
        karakers = new HashMap<>();
        karakers.put(1,"\t");
        karakers.put(2,",");
        karakers.put(3,".");
        karakers.put(4,":");
        karakers.put(5,";");
        karakers.put(6," ");
    }

     /**
     * Methode om de losse regels uit het txt te halen inc tabs.
     */

    public void CheckfileAllLinesandTabs() {

        //Path fileLocation = Paths.get(LocalPath, "upload.txt"); //test locatie
        // Path fileLocation = Paths.get("/var/tmp/", "upload.txt"); //test locatie
        // voor file moet uit
        //frontend komen

        Path fileLocation = Paths.get(location, filenameAndextensie);

        System.out.println("file wordt gecontroleerd");

        Charset charset = Charset.forName("ISO-8859-1");
        try {
            List<String> lines = Files.readAllLines(fileLocation, charset);
            String test[] = null;
            for (String line : lines) {
                System.out.println(line);
                test = line.split(karakter_scheidingsteken);
                for (int j = 0; j < test.length; j++) {
                    databaseregelsinhoud.add(test[j]);
                    databaseregelsinhoud.removeIf(String -> String.charAt(0) == '#');
                }
                databaseregels.add(line);
            }
        } catch(IOException e){
            System.out.println(e);
        }
    }


    /**
     * Methode om de losse regels uit het repo bestand weer te geven.
     *
     * @param Arraynummber input voor regel nummer uit het repo bestand (zonder de '#')
     * @return    deze method geeft eens String terug (zin uit de txt file)
     */
    public void printdata(){
        System.out.println(databaseregels.size());
        System.out.println(databaseregelsinhoud.size());
        for(int i =0; i<databaseregelsinhoud.size(); i++) {
            System.out.println(databaseregelsinhoud.get(i));
        }
    }

    public String FileUpload(MultipartFile file, int scheidingsteken, String filename) {
        try {
            karakter_scheidingsteken = karakers.get(scheidingsteken);

//            this.operatingSystem = System.getProperty("os.name");

            System.out.println("Besturingssysteem: " + operatingSystem);
            System.out.println("Opslaglocatie    : " + location);
            this.filenameAndextensie = filename;

//            if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {   //locatie op MAC testen
//                //this.location = "/var/tmp/";
//                this.filenameAndextensie = filename;
//            }
//            else if ("Windows".equals(operatingSystem)) {
//                //this.location = "E:/tmp/";
//                this.filenameAndextensie = filename;
//            }
//            else {
//                //this.location = "/var/tmp/";
//                this.filenameAndextensie = filename;
            //}
           File convertFile = new File(location + filenameAndextensie);

            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
            CheckfileAllLinesandTabs();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "File geupload";
    }

    public static void Filereader() {
    }
}

