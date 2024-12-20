import java.io.*;
import java.util.*;

public class currencyConverter {

    public static void main(String[] args) throws IOException {
        // Var declarations
        Scanner sc = new Scanner(System.in);
        String line;
        String current_base_currency = "";

        System.out.println("Welcome to the Currency Converter!!");
        System.out.println("To use a function, please enter the number associated with it");   
   
        try {
            BufferedReader curr = new BufferedReader(new FileReader("base_currency.txt")); 
            while ((line = curr.readLine()) != null){
                current_base_currency = line; 
            }
            curr.close();
        }
        catch(IOException e){
            System.out.println("base_currency.txt not found");
        }

        System.out.println("The default base currency is "+current_base_currency);      
        System.out.println("To close the program, please enter (0)"); 
        System.out.println("To change the base currency, please enter (1)");         
        System.out.println("To get the latest exchange rates, please enter (2)");      
        System.out.println("To get the exchange rates for an older date, please enter (3)"); //(format: 2021-12-31)       
        System.out.println("To get the exchange rates for a range of dates, please enter (4)");
        System.out.print("Enter a number between 0-4: ");
        int function = sc.nextInt();
        System.out.println();

        while (function!=0){
            if (function<0 || function>4){
                System.out.println("Error! Please try again");
                System.out.println();
                System.out.println("To close the program, please enter (0)"); 
                System.out.println("To change the base currency, please enter (1)");         
                System.out.println("To get the latest exchange rates, please enter (2)");      
                System.out.println("To get the exchange rates for an older date, please enter (3)"); //(format: 2021-12-31)       
                System.out.println("To get the exchange rates for a range of dates, please enter (4)");
                System.out.print("Enter a number between 0-4: ");
                function = sc.nextInt();
                System.out.println();                
            }
            else if (function==1)
            {
                System.out.print("Please enter the new base currency: ");
                String base_curr = sc.next().toUpperCase();  
                sc.close();
                callAPI(1,base_curr);              
            }
            else if (function==2){
                callAPI(2, "latest");
                readFile("latest_data.txt");
                                
          }
          else if (function==3){
            System.out.print("Please enter the date to get the exchange rate for that day (format: YYYY-MM-DD): ");
            String exchange_rate_date = sc.next();
            callAPI(3,exchange_rate_date);  
            readFile("historical_data.txt");  
          }
          else{
        
          }
    
        }
        System.out.println("The program is shutting down...");
        System.exit(0);
        
    }

    public static void callAPI(int function, String arg){
        ProcessBuilder pb = new ProcessBuilder("python", "api.py",Integer.toString(function),arg);

        //pb.directory(new File("path/to/your/python/script"));

        //Run api.py
        try{
            Process process = pb.start();

            // Capture and print the output of the Python script
            String line;
            try ( 
                    BufferedReader apiOutput = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    while ((line = apiOutput.readLine()) != null) {
                        System.out.println(line);  // Print Python script output to Java console
                }
                // Close the BufferedReader
            }

            // Wait for api.py to finish running and display relevant message
            try{
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("api.py executed successfully.");
                } else {
                    System.out.println("api.py execution failed with exit code: " + exitCode);
                }
            }   
            catch(InterruptedException ix){
            }
        }
        catch(IOException ioexception){
            System.out.println("api.py executed unsuccessfully.");
        }


    }

    public static void readFile(String textfile){
        String code="";
        String value="";
        String time, date;
        File file = new File(textfile);
        if (!file.exists())
            System.out.println("File not found!");
        
        System.out.println("File exists");
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Latest data"); 
            while ((line = reader.readLine()) != null) {
                if (line.contains("last_updated_at")) {
                    date = line.substring(line.indexOf(":")+3,line.indexOf("T"));
                    time = line.substring(line.indexOf("T")+1,line.indexOf("Z"));
                    System.out.println("This data was last updated on "+date+" at "+time);
                }
                if (line.contains("code"))
                    code = line.substring(line.indexOf(":")+3,line.indexOf(",")-1);
                if (line.contains("value"))
                    value = line.substring(line.indexOf(":")+2);
                if (!line.contains("meta") || !line.contains("data") )
                    if (line.contains("value")) 
                        System.out.println(code+" : "+value);
                }
            } catch (IOException ex) {
        }
    }
}
