package at.ac.htlstp.java22_23_4ahet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Textsuche {

    public static final String FAUST= ("data/faust.txt");
    private static final String BIBEL= ("data/bibel02.txt");

    public static final String OUTPUT = "data/output.txt";


    public static void main(String[]args ){
        List<String> data;
        File file =new File(FAUST);

        try{
            data= Files.readAllLines(file.toPath());
            //suche nach Faust
            //suche nach F_T(data);
            //ersetze(data);
            //int worte=wortecounter(data);
            //System.out.println("Der Text hat" +worte);
            List<String> enc= rot(data,13);

            Files.write((new File(OUTPUT)).toPath(),data);



        } catch (IOException e) {
            System.out.println("Datei konnte nicht gelesen werden");
        }

    }
    private static final int abs= 1+'z'-'a';

    public static  char rot(char c, int n){
        if(c>= 'a' && c<='z'){
            c=(char)((c-'a')+n);

        }else if(c>= 'A' && c<='Z'){
            c=(char)(c+n);
        }
        return c;
    }

    private static List<String> rot(List<String> data, int n) {
        List<String> ret= new ArrayList<>();
        for (String line:data){
            char[] ca= line.toCharArray();
            for (int i=0; i<ca.length; i++)
            ca[i]=rot(ca[i],n);
            line=String.copyValueOf(ca);
            ret.add(line);
        }

        return ret;
    }



    public static int wortecounter(List<String> data){
        int ct=0;
        for (String line:data){
          String[] worte=line.split("[ ,\\.;!\\?:\"]+");
          for(String wort:worte)
              if(wort.length()>1)
          ct ++;
        }
        return ct;
    }
    public static void ersetze(List<String> data){
        for (int i=0; i<data.size();i++){
            String line = data.get(i);
            line.replaceAll("[Ft]aust", "Fritz");
                    data.set(i,line);
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
