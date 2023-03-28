package at.ac.htlstp.java22_23_4ahet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RotHack {
    private static final String OUTPUT = "data/hack.txt";
    private static final int abs= 1+'z'-'a';
    public static void main(String[] args) {
        List<String> data;
        File file = new File(Textsuche.OUTPUT);

        try {
            data = Files.readAllLines(file.toPath());
            char dc = findDominantChar(data);
            int n= dc - 'e';
            if (n<0) n+=abs;
            List<String> dec =Textsuche.rot(data, abs-n);

            Files.write((new File(OUTPUT)).toPath(), dec);

        } catch (IOException e) {
            System.out.println("Datei konnte nicht gelesen werden");
        }

    }

    private static char findDominantChar(List<String> data) {
        HashMap<Character, Integer> cct = new HashMap<>();
        for (String line : data) {
            char[] ca = line.toCharArray();
            for (int i = 0; i < ca.length; i++) {
                char c = Character.toLowerCase(ca[i]);
                if (c >= 'a' && c <= 'z') {
                    if (cct.containsKey(c)) cct.put(c, cct.get(c) + 1);
                    else cct.put(c, 1);
                }
            }

        }
        Character most = null;
        int ct = 0;
        for (char c : cct.keySet())
            if (cct.get(c) > ct) {
                most = c;
                ct = cct.get(c);
            }
        return most;
    }
}

