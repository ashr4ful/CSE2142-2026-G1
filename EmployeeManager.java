
import java.io.*;
import java.util.*;

// File Name: EmployeeManager.java
public class EmployeeManager {

    public static void main(String[] args) {

        // Display all employees stored in the file.
        if (args[0].equals("l")) {

            System.out.println("Loading data ...");

            try {

                String[] employees = readFile().split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            }

            System.out.println("Data Loaded.");
        // Select and display a random employee.
        } else if (args[0].equals("s")) {

            System.out.println("Loading data ...");

            try {
                String l = readFile();
                System.out.println(l);

                String e[] = l.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(e.length);

                System.out.println(e[idx]);

            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");
        // Add a new employee to the file.
        } else if (args[0].contains("+")) {

            System.out.println("Loading data ...");

            try {

                writeFile(", " + args[0].substring(1), true);

            } catch (IOException exception) {
                exception.printStackTrace();
            }

            System.out.println("Data Loaded.");
        // Search for an employee by name.
        } else if (args[0].contains("?")) {

            System.out.println("Loading data ...");

            try {
                String e[] = readFile().split(",");
                boolean found = false;
                String s = args[0].substring(1);

                for (int i = 0; i < e.length && !found; i++) {
                    if (e[i].equals(s)) {
                        found = true;
                    }
                }

                if (found) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }

            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");
        // Count the words in the employee file.
        } else if (args[0].contains("c")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();

                int wordCount = line.trim().split("\\s+").length;

                System.out.println("Word count: " + wordCount);

            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");
        // Update an existing employee's name.
        } else if (args[0].contains("u")) {

            System.out.println("Loading data ...");

            try {
                String e[] = readFile().split(",");
                String n = args[0].substring(1);

                for (int i = 0; i < e.length; i++) {

                    if (e[i].equals(n)) {
                        e[i] = "Updated";
                    }
                }

                writeFile(String.join(",", e), false);

            } catch (IOException exception) {
                exception.printStackTrace();
            }

            System.out.println("Data Updated.");
        // Delete an employee from the file.
        } else if (args[0].contains("d")) {

            System.out.println("Loading data ...");

            try {
                String e[] = readFile().split(",");
                String n = args[0].substring(1);

                List<String> list = new ArrayList<>(Arrays.asList(e));
                list.remove(n);

                writeFile(String.join(",", list), false);

            } catch (IOException exception) {
                exception.printStackTrace();
            }


            System.out.println("Data Deleted.");
        // Display an error message for invalid commands.
        } else {
            System.out.println("Invalid argument!");
            System.out.println("Valid commands: l, s, +Name, ?Name, c, uName, dName");
        }
    }

    // Reads and returns the employee data from the file.
    private static String readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(
            new FileReader(Constants.EMPLOYEE_FILE))) {
                return reader.readLine();
        }
    }


    // Writes data to the file, either appending or replacing its contents.
    private static void writeFile(String data, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(Constants.EMPLOYEE_FILE, append))) {
                writer.write(data);
        }
    }
}
