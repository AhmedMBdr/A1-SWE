import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
public class food {
    HashMap<String, Integer> foodCalories = new HashMap<String, Integer>();
    File foodFile = new File("Food.txt");
    public String addFood(Scanner inp) {
        System.out.println("Enter the name of the food:");
        String name = inp.nextLine();
        if(name.isEmpty()){
            name = inp.nextLine();
        }
        while(name.startsWith(" ")){
            name = name.substring(1);
        }
        String[] nameSplit = name.split(" ");
        for(int i = 0; i < nameSplit.length; i++){
            nameSplit[i] = nameSplit[i].substring(0,1).toUpperCase() + nameSplit[i].substring(1).toLowerCase();
        }
        String newName = String.join(" ", nameSplit);
        if(foodCalories.containsKey(name) || foodCalories.containsKey(newName)){
            System.out.println(newName + " already exists in the food list with " + foodCalories.get(newName) + " calories per 100 grams.");
            return newName;
        }
        
        System.out.println("Enter the calories in 100 grams of " + newName + ":");
        int calories = inp.nextInt();
        foodCalories.put(newName, calories);
        saveFood();
        System.out.println(newName + " has been added to the food list.");
        return newName;
    }
    public food(){
        foodCalories.put("Chicken Breast", 165);
        foodCalories.put("Turkey Breast", 104);
        foodCalories.put("Beef Lean", 169);
        foodCalories.put("Salmon", 230);
        foodCalories.put("Tuna", 116);
        foodCalories.put("Shrimp", 99);
        foodCalories.put("Egg", 155);
        foodCalories.put("Tofu", 76);
        foodCalories.put("Lentils", 94);

        foodCalories.put("Milk Whole", 61);
        foodCalories.put("Milk Skim", 34);
        foodCalories.put("Greek Yogurt", 97);
        foodCalories.put("Yogurt", 59);
        foodCalories.put("Cheddar Cheese", 402);
        foodCalories.put("Mozzarella Cheese", 280);
        foodCalories.put("Butter", 717);
        foodCalories.put("Ice Cream", 207);

        foodCalories.put("White Rice", 130);
        foodCalories.put("Brown Rice", 132);
        foodCalories.put("Pasta", 157);
        foodCalories.put("Bread White", 265);
        foodCalories.put("Bread Wholemeal", 217);
        foodCalories.put("Oats", 389);
        foodCalories.put("Quinoa", 111);

        foodCalories.put("Broccoli", 34);
        foodCalories.put("Carrot", 41);
        foodCalories.put("Tomato", 18);
        foodCalories.put("Cucumber", 15);
        foodCalories.put("Spinach", 23);
        foodCalories.put("Mushrooms", 22);
        foodCalories.put("Peas", 81);

        foodCalories.put("Apple", 52);
        foodCalories.put("Banana", 89);
        foodCalories.put("Orange", 47);
        foodCalories.put("Grapes", 69);
        foodCalories.put("Strawberry", 32);
        foodCalories.put("Watermelon", 30);

        foodCalories.put("Almonds", 579);
        foodCalories.put("Peanuts", 567);
        foodCalories.put("Walnuts", 654);
        foodCalories.put("Sunflower Seeds", 584);
        foodCalories.put("Pumpkin Seeds", 574);
        foodCalories.put("Chia Seeds", 486);
        foodCalories.put("Flax Seeds", 534);

        foodCalories.put("Olive Oil", 884);
        foodCalories.put("Vegetable Oil", 884);
        foodCalories.put("Coconut Oil", 862);
        foodCalories.put("Ghee", 890);

        foodCalories.put("Milk Chocolate", 535);
        foodCalories.put("Dark Chocolate", 546);
        foodCalories.put("Sugar", 387);
        foodCalories.put("Honey", 304);
        foodCalories.put("Cookies", 490);
        foodCalories.put("Chips", 536);

        foodCalories.put("Orange Juice", 45);
        foodCalories.put("Apple Juice", 46);
        foodCalories.put("Cola", 42);
        foodCalories.put("Coffee", 2);
        foodCalories.put("Tea", 2);
        loadFood();
    }
    public int search(String foodName){
        while(foodName.startsWith(" ")){
            foodName = foodName.substring(1);
        }
        String[] foodNameSplit = foodName.split(" ");
                for(int i = 0; i < foodNameSplit.length; i++){
                    foodNameSplit[i] = foodNameSplit[i].substring(0,1).toUpperCase() + foodNameSplit[i].substring(1).toLowerCase();
                }
                foodName = String.join(" ", foodNameSplit);
                if(foodCalories.containsKey(foodName)){
                    return foodCalories.get(foodName);
            }
            else {
                return -1;
            }
            
    }
    public List<String> searchSuggestions(String foodName){
        List<String> suggestions = new ArrayList<String>();
        for(String key : foodCalories.keySet()){
            if(key.toLowerCase().contains(foodName.toLowerCase())){
                suggestions.add(key);
            }
        }
        return suggestions;
    }
    public void saveFood() {
        try {
            FileWriter foodWriter = new FileWriter("Food.txt");
            for (String key : foodCalories.keySet()) {
                foodWriter.write(key + ": " + foodCalories.get(key) + "\n");
            }
            foodWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void viewFood() {
        int i = 1;
        for (String key : foodCalories.keySet()) {
            System.out.println(i + ". " + key + ": " + foodCalories.get(key) + " Calories");
            i++;
        }
    }
    public void loadFood() {
        try {
            if (!foodFile.createNewFile()) {
                Scanner fileScanner = new Scanner(foodFile);
                while (fileScanner.hasNextLine()) {
                    String[] line = fileScanner.nextLine().split(": ");
                    if (line.length == 2) {
                        foodCalories.put(line[0], Integer.parseInt(line[1]));
                    }
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
