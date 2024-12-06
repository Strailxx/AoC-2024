import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class D6P2 {
    private static int loops = 0;
    private static int length = 130;
    private static char[][] maze = new char[length][length];
    private static int orgGuardPosX = 0;
    private static int orgGuardPosY = 0;
    private static int guardPosX = 0;
    private static int guardPosY = 0;
    private static boolean nav = true;
    private static ArrayList<ArrayList<Integer>> visitedTiles = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<ArrayList<Integer>> confirmedLoopTiles = new ArrayList<ArrayList<Integer>>();

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
                                    orgGuardPosY = i;
                                    orgGuardPosX = j;
                                    guardPosX = orgGuardPosX;
                                    guardPosY = orgGuardPosY;
                                    guardNotFound = false;
                                    ArrayList<Integer> tile = new ArrayList<Integer>();
                                    tile.add(orgGuardPosY);
                                    tile.add(orgGuardPosX);
                                    tile.add(0);
                                    visitedTiles.add(tile);
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

        // Original Nav
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

        ArrayList<ArrayList<Integer>> originalTiles = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> i : visitedTiles) {
            originalTiles.add(i);
        }
        visitedTiles.removeAll(visitedTiles);
        for (ArrayList<Integer> is : originalTiles) {
            int y = is.get(0);
            int x = is.get(1);
            guardPosX = orgGuardPosX;
            guardPosY = orgGuardPosY;
            nav = true;
            int orgLoops = loops;
            if(y == orgGuardPosY && x == orgGuardPosX){
                continue;
            }
            maze[y][x] = '#';
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
            visitedTiles.removeAll(visitedTiles);
            maze[y][x] = '.';
            ArrayList<Integer> loopTile = new ArrayList<Integer>();
            if(loops > orgLoops){
                loopTile.add(x);
                loopTile.add(y);
                if(confirmedLoopTiles.contains(loopTile)){
                    loops--;
                }
                else{
                    confirmedLoopTiles.add(loopTile);
                }
                
            }
        }
        System.out.println(loops);
    }

    // 0 = UP | 1 = RIGHT | 2 = DOWN | 3 = LEFT
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
                maze[i][guardPosX] = 'O';
                ArrayList<Integer> tile = new ArrayList<Integer>();
                tile.add(i);
                tile.add(guardPosX);
                tile.add(0);
                if(!visitedTiles.contains(tile)){
                    visitedTiles.add(tile);
                }
                else{
                    loops++;
                    nav = false;
                    return;
                }
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
                maze[guardPosY][i] = 'O';
                ArrayList<Integer> tile = new ArrayList<Integer>();
                tile.add(guardPosY);
                tile.add(i);
                tile.add(1);
                if(!visitedTiles.contains(tile)){
                    visitedTiles.add(tile);
                }
                else{
                    loops++;
                    nav = false;
                    return;
                }
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
                maze[i][guardPosX] = 'O';
                ArrayList<Integer> tile = new ArrayList<Integer>();
                tile.add(i);
                tile.add(guardPosX);
                tile.add(2);
                if(!visitedTiles.contains(tile)){
                    visitedTiles.add(tile);
                }
                else{
                    loops++;
                    nav = false;
                    return;
                }
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
                maze[guardPosY][i] = 'O';
                ArrayList<Integer> tile = new ArrayList<Integer>();
                tile.add(guardPosY);
                tile.add(i);
                tile.add(3);
                if(!visitedTiles.contains(tile)){
                    visitedTiles.add(tile);
                }
                else{
                    loops++;
                    nav = false;
                    return;
                }
            }
        }
    }
}
