import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class D4P1 {
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
                    if(chars[i][j] == 'X'){
                        checkXMAS(i, j);
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void checkXMAS(int i, int j){
        if(j + 3 < length && chars[i][j+1] == 'M' && chars[i][j+2] == 'A' && chars[i][j+3] == 'S') { count++; } // Right
        if(j - 3 > -1 && chars[i][j-1] == 'M' && chars[i][j-2] == 'A' && chars[i][j-3] == 'S') { count++; } // Left
        if(i + 3 < length && chars[i+1][j] == 'M' && chars[i+2][j] == 'A' && chars[i+3][j] == 'S') { count++; } // Down
        if(i - 3 > -1 && chars[i-1][j] == 'M' && chars[i-2][j] == 'A' && chars[i-3][j] == 'S') { count++; } // Up
        if(i + 3 < length && j + 3 < length && chars[i+1][j+1] == 'M' && chars[i+2][j+2] == 'A' && chars[i+3][j+3] == 'S') { count++; } // Down Right
        if(i + 3 < length && j - 3 > -1 && chars[i+1][j-1] == 'M' && chars[i+2][j-2] == 'A' && chars[i+3][j-3] == 'S') { count++; } // Down Left
        if(i - 3 > -1 && j + 3 < length && chars[i-1][j+1] == 'M' && chars[i-2][j+2] == 'A' && chars[i-3][j+3] == 'S') { count++; } // Up Right
        if(i - 3 > -1 && j - 3 > -1 && chars[i-1][j-1] == 'M' && chars[i-2][j-2] == 'A' && chars[i-3][j-3] == 'S') { count++; } // Up Left
    }
}
