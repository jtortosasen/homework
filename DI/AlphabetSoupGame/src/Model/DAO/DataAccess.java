package Model.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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

    private ArrayList<Character> readFileAsChars(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Character> arrayList = new ArrayList<>();
        int c;
        while((c = br.read()) != -1){
            if(c != '\n')
                arrayList.add((char)c);
        }
        br.close();
        return arrayList;
    }


    public ArrayList<Character> getFileLettersAsArray() {

        ArrayList <Character> arrayList = null;
        try {
            arrayList = readFileAsChars(file_letters);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private ArrayList<String> readFileAsString(File file) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> arrayList = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null){
            arrayList.add(line);
        }
        br.close();
        return arrayList;
    }

    public ArrayList<String> getFileWordsAsArray() {
        ArrayList <String> arrayList = null;
        try {
            arrayList = readFileAsString(file_words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public int getRowsLettersFile() {
        int rows = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file_letters));
            while(br.readLine() !=  null)
                rows++;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }
    public int getColsLettersFile() {
        int cols = 0;
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file_letters));
            if((line = br.readLine()) != null){
                cols = line.length();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cols;
    }
}
