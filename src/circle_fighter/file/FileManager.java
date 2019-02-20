package circle_fighter.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static void writeToFile(String filePath, ArrayList<String> text){
        checkGameDirectory();
        File file = new File(System.getenv("APPDATA") + "/Circle Fighter/" + filePath);
        try {
            PrintWriter writer = new PrintWriter(file);
            for(String line : text){
                writer.println(line);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFromFile(String filePath){
        checkGameDirectory();
        File file = new File(System.getenv("APPDATA") + "/Circle Fighter/" + filePath);
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(new FileInputStream(file));
            while (input.hasNextLine()){
                data.add(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            //not swallowed. Will result in an empty arraylist. This would never happen otherwise.
        }
        return data;
    }

    private static void checkGameDirectory(){
        File gameDir = new File(System.getenv("APPDATA") + "/Circle Fighter");
        if(!gameDir.exists()) {
            gameDir.mkdir();
        }
    }
}