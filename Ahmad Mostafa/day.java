import java.util.TreeMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class day {
    TreeMap<String, Integer> dayCalories = new TreeMap<String, Integer>();
    File dayFile = new File("Day.txt");

    public void addDay(int name, int calories) {
        dayCalories.put(String.valueOf(name), calories);
        saveDay();
    }
    public day(){
        loadDay();
    }

    public void viewDay() {
        int count = 1;
        for (String key : dayCalories.descendingMap().keySet()) {
            System.out.println("Day " + key + ": " + dayCalories.get(key) + " Calories\n");
            count++;
            if (count > 7) {
                break;
            }
        }
    }

    public void loadDay() {
        try {
            if (!dayFile.createNewFile()) {
                Scanner fileScanner = new Scanner(dayFile);
                while (fileScanner.hasNextLine()) {
                    String[] line = fileScanner.nextLine().split(": ");
                    if (line.length == 2) {
                        dayCalories.put(line[0], Integer.parseInt(line[1]));
                    }
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveDay() {
        try {
            FileWriter dayWriter = new FileWriter("Day.txt");
            for (String key : dayCalories.keySet()) {
                dayWriter.write(key + ": " + dayCalories.get(key) + "\n");
            }
            dayWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
