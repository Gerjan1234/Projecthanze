package backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * class Filereader
 * @author (Gerjan)
 * @version (09-08-2019)
 */

public class Filereader {

    //private HashMap<String, Boolean> databaseregelsinhoud;
    private ArrayList<HashMap> databaseregels;
    private String karakter_scheidingsteken;
    private HashMap<Integer, String> karakers;
    private String operatingSystem = OsCheck.OS;
    private String location = OsCheck.LocalPath;
    private String filenameAndextensie = "";
    private ResponseEntity returndata;

    /**
     * Constructor voor objects van class Filereader
     * aanmaken arrayList en methode starten
     */
    public Filereader()
    {
        //databaseregelsinhoud = new HashMap<String, Boolean>();
        databaseregels = new ArrayList<>();
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
      * controle of de regels voldoen aan het formaat
     */

    public ArrayList CheckfileAllLinesandTabs() {
        Path fileLocation = Paths.get(location, filenameAndextensie);
        System.out.println("file wordt gecontroleerd");
        Charset charset = Charset.forName("ISO-8859-1");
        employees importdata = new employees();

        try {
            List<String> lines = Files.readAllLines(fileLocation, charset);
            String test[] = null;
            Iterator it = lines.iterator();
            it.next();
                while(it.hasNext()) {
                    String line = (String)it.next();
                System.out.println(line);
                test = line.split(karakter_scheidingsteken);
                  //  if (test.length == 10) {
                        for (int j = 0; j < test.length; j++) {
                            HashMap<String, Boolean> testhas = new HashMap<>();
                            switch(j) {
                                case 0: //double
                                case 1:
                                try {
                                    Double.parseDouble(test[j]);
                                    testhas.put(test[j], true);
                                } catch (NumberFormatException e) {
                                    testhas.put(test[j], false);
                                }
                                break;
                                case 4:
                                case 9: //date
                                    try {
                                        new SimpleDateFormat("dd/MM/yyyy").parse(test[j]);
                                        testhas.put(test[j], true);
                                    } catch (Exception e) {
                                        testhas.put(test[j], false);
                                    }
                                    break;
                                case 7:  //int
                                    try {
                                        Integer.parseInt(test[j]);
                                        testhas.put(test[j], true);
                                    } catch (NumberFormatException e) {
                                        testhas.put(test[j], false);
                                    }
                                    break;
                            }
                            databaseregels.add(testhas);
                           // System.out.println(testhas);
                        }
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return databaseregels;
    }

    public void uploadallowed(){


                 /*       importdata.socialsecurity_id = Double.parseDouble(test[0]);
                        importdata.employer_id = Double.parseDouble(test[1]);
                        importdata.first_name = test[2];
                        importdata.last_name = test[3];
                        importdata.date_of_birth = new SimpleDateFormat("dd/MM/yyyy").parse(test[3]);
                        importdata.status = test[5];
                        importdata.gender = test[6];
                        importdata.adress_id =Integer.parseInt(test[7]);
                        importdata.communication_type = test[8];
                        importdata.date_of_birth = new SimpleDateFormat("dd/MM/yyyy").parse(test[9]);*/
        //  } catch (IOException e) {


        //  databaseregelsinhoud.add(test[j]);
        // databaseregelsinhoud.removeIf(String -> String.charAt(0) == '#');
        //  }

    }

    /**
     * Methode om de losse regels uit het repo bestand weer te geven.
     *
     * @param Arraynummber input voor regel nummer uit het repo bestand (zonder de '#')
     * @return    deze method geeft eens String terug (zin uit de txt file)
     */
    public void printdata(){
        System.out.println(databaseregels.size());
       // System.out.println(databaseregelsinhoud.size());
       // for(int i =0; i<databaseregelsinhoud.size(); i++) {
       //     System.out.println(databaseregelsinhoud.get(i));
      //  }
    }

    public ArrayList FileUpload(MultipartFile file, int scheidingsteken, String filename) {
        try {
            karakter_scheidingsteken = karakers.get(scheidingsteken);
            System.out.println("Besturingssysteem: " + operatingSystem);
            System.out.println("Opslaglocatie    : " + location);
            this.filenameAndextensie = filename;
           File convertFile = new File(location + filenameAndextensie);
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
            CheckfileAllLinesandTabs();
        } catch (Exception e) {
            System.out.println(e);
        }
        return databaseregels;
    }

    public static void Filereader() {
    }
}

