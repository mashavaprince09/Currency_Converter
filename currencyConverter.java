import java.io.*;
import java.util.*;

public class currencyConverter {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to the Currency Converter!!");
            System.out.println("To use a function, please enter the number associated with it");   
            System.out.println("The deafault base currency is ZAR");      
            System.out.println("To change the base currency, please enter (1)");         
            System.out.println("To get the latest exchange rates, please enter (2)");      
            System.out.println("To get the exchange rates for an older date, please enter (3)");      
            System.out.println("To close the program, please enter (0)"); 
            //(format: 2021-12-31)            
            System.out.println("To get the exchange rates for a range of dates, please enter (4)");
            System.out.print("Enter a number between 0-4: ");
            int function = sc.nextInt();

            if (function==0){
                System.out.println("The program is shutting down...");
                System.exit(0);
            }

            ProcessBuilder pb = new ProcessBuilder("python", "api.py",Integer.toString(function));

            //pb.directory(new File("path/to/your/python/script"));

            //Run api.py
            Process process = pb.start();

            // Capture and print the output of the Python script
            try ( 
                    BufferedReader apiOutput = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = apiOutput.readLine()) != null) {
                    System.out.println(line);  // Print Python script output to Java console
                }
                // Close the BufferedReader
            }

            // Wait for api.py to finish running and display relevant message
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python script executed successfully.");
            } else {
                System.out.println("Python script execution failed with exit code: " + exitCode);
            }

            // Read the data file
            File file = new File("latest_data.txt");
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    System.out.println("Data from file: "); 
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("File not found!");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
