import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class D5P2 {
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
                if(line == ""){
                    readingRules = false;
                    continue;
                }
                if(readingRules){
                    addRule(line);
                }
                else{
                    ArrayList<String> check = checkLine(line);
                    if(check.get(0) == "true"){
                        ruleFollowers.add(check.get(1));
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

    private static ArrayList<String> checkLine(String line){
        String[] temp = line.split(",");
        ArrayList<String> check = new ArrayList<String>();
        String isEdited = "false";
        for (String s: temp) {
            check.add(s);
        }
        for (int i = check.size()-1; i > 0; i--) {
            ArrayList<String> rules = map.get(check.get(i));
            String num = check.get(i);
            if(rules != null){
                for (int j = i-1; j >= 0; j--) {
                    if(rules.contains(check.get(j))){
                        check.remove(num);
                        check.add(j, num);
                        isEdited = "true";
                    }
                }
            }
        }

        ArrayList<String> result = new ArrayList<String>(); 
        if(isEdited == "true"){
            line = String.join(",", check);
            result = checkLine(line);
            result.set(0, "true");
            return result;
        }
        else{
            result.add(isEdited);
            result.add(line);
        }
        return result;
    }

    private static int findMiddle(String line){
        String[] split = line.split(",");
        int mid = split.length/2;
        return Integer.parseInt(split[mid]);
    }
}
