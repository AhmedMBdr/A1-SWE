import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList; // Dynamic Array (like vector)
import java.io.FileWriter;

public class Main {

  public static void createFile(File tasksFile) {
    try {
      if (tasksFile.createNewFile()) {
        System.out.println("file created\n");
      } else {
        System.out.println("file was here\n");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void readFile(File tasksFile) {
    System.out.println();
    try (Scanner myReader = new Scanner(tasksFile)) {
      int cnt = 1;
      while (myReader.hasNextLine()) {
        System.out.println(cnt++ + "- " + myReader.nextLine());
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    System.out.println();
  }

  public static void writeTask(String text, ArrayList tasks) {
    try {
      FileWriter myWriter = new FileWriter("Tasks.txt");
      for (int i = 0; i < tasks.size(); i++) {
        myWriter.write(tasks.get(i).toString());
        myWriter.write("\n");
      }
      myWriter.write(text);
      if (text != "")
        myWriter.write("\n");
      myWriter.close();
    } catch (

    IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void deleteAll() {
    try {
      FileWriter myWriter = new FileWriter("Tasks.txt");
      myWriter.write("");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String[] choices = { "Add Task", "Read All Tasks", "Update Specific Task", "Delete Specific Task",
        "Delete All Tasks", "Change The Order Of Tasks", "Exit" };
    File tasksFile = new File("Tasks.txt");
    createFile(tasksFile);

    // Preparing tasks Array
    ArrayList<String> tasks = new ArrayList<>();
    try (Scanner myReader = new Scanner(tasksFile)) {
      while (myReader.hasNextLine()) {
        tasks.add(myReader.nextLine());
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    while (true) {
      // Menu
      for (int i = 0; i < choices.length; i++) {
        System.out.println((i + 1) + "- " + choices[i]);
      }
      System.out.print("\nEnter The Number : ");
      int op = input.nextInt();
      input.nextLine(); // consume leftover newline
      if (op == 1) {
        System.out.print("\nEnter The Task : ");
        String task = input.nextLine();
        writeTask(task, tasks);
        if (task != "")
          tasks.add(task);
      } else if (op == 2) {
        readFile(tasksFile);
      } else if (op == 3) {
        if (!tasks.isEmpty()) {
          while (true) {
            readFile(tasksFile);
            System.out.print("Choose The Task To Edit : ");
            op = input.nextInt();
            input.nextLine(); // consume leftover newline
            if (op > 0 && op <= tasks.size()) {
              System.out.print("Enter The Task : ");
              String update = input.nextLine();
              tasks.set(op - 1, update);
              break;
            } else
              System.out.println("Plz Enter Valid Number \n");
          }
          deleteAll();
          writeTask("", tasks);
        } else
          System.out.println("It's Empty\n");
      } else if (op == 4) {
        if (!tasks.isEmpty()) {
          while (true) {
            readFile(tasksFile);
            System.out.print("Choose The Task To Delete : ");
            op = input.nextInt();
            input.nextLine(); // consume leftover newline
            if (op > 0 && op <= tasks.size()) {
              tasks.remove(op - 1);
              System.out.println("Deletion Done \n");
              break;
            } else
              System.out.println("Plz Enter Valid Number \n");
          }
          deleteAll();
          writeTask("", tasks);
        } else
          System.out.println("It's Empty\n");
      } else if (op == 5) {
        deleteAll();
        tasks.clear();
        System.out.println("Delete All Done \n");
      } else if (op == 6) {
        if (tasks.size() > 1) {
          int cnt = 0;
          boolean[] checked = new boolean[tasks.size()];
          String[] temp = new String[tasks.size()];
          while (cnt < tasks.size()) {
            for (int i = 0; i < tasks.size(); i++) {
              if (!checked[i]) {
                System.out.println((i + 1) + "- " + tasks.get(i));
              }
            }
            System.out.println("Enter The Task Number : ");
            op = input.nextInt();
            if (op > 0 && op <= tasks.size() && !checked[op - 1]) {
              temp[cnt++] = tasks.get(op - 1);
              checked[op - 1] = true;
            } else
              System.out.println("Plz Enter Valid Value.");
          }
          for (int i = 0; i < tasks.size(); i++) {
            tasks.set(i, temp[i]);
          }
          deleteAll();
          writeTask("", tasks);
          System.out.println("Reordering Done\n");
        } else
          System.out.println("No Need For Reordering.\n");
      } else if (op == 7) {
        break;
      } else
        System.out.println("Plz Enter Valid Value.");
    }
  }
}