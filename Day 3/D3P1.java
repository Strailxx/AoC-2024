import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D3P1 {
    public static void main(String[] args) {
        try {
            String input = Files.readString(Paths.get("inputs/D3input.txt"));
            String regex = "(?<=mul\\()\\d{1,3},\\d{1,3}(?=\\))";
            Matcher m = Pattern.compile(regex).matcher(input);

            ArrayList<String> muls = new ArrayList<String>();
            while(m.find()){
                muls.add(m.group(0));
            }

            int sum = 0;

            for(String s : muls){
                String[] split = s.split(",");
                int num = Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
                sum += num;
            }

            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
