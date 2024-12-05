import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class D5P1 {
    private static ArrayList<String> ruleFollowers = new ArrayList<String>();
    private static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    private static int sum = 0;
    public static void main(String[] args) {
        
        try{
            File input = new File("inputs/D5input.txt");
            Scanner reader = new Scanner(input);
            boolean readingRules = true;
            
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                System.out.println(line);
                if(line == ""){
                    readingRules = false;
                    continue;
                }
                if(readingRules){
                    addRule(line);
                }
                else{
                    if(checkLine(line)){
                        ruleFollowers.add(line);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        for (String s: ruleFollowers) {
            sum += findMiddle(s);
        }
        System.out.println(sum);
    }

    private static void addRule(String rule){
        String[] split = rule.split("\\|");
        String mainNum = split[0];
        String ruleNum = split[1];

        if(map.get(mainNum) != null){
            map.get(mainNum).add(ruleNum);
        }
        else{
            ArrayList<String> tempList = new ArrayList<String>();
            tempList.add(ruleNum);
            map.put(mainNum, tempList);
        }
    }

    private static boolean checkLine(String line){
        String[] check = line.split(",");
        for (int i = check.length-1; i > 0; i--) {
            ArrayList<String> rules = map.get(check[i]);
            for (int j = i-1; j >= 0; j--) {
                if(rules.contains(check[j])){
                    return false;
                }
            }
        }
        return true;
    }

    private static int findMiddle(String line){
        String[] split = line.split(",");
        int mid = split.length/2;
        return Integer.parseInt(split[mid]);
    }
}
