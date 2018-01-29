package Model.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataAccess {

    private File file_words;
    private File file_letters;

    public DataAccess (String file_words, String file_letters){
        this.file_words = new File(file_words);
        this.file_letters = new File(file_letters);
    }

    ArrayList<Character> readFileAsChars(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Character> arrayList = new ArrayList<>();
        int c;
        while((c = br.read()) != -1){
            arrayList.add((char)c);
        }
        br.close();
        return arrayList;
    }


    public ArrayList<Character> getFileLetters() {

        ArrayList <Character> arrayList = null;
        try {
            arrayList = readFileAsChars(file_letters);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
