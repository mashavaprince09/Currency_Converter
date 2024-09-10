import java.io.*;
import java.util.*;

public class currencyConverter {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to the Currency Converter!!");
            System.out.println("To use a function, please enter the number associated with it");   

            String current_base_currency = "";
            BufferedReader curr = new BufferedReader(new FileReader("base_currency.txt")); 
            String line;
            while ((line = curr.readLine()) != null) {
                    current_base_currency = line; 
                }
            
            System.out.println("The default base currency is "+current_base_currency);      
            System.out.println("To change the base currency, please enter (1)");         
            System.out.println("To get the latest exchange rates, please enter (2)");      
            System.out.println("To get the exchange rates for an older date, please enter (3)");      
            System.out.println("To close the program, please enter (0)"); 
            //(format: 2021-12-31)            
            System.out.println("To get the exchange rates for a range of dates, please enter (4)");
            System.out.print("Enter a number between 0-4: ");
            int function = sc.nextInt();

            System.out.println();

            String base_curr = "";
            if (function==0){
                System.out.println("The program is shutting down...");
                System.exit(0);
            }
            else if (function==1)
            {
                System.out.print("Please enter the new base currency: ");
                base_curr = sc.next().toUpperCase();                

            }
            ProcessBuilder pb = new ProcessBuilder("python", "api.py",Integer.toString(function),base_curr);

            //pb.directory(new File("path/to/your/python/script"));

            //Run api.py
            Process process = pb.start();

            // Capture and print the output of the Python script
            try ( 
                    BufferedReader apiOutput = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
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
                    System.out.println("Data from file: "); 
                    while ((line = reader.readLine()) != null) {
                        //System.out.println(line);
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
