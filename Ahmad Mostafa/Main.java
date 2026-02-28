import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        food food = new food();
        Goal userGoal = new Goal(inp);
        weeks week = new weeks();
        day day = new day();
        int currentCaloriesDay = 0 ;
        while(true){
            System.out.println("Day : " + (day.dayCalories.size() + 1));
            System.out.printf("Goal Calories: %f\n" ,(float) userGoal.calorieIntake + userGoal.caloriesToBurn);
            if(userGoal.calorieIntake + userGoal.caloriesToBurn - currentCaloriesDay == 0){
                System.out.println("You have reached your goal! Congratulations!");
            }
            else if (userGoal.calorieIntake + userGoal.caloriesToBurn < currentCaloriesDay){
                System.out.println("You have exceeded your goal by " + (currentCaloriesDay - (userGoal.calorieIntake + userGoal.caloriesToBurn)) + " calories. Consider eating less or exercising more.");
            }
            System.out.printf("Current Calories: %d\n", currentCaloriesDay);
            System.out.println("1. Enter Food");
            System.out.println("2. Add Food to List");
            System.out.println("3. View Food List");
            System.out.println("4. Next Day");
            System.out.println("5. View Days");
            System.out.println("6. View Weeks");
            System.out.println("7. Edit Details");
            System.out.println("8. Exit");
            String choice;
            choice = inp.next();
             if(choice.isEmpty()){
                choice = inp.next();
            }
            if(choice.equals("1")){
                while(true){
                System.out.println("Enter the name of the food:");
                String foodName = inp.nextLine();
                if(foodName.isEmpty()){
                    foodName = inp.nextLine();
                }
                int isCaloriesThere = food.search(foodName);
                if(isCaloriesThere != -1){
                    System.out.println("How many grams of " + foodName + " did you eat?");
                    int grams = inp.nextInt();
                    while(grams < 0){
                        System.out.println("Please enter a valid amount of grams.");
                        grams = inp.nextInt();
                    }
                    int calories = (isCaloriesThere * grams) / 100;
                    currentCaloriesDay += calories;
                    break;
            }
            else {
                    List<String> suggestions = new ArrayList<String>();
                    suggestions = food.searchSuggestions(foodName);
                    if(suggestions.size() > 0){
                        System.out.println("Food not found. Did you mean:");
                        int i = 1;
                        for(String suggestion : suggestions){
                            System.out.println(i + ". " + suggestion);
                            i++;
                        }
                    }
                    else {
                        System.out.println("Food not found.\n 1. Add Food \n 2. Try Again \n 3. View Food List \n 4. Exit");
                        int subChoice = inp.nextInt();
                        if(subChoice == 1){
                            String newFoodName = food.addFood(inp);
                            System.out.println("How many grams of " + newFoodName + " did you eat?");
                            int grams = inp.nextInt();
                            while(grams < 0){
                                System.out.println("Please enter a valid amount of grams.");
                                grams = inp.nextInt();
                            }
                            currentCaloriesDay += (food.foodCalories.get(newFoodName) * grams) / 100;
                            break;
                        }
                        else if(subChoice == 2){
                            continue;
                        }
                        else if(subChoice == 3){
                            food.viewFood();
                        }
                        else if(subChoice == 4){
                            break;
                        }
                        else {
                            System.out.println("Invalid choice, try again.");
                        }
                    }
            }
        }
    }
            else if(choice.equals("2")){
                food.addFood(inp);
            }
            else if(choice.equals("3")){
                System.out.println("Food per 100 grams:");
                food.viewFood();
            }
            else if(choice.equals("4")){
                day.addDay(day.dayCalories.size() + 1, currentCaloriesDay);
                currentCaloriesDay = 0;
                if (day.dayCalories.size() % 7 == 0) {
                    int totalCaloriesWeek = 0;
                    int count = 0;
                    for (String key : day.dayCalories.descendingMap().keySet()) {
                        totalCaloriesWeek += day.dayCalories.get(key);
                        count++;
                        if (count == 7) {
                            break;
                        }
                    }
                    week.addWeek("Week " + (week.weekCalories.size() + 1), totalCaloriesWeek);
                }
                
            }
            else if(choice.equals("5")){
                day.viewDay();
            }
            else if(choice.equals("6")){
                week.viewWeek(userGoal.calorieIntake);
                inp.nextLine();
                System.out.println("Press Enter to continue...");
                inp.nextLine();
            }
            else if(choice.equals("7")){
               userGoal.editGoal(inp);
            }
            else if(choice.equals("8")){
                break;
            }
             else {
                System.out.println("Invalid choice, try again.");
            }
        }
        inp.close();
    }
}