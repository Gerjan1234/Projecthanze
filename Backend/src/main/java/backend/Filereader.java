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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.util.Scanner;

/**
 * class Filereader
 * @author (Gerjan)
 * @version (09-08-2019)
 */

public class Filereader {

   // private int keuze_scheidingsteken;
    private ArrayList<String> repodata;
    private ArrayList<String> databaseregelsinhoud;
    private ArrayList<String> databaseregels;
    private String karakter_scheidingsteken;
    private HashMap<Integer, String> karakers;
    private String OS;
    private String LocalPath;
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
        setPathLocal();
    }

    /**
     * Methode om padnaam te bepalen bij verschillende operatingsystems
     * @author Teo
     * @version (18-08-2019)
     */

    private void setPathLocal() {

        OS = OsCheck.detectedOS.toString();

        String str = OS;
        switch(str)
        {
            case "Windows":
                LocalPath = "E:/tmp/";
                break;
            case "Linux":
                LocalPath = "/var/tmp/";
                break;
            case "MacOS":
                LocalPath = "/"; // Henk pad nog invullen
                break;
            default:
        }
    }

    /**
     * Methode om de losse regels uit het txt te halen inc tabs.
     */

    public void CheckfileAllLinesandTabs() {
        Path fileLocation = Paths.get(LocalPath, "upload.txt"); //test locatie
        // Path fileLocation = Paths.get("/var/tmp/", "upload.txt"); //test locatie
        // voor file moet uit
        //frontend komen
        System.out.println("file wordt gecontroleerd");
        System.out.println(LocalPath);
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
/*
    public void keuze_scheidingsteken() {
        for (int j = 1; j < karakers.size(); j++) {
            System.out.println(j + ": " + karakers.get(j));
            System.out.println("kies karater");
        }
        try {
            Scanner s = new Scanner(System.in);
            keuze_scheidingsteken = s.nextInt();

            switch (keuze_scheidingsteken) {
                case 1:
                    karakter_scheidingsteken = karakers.get(1);
                    break;
                case 2:
                    karakter_scheidingsteken = karakers.get(2);
                    break;
                case 3:
                    karakter_scheidingsteken = karakers.get(3);
                    break;
                case 4:
                    karakter_scheidingsteken = karakers.get(4);
                    break;
                case 5:
                    karakter_scheidingsteken = karakers.get(5);
                    break;
                case 6:
                    karakter_scheidingsteken = karakers.get(6);
                    break;
                default:
                    System.out.println("geen juiste keuze");
                    keuze_scheidingsteken();
            }
            CheckfileAllLinesandTabs();
            printdata();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
*/
    public String FileUpload(MultipartFile file, int scheidingsteken) {
        try {
          ///  File convertFile = new File("/var/tmp/" + file.getOriginalFilename());  //var/temp werkt op linux
            karakter_scheidingsteken = karakers.get(scheidingsteken);
            File convertFile = new File(LocalPath + "upload.txt");
           // File convertFile = new File("/var/tmp/" + "upload.txt");
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

    public static void Filereader() { // main voor het testen //main(String[] args) { //
        Filereader f = new Filereader();
       // f.keuze_scheidingsteken();
    }
}

