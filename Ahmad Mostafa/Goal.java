import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Goal {
    public int calorieIntake = 0;
    public int goal = 0;
    public float kg = 0;
    public final float fat = 7700 / 7;
    public float caloriesToBurn = 0;
    public String welcome = "Welcome to the Calorie Tracker! This program allows you to track your daily calorie intake by entering the foods you eat. You can also view a list of common foods and their calorie counts. To get started, simply enter the name of a food item and its calorie count when prompted. You can also type 'list' to see the available foods and their calories. Happy tracking!";
    File goalFile = new File("Goal.txt");
    public Goal(Scanner inp){
        if(goalFile.exists()){
            loadGoal();
        }
        else {
            saveGoal(inp);
        }
        System.out.println(welcome);
    }
    public void saveGoal(Scanner inp) {
        try {
            if (goalFile.createNewFile()) {
                FileWriter goalWriter = new FileWriter("Goal.txt");

                System.out.println("Please Enter Your Calorie Intake:");
                calorieIntake = inp.nextInt();
                goalWriter.write("Calorie Intake: " + calorieIntake);
                System.out.println(
                        "Great! Your calorie intake has been recorded. \n Now what is your goal? \n 1. Lose Weight \n 2. Maintain Weight \n 3. Gain Weight");
                goal = inp.nextInt();

                while (true) {
                    if (goal == 1) {
                        goalWriter.write("\nGoal: Lose Weight");
                    } else if (goal == 2) {
                        goalWriter.write("\nGoal: Maintain Weight");
                    } else if (goal == 3) {
                        goalWriter.write("\nGoal: Gain Weight");
                    } else {
                        System.out.println("Invalid Input. Please enter 1, 2, or 3.");
                        goal = inp.nextInt();

                        continue;
                    }
                    break;
                }
                if (goal == 1 || goal == 3) {
                    System.out.println("Your goal has been recorded. How many KG do you want to "
                            + (goal == 1 ? "lose" : "gain") + "?\n" + "Note that"
                            + (goal == 1 ? " losing " : " gaining ") + "1 KG in a week is unhealthy.");
                    kg = inp.nextFloat();

                    while (kg > 1) {
                        System.out.println(
                                "Please enter a healthy amount of KG to " + (goal == 1 ? "lose" : "gain") + ".");
                        kg = inp.nextFloat();

                    }
                    goalWriter.write("\nKG to " + (goal == 1 ? "lose: " : "gain: ") + kg);
                }
                goalWriter.close();
                caloriesToBurn = kg * fat;
                if (goal == 1) {
                    caloriesToBurn = -caloriesToBurn;
                } else if (goal == 2) {
                    caloriesToBurn = 0;
                }
            } else {
                welcome = "Welcome back to the Calorie Tracker! This program allows you to track your daily calorie intake by entering the foods you eat. You can also view a list of common foods and their calorie counts. To get started, simply enter the name of a food item and its calorie count when prompted. You can also type 'list' to see the available foods and their calories. Happy tracking!";
                loadGoal();

            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void loadGoal() {
        try (Scanner fileScanner = new Scanner(goalFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith("Calorie Intake: ")) {
                    calorieIntake = Integer.parseInt(line.substring(16));
                } else if (line.startsWith("Goal: ")) {
                    String goalStr = line.substring(6);
                    if (goalStr.equals("Lose Weight")) {
                        goal = 1;
                    } else if (goalStr.equals("Maintain Weight")) {
                        goal = 2;
                    } else if (goalStr.equals("Gain Weight")) {
                        goal = 3;
                    }
                } else if (line.startsWith("KG to lose: ")) {
                    kg = Float.parseFloat(line.substring(12));
                } else if (line.startsWith("KG to gain: ")) {
                    kg = Float.parseFloat(line.substring(12));
                }
                caloriesToBurn = kg * fat;
                if (goal == 1) {
                    caloriesToBurn = -caloriesToBurn;
                } else if (goal == 2) {
                    caloriesToBurn = 0;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editGoal(Scanner inp) {
        System.out.println("What is your new calorie intake? \n (Current: " + calorieIntake + ")");
        calorieIntake = inp.nextInt();
        System.out.println(
                "What is your new goal? \n 1. Lose Weight \n 2. Maintain Weight \n 3. Gain Weight \n (Current: "
                        + (goal == 1 ? "Lose Weight" : goal == 2 ? "Maintain Weight" : "Gain Weight") + ")");
        goal = inp.nextInt();
        
        if(goal != 2){
        System.out.println("What is your new KG to " + (goal == 1 ? "lose" : goal == 3 ? "gain" : "maintain")
                + "? \n (Current: " + kg + ")");
        kg = inp.nextFloat();


        while (kg > 1) {
            System.out.println("Please enter a healthy amount of KG to "
                    + (goal == 1 ? "lose" : goal == 3 ? "gain" : "maintain") + ".");
            kg = inp.nextFloat();

        }
    }
        caloriesToBurn = kg * fat;
        if (goal == 1) {
            caloriesToBurn = -caloriesToBurn;
        } else if (goal == 2) {
            caloriesToBurn = 0;
        }

        try {
            goalFile.delete();
            goalFile.createNewFile();
            FileWriter goalWriter = new FileWriter("Goal.txt");
            goalWriter.write("Calorie Intake: " + calorieIntake);
            if (goal == 1) {
                goalWriter.write("\nGoal: Lose Weight");
                goalWriter.write("\nKG to lose: " + kg);
            } else if (goal == 2) {
                goalWriter.write("\nGoal: Maintain Weight");
            } else if (goal == 3) {
                goalWriter.write("\nGoal: Gain Weight");
                goalWriter.write("\nKG to gain: " + kg);
            }
            goalWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

