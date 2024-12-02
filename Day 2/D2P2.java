import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D2P2 {
    public static void main(String[] args) {
        int count = 0;
        try{
            File input = new File("inputs/D2input.txt");
            Scanner reader = new Scanner(input);
                while(reader.hasNextLine()){
                    String[] line = reader.nextLine().split(" ");
                    ArrayList<Integer> nums = new ArrayList<Integer>();
                    
                    for(String s: line){
                        nums.add(Integer.parseInt(s));
                    }

                    for (int skip = 0; skip < nums.size(); skip++) {
                        ArrayList<Integer> skipList = new ArrayList<Integer>();
                        for (int i = 0; i < nums.size(); i++) {
                            if(i != skip){
                                skipList.add(nums.get(i));
                            }
                        }
                        if(!orderCheck(skipList)){
                            continue;
                        }
                        if(!differCheck(skipList)){
                            continue;
                        }
                        count++;
                        break;
                    }
                }
            reader.close();
            } catch (FileNotFoundException e){
                System.out.println("Error has occured");
                e.printStackTrace();
            }
            System.out.println(count);
    }

    private static boolean orderCheck(ArrayList<Integer> nums){
        ArrayList<Integer> ascending = new ArrayList<>(List.copyOf(nums));
        ArrayList<Integer> descending = new ArrayList<>(List.copyOf(nums));
        ascending.sort(null);
        descending.sort((a, b) -> b - a);
        if(ascending.equals(nums) || descending.equals(nums)){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean differCheck(ArrayList<Integer> nums){
        for (int i = 0; i < nums.size()-1; i++) {
            int check = Math.abs(nums.get(i) - nums.get(i+1));
            if(check == 0 || check > 3){
                return false;
            }
        }
        return true;
    }
}
