
/**
 * Write a description of Numbers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class NumberCalculator {

    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBirthsF = 0;
        int totalBirthsM = 0;   
        
        for (CSVRecord record : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if (record.get(1).equals("F")){
                totalBirthsF += numBorn;
            }
            else {
                totalBirthsM += numBorn;
            }
        }
        
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total births Girls = " + totalBirthsF);
        System.out.println("Total births Boys = " + totalBirthsM);
    }
    
       public int numberNamesTotal(FileResource fr){
        int totalNames = 0;
        String startName = "";
        String currentName = "";
        
        for (CSVRecord record : fr.getCSVParser(false)){
            currentName = record.get(0);
            if (currentName != startName){
                totalNames += 1;
            }  
        }
        
        return totalNames;
    }
    
    public int numberNamesGender(FileResource fr, String gender){
        int totalNames = 0;
        String startName = "";
        String currentName = "";
        
        for (CSVRecord record : fr.getCSVParser(false)){
            currentName = record.get(0);
            if (currentName != startName && record.get(1).equals(gender)){
                totalNames += 1;
            }
        }
        
        return totalNames;
    }
    
    public void printAllNamesOfFile(){
        FileResource fr = new FileResource();
        
        for (CSVRecord record : fr.getCSVParser(false)){
            System.out.print("Name: " + record.get(0));
            System.out.print("Gender: " + record.get(1));
            System.out.println("Num Born: " + record.get(2));
        }
    }
 
}

