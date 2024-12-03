import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D3P2 {
    public static void main(String[] args) {
        try {
            String input = Files.readString(Paths.get("inputs/D3input.txt"));
            input = "do()" + input; // This is stupidy cheeky to get this to work, I don't like it but it works LMAO
            
            String doRegex = "(?<=do\\(\\))(?s).*?(?=don't\\(\\)|\\Z)";
            Matcher doM = Pattern.compile(doRegex).matcher(input);
            ArrayList<String> runs = new ArrayList<String>();
            while(doM.find()){
                runs.add(doM.group(0));
            }

            String mulRegex = "(?<=mul\\()\\d{1,3},\\d{1,3}(?=\\))";
            ArrayList<String> muls = new ArrayList<>();          
            for(String r : runs){
                Matcher mulM = Pattern.compile(mulRegex).matcher(r);
                while(mulM.find()){
                    muls.add(mulM.group(0));
                } 
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
