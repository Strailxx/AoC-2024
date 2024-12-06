import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D6P1 {
    private static int tilesVisited = 0;
    private static int length = 130;
    private static char[][] maze = new char[length][length];
    private static int guardPosX = 0;
    private static int guardPosY = 0;
    private static boolean nav = true;

    public static void main(String[] args) {
        
        try{
            File input = new File("inputs/D6input.txt");
            Scanner reader = new Scanner(input);
            boolean guardNotFound = true;
                while(reader.hasNextLine()){
                    for (int i = 0; i < length; i++) {
                        maze[i] = reader.nextLine().toCharArray();
                        if(guardNotFound){
                            for (int j = 0; j < length; j++) {
                                if (maze[i][j] == '^'){
                                    guardPosY = i;
                                    guardPosX = j;
                                    guardNotFound = false;
                                    tilesVisited++;
                                    maze[i][j] = 'X';
                                }
                            }
                        }
                    }
                }
            reader.close();
        } catch (FileNotFoundException e){
            System.out.println("Error has occured");
            e.printStackTrace();
        }
        
        while(nav){
            if(nav){
                moveUp();
            }
            if(nav){
                moveRight();
            }

            if(nav){
                moveDown();
            }
            if(nav){
                moveLeft();
            }
        }
        System.out.println(tilesVisited);
    }

    private static void moveUp(){
        for (int i = guardPosY-1; i >= -1; i--) {
            if(i == -1){
                nav = false;
                return;
            }
            if(maze[i][guardPosX] == '#'){
                guardPosY = i+1;
                return;
            }
            else if(maze[i][guardPosX] != 'X'){
                maze[i][guardPosX] = 'X';
                tilesVisited++;
            }
        }
    }
    private static void moveRight(){
        for (int i = guardPosX+1; i <= length; i++) {
            if(i == length){
                nav = false;
                return;
            }
            if(maze[guardPosY][i] == '#'){
                guardPosX = i-1;
                return;
            }
            else if(maze[guardPosY][i] != 'X'){
                maze[guardPosY][i] = 'X';
                tilesVisited++;
            }
        }
        
    }
    private static void moveDown(){
        for (int i = guardPosY+1; i <= length; i++) {
            if(i == length){
                nav = false;
                return;
            }
            if(maze[i][guardPosX] == '#'){
                guardPosY = i-1;
                return;
            }
            else if(maze[i][guardPosX] != 'X'){
                maze[i][guardPosX] = 'X';
                tilesVisited++;
            }
        }
    }
    private static void moveLeft(){
        for (int i = guardPosX-1; i >= -1; i--) {
            if(i == -1){
                nav = false;
                return;
            }
            if(maze[guardPosY][i] == '#'){
                guardPosX = i+1;
                return;
            }
            else if(maze[guardPosY][i] != 'X'){
                maze[guardPosY][i] = 'X';
                tilesVisited++;
            }
        }
    }
}
