import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class D1P2 {
     public static void main(String[] args){
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        ArrayList<Integer> diff = new ArrayList<Integer>();
        try{
        File input = new File("inputs/D1input.txt");
        Scanner reader = new Scanner(input);
            while(reader.hasNextLine()){
                left.add(reader.nextInt());
                right.add(reader.nextInt());
            }
        reader.close();
        } catch (FileNotFoundException e){
            System.out.println("Error has occured");
            e.printStackTrace();
        }
        left.sort(null);
        right.sort(null);
        for (int i = 0; i < left.size(); i++) {
            int appearances = Collections.frequency(right, left.get(i));
            diff.add(left.get(i) * appearances);
        }
        long diffSum = diff.stream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println(diffSum);
    }
}
