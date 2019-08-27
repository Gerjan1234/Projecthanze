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

    private ArrayList<HashMap> databaseregels;
    private ArrayList<responsfile> senddata;
    private String karakter_scheidingsteken;
    private HashMap<Integer, String> karakers;
    private HashMap<String, HashMap> inputdata;
    private String operatingSystem = OsCheck.OS;
    private String location = OsCheck.LocalPath;
    private String filenameAndextensie = "";


    /**
     * Constructor voor objects van class Filereader
     * aanmaken arrayList en methode starten
     */
    public Filereader()
    {
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
        this.senddata = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(fileLocation, charset);
            int regel = 1;
            System.out.println("aantal regels file " + lines.size());
            String test[];
            Iterator it = lines.iterator();
            it.next();
                while(it.hasNext()) {
                    String line = (String)it.next();
                    test = line.split(karakter_scheidingsteken);
                  //  if (test.length == 10) {
                        for (int j = 0; j < test.length; j++) {
                            responsfile test2 = new responsfile();
                            switch(j) {
                                case 0: //double
                                    try {
                                        Double.parseDouble(test[j]);
                                        test2.regel = regel;
                                        test2.type = "socialsecurity_id";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (NumberFormatException e) {
                                        test2.regel = regel;
                                        test2.type = "socialsecurity_id";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "getal_10_cijvers";
                                    }
                                    break;
                                case 1: //double
                                    try {
                                        Double.parseDouble(test[j]);
                                        test2.regel = regel;
                                        test2.type = "employer_id";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (NumberFormatException e) {
                                        test2.regel = regel;
                                        test2.type = "employer_id";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "getal_5_cijvers";
                                    }
                                    break;
                                case 2: //String
                                    test2.regel = regel;
                                    test2.type = "first_name";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 3: //String
                                    test2.regel = regel;
                                    test2.type = "last_name";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 4: //date
                                    try {
                                        new SimpleDateFormat("dd/MM/yyyy").parse(test[j]);
                                        test2.regel = regel;
                                        test2.type = "date_of_birth";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (Exception e) {
                                        test2.regel = regel;
                                        test2.type = "date_of_birth";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "formaat_dd/MM/yyyy";
                                    }
                                    break;
                                case 5: //String
                                    test2.regel = regel;
                                    test2.type = "status";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 6: //String
                                    test2.regel = regel;
                                    test2.type = "gender";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 7: //int
                                    try {
                                        Integer.parseInt(test[j]);
                                        test2.regel = regel;
                                        test2.type = "adress_id";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (Exception e) {
                                        Integer.parseInt(test[j]);
                                        test2.regel = regel;
                                        test2.type = "adress_id";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "getal_4_cijvers";
                                    }
                                    break;
                                case 8: //String
                                    test2.regel = regel;
                                    test2.type = "communication_type";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 9: //date
                                    try {
                                        new SimpleDateFormat("dd/MM/yyyy").parse(test[j]);
                                        test2.regel = regel;
                                        test2.type = "hire_date";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (Exception e) {
                                        test2.regel = regel;
                                        test2.type = "hire_date";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "formaat_dd/MM/yyyy";
                                    }
                                    break;
                                default:

                            }
                            senddata.add(test2);
                        }
                regel++;
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return senddata;
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
        return senddata;
    }

    public static void Filereader() {
    }
}

