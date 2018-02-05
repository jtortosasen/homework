package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ProgramaOrdenat {

    public static void main(String[] args) {
        int maxNumber;
        ArrayList<Integer> numbers = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        if(args.length==1){
            maxNumber = Integer.parseInt(args[0]);
            try{
                for(int i = 0; i<maxNumber; i++){
                    numbers.add(Integer.parseInt(br.readLine()));
                    System.out.println(numbers.get(i));
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                Collections.sort(numbers);
                for (Integer number : numbers) {
                    System.out.print(String.valueOf(number).concat(" "));
                }
            }
        }
        else{
            System.err.println("Set only one arg");
        }

    }


}
