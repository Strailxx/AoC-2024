import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class D4P2 {
    private static int count = 0;
    private static int length = 140;
    private static char[][] chars = new char[length][length];
    public static void main(String[] args) {
        try {
            File input = new File("inputs/D4input.txt");
            Scanner reader = new Scanner(input);
            
            while(reader.hasNextLine()){
                for (int i = 0; i < length; i++) {
                    chars[i] = reader.nextLine().toCharArray();
                }
            }
            reader.close();

            for(int i = 0; i < length; i++){
                for (int j = 0; j < length; j++) {
                    if(chars[i][j] == 'A'){
                        if(validA(i, j)){
                            checkMAS(i, j);
                        }
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void checkMAS(int i, int j){
        // Top Left, Top Right, Bottom Left, Bottom Right
        if(chars[i+1][j-1] == 'M' && chars[i+1][j+1] == 'S' && chars[i-1][j-1] == 'M' && chars[i-1][j+1] == 'S'){ count++; } // 1
        if(chars[i+1][j-1] == 'M' && chars[i+1][j+1] == 'M' && chars[i-1][j-1] == 'S' && chars[i-1][j+1] == 'S'){ count++; } // 2
        if(chars[i+1][j-1] == 'S' && chars[i+1][j+1] == 'M' && chars[i-1][j-1] == 'S' && chars[i-1][j+1] == 'M'){ count++; } // 3
        if(chars[i+1][j-1] == 'S' && chars[i+1][j+1] == 'S' && chars[i-1][j-1] == 'M' && chars[i-1][j+1] == 'M'){ count++; } // 4
        
    }

    private static boolean validA(int i, int j){
        if(i + 1 < length && i - 1 > -1 && j + 1 < length && j - 1 > -1){
            return true;
        }
        else{
            return false;
        }
    }
}

/* 
 *   1     2     3     4
 *  M S   M M   S M   S S
 *   A     A     A     A
 *  M S   S S   S M   M M
*/