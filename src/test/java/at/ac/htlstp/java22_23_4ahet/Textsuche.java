package at.ac.htlstp.java22_23_4ahet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Textsuche {

    private static final String FAUST= ("data/faust.txt");
    private static final String BIBEL= ("data/bibel02.txt");


    public static void main(String[]args ){
        List<String> data;
        File file =new File(FAUST);

        try{
            data= Files.readAllLines(file.toPath());
            //suche nach Faust
            sucheNachF_T(data);



        } catch (IOException e) {
            System.out.println("Datei konnte nicht gelesen werden");
        }


    }
    /**
     * Text Datei einlesen mit:
     * System.out.println(data.get(0));
     * mit Pattern wird nur nach dem Wort gesucht
     * [Ff] ist eine Menge, es wird nach beiden Schreibweisen gesucht
     *
     * Vorher und nachher darf kein anderer Buchstabe sein mit: [^a-zA-öäüÖÄÜ]
     * vorher nachher kein buchstabe, aber vorher ein Leerzeichen  (^|[^a-zA-öäüÖÄÜ])
     * vorher nachher kein buchstabe und kein Leerzeichen (^|[^a-zA-öäüÖÄÜß])[Ff]aust($|[^a-zA-öäüÖÄÜß])
     */

    public static void sucheNachFaust(List<String> data){
        Pattern p= Pattern.compile("(^|[^a-zA-öäüÖÄÜß])[Ff]aust($|[^a-zA-öäüÖÄÜß])");
        for(String line:data){
            Matcher m= p.matcher(line);
            if (m.find()){
                System.out.println(line);
            }

        }
    }
    public static void sucheNachF_T(List<String> data){
        HashMap<String, Integer> worte =new HashMap<>();
        Pattern p=Pattern.compile("(^|[^a-zA-öäüÖÄÜß]|)(?<ft>[Ff]\\w*[tT])aust($|[^a-zA-öäüÖÄÜß])");
        Matcher m;
        for(String line:data){
            m = p.matcher(line);
            while (m.find()) {
                System.out.println(m.group("ft"));
                line = line.substring(m.end());
            }

        }
    }

}
