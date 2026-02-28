import java.util.TreeMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class weeks {
    
    TreeMap<String, Integer> weekCalories = new TreeMap<String, Integer>();
    File weekFile = new File("Week.txt");

    public void addWeek(String name, int calories) {
        weekCalories.put(name, calories);
        saveWeek();
    }
    public weeks(){
        loadWeek();
    }
    public void loadWeek() {
        try {
            if (!weekFile.createNewFile()) {
                Scanner fileScanner = new Scanner(weekFile);
                while (fileScanner.hasNextLine()) {
                    String[] line = fileScanner.nextLine().split(": ");
                    if (line.length == 2) {
                        weekCalories.put(line[0], Integer.parseInt(line[1]));
                    }
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveWeek() {
        try {

            FileWriter weekWriter = new FileWriter("Week.txt");
            for (String key : weekCalories.keySet()) {
                weekWriter.write(key + ": " + weekCalories.get(key) + "\n");
            }
            weekWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void viewWeek(int calorieIntake) {
        for (String key : weekCalories.keySet()) {
            float calorieDiff = weekCalories.get(key) - calorieIntake * 7;
            System.out.println(key + ": " + weekCalories.get(key) + " Calories , you "
                    + (calorieDiff > 0 ? "gained" : "lost") + " " + Math.abs(calorieDiff) / 7700 + " KG\n");
        }
    }
}

    

