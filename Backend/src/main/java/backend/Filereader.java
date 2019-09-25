package backend;


import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private List<String> lines;
    private Boolean Firststap = true;
    private int regel = 1;
    private Boolean update = false;


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
     * Methode om de losse regels uit het txt te halen..
     */

    public void CheckfileAllLinesandTabs() {
        Path fileLocation = Paths.get(location, filenameAndextensie);
        System.out.println("file wordt gecontroleerd");
        Charset charset = Charset.forName("ISO-8859-1");
        this.senddata = new ArrayList<>();
        try {
            lines = Files.readAllLines(fileLocation, charset);
            Updatecheckdata(lines);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Methode om de losse regels om te zetten naar cellen.
     */

        public void Updatecheckdata(List lines) {
            String test[];
            Iterator it = lines.iterator();
            if (Firststap == true) {
                it.next();
            }
            while (it.hasNext()) {
                String line = (String) it.next();
                test = line.split(karakter_scheidingsteken);
                if (test.length < 10) {
                    update = true;
                }
                for (int j = 0; j < test.length; j++) {
                    dataToSwith(test, j);
                }
            }
        }

    /**
     * Methode voor de swith.
     * controle of de regels voldoen aan het formaat.
     */

                            public ArrayList dataToSwith(String test[], int j) {
                            responsfile test2 = new responsfile();
                            switch(j) {
                                case 0: //double
                                    try {
                                        Double.parseDouble(test[j]);
                                        if(update == true){
                                            try {
                                                test2.goedfout = Database.checkIDorEmplo_id(test[j], "socialsecurity_id");
                                            } catch (Exception e) {
                                                test2.goedfout = false;
                                            }
                                        }else {test2.goedfout = true;}
                                        test2.regel = regel;
                                        test2.type = "socialsecurity_id";
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
                                        if(update == true){
                                        try {
                                         test2.goedfout =  Database.checkIDorEmplo_id(test[j], "employer_id");
                                        } catch (Exception e) {test2.goedfout = false; }
                                    }else {test2.goedfout = true;}
                                        test2.regel = regel;
                                        test2.type = "employer_id";
                                        test2.waarde = test[j];
                                    } catch (NumberFormatException e) {
                                        test2.regel = regel;
                                        test2.type = "employer_id";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "getal_5_cijvers";
                                    }
                                    break;
                                case 2: //double
                                    try {
                                        Double.parseDouble(test[j]);
                                        test2.regel = regel;
                                        test2.type = "invoice_id";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (NumberFormatException e) {
                                        test2.regel = regel;
                                        test2.type = "invoice_id";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "getal";
                                    }
                                    break;
                                case 3: //float
                                    try {
                                        Float.parseFloat(test[j]);
                                        test2.regel = regel;
                                        test2.type = "salary";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (NumberFormatException e) {
                                        test2.regel = regel;
                                        test2.type = "salary";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "getal met punt";
                                    }
                                    break;
                                case 4: //float
                                    try {
                                        Float.parseFloat(test[j]);
                                        test2.regel = regel;
                                        test2.type = "parttime_factor";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (NumberFormatException e) {
                                        test2.regel = regel;
                                        test2.type = "parttime_factor";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "getal met punt";
                                    }
                                    break;
                                case 5: //String
                                    test2.regel = regel;
                                    test2.type = "first_name";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 6: //String
                                    test2.regel = regel;
                                    test2.type = "last_name";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 7: //date
                                    try {
                                        new SimpleDateFormat("yyyy-MM-dd").parse(test[j]);
                                        test2.regel = regel;
                                        test2.type = "date_of_birth";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (Exception e) {
                                        test2.regel = regel;
                                        test2.type = "date_of_birth";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "formaat_yyy-MM-dd";
                                    }
                                    break;
                                case 8: //String
                                    test2.regel = regel;
                                    test2.type = "status";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 9: //String
                                    test2.regel = regel;
                                    test2.type = "gender";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 10: //int
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
                                case 11: //String
                                    test2.regel = regel;
                                    test2.type = "communication_type";
                                    test2.goedfout = true;
                                    test2.waarde = test[j];
                                    break;
                                case 12: //date
                                    try {
                                        new SimpleDateFormat("yyy-MM-dd").parse(test[j]);
                                        test2.regel = regel;
                                        test2.type = "hire_date";
                                        test2.goedfout = true;
                                        test2.waarde = test[j];
                                    } catch (Exception e) {
                                        test2.regel = regel;
                                        test2.type = "hire_date";
                                        test2.goedfout = false;
                                        test2.waarde = test[j];
                                        test2.format = "formaat_yyy-MM-dd";
                                    }
                                    break;
                                default:
                            }

                senddata.add(test2);
                regel++;
        return senddata;
    }

    /**
     * Methode om de file in te laden en begin data vastleggen.
     */

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

    /**
     * Methode om de gewijzigde data weer te controleren.
     */

    public ArrayList checkscheider(List lines, int scheidingsteken){
        karakter_scheidingsteken = karakers.get(scheidingsteken);
        Firststap = false;
        this.senddata = new ArrayList<>();
        Updatecheckdata(lines);
        return senddata;
    }

    /**
     * Methode om de gewijzigde data weer te controleren.
     */
            public ArrayList salarismutatiecheckcontrole(List lines, int scheidingsteken){
            karakter_scheidingsteken = karakers.get(scheidingsteken);
            this.senddata = new ArrayList<>();
            update = true;
            String[] test;
            Iterator it = lines.iterator();
            while (it.hasNext()) {
                String line = (String) it.next();
                test = line.split(karakter_scheidingsteken);
                dataToSwith(test, 0);
                dataToSwith(test, 1);
                dataToSwith(test, 2);
                dataToSwith(test, 3);
                dataToSwith(test, 4);
            }
            return senddata;
        }

}

