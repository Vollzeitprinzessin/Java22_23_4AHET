package at.ac.htlstp.java22_23_4ahet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Vector;

public class Dateien {

    public static void main(String[] args){
        File file =new File("data/test.txt");
        System.out.println(file.getAbsolutePath());
        try {
            file.createNewFile();
            Vector<String> data=new Vector<>();
            data.add("Hallo Welt");
            data.add("Dies ist die zweite Zeile");
            Files.write(file.toPath(),data );

            FileWriter fw= new FileWriter(file, true);
            fw.write("Hallo Welt!\n");
            fw.write("Dies ist die zweite Zeile!\n");
            fw.close();

        } catch (IOException e) {
            System.out.println("Datei konnte nicht angelegt werden");

        }
    }
}
