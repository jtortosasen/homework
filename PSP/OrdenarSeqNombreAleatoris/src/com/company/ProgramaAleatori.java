package com.company;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class ProgramaAleatori {

    static int minRange = 1000;
    static int maxRange = 2000;

    public static void main(String[] args) {
        int nRandoms;
        char outputCharType;

        if(args.length == 2){
            if(Integer.parseInt(args[0])<=1000){
                nRandoms = Integer.parseInt(args[0]);
                outputCharType = args[1].charAt(0);
                generateRandomNumbers(nRandoms,outputCharType);
            }
            else System.err.println("Max number less or equals than 1000");
        }
        else System.err.println("Invalid args");
    }

    private static void generateRandomNumbers(int nRandoms, char outputCharType){
        switch (outputCharType){
            case 'p':
                generateRandomToScreen(nRandoms);
                break;
            case 'f':
                generateRandomToFile(nRandoms);
                break;
            default:
                System.err.println("Invalid char type");
                break;
        }
    }

    private static void generateRandomToScreen(int nRandoms){
        for(int i = 0; i< nRandoms; i++){
            System.out.print(String.valueOf(randomNumber(minRange,maxRange)).concat(" "));
        }
    }

    private static void generateRandomToFile(int nRandoms){
        File file = new File("aleatoris.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i< nRandoms; i++){
                writer.write(String.valueOf(randomNumber(minRange,maxRange)).concat(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int randomNumber(int minRange, int maxRange){
        int random = ThreadLocalRandom.current().nextInt(minRange, maxRange + 1);
        return random;
    }


}
