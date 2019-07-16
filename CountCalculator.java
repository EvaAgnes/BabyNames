
/**
 * Calculates number of births, total names, and names per gender.
 * 
 * @return number 
 * 
 * @Eva
 * @Version 2, July 2019
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CountCalculator {

    /**
    * Calculates total number of births, total number of girls and boys,
    *   given a year. 
    * Requires file.  
    * @prints total births, girls, boys for given year. 
    */
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
    
    /**
    * Calculates total number of unique names, given a year.
    * Requires file.
    * @return total number of unique names for given year.
    */
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
    
    /**
    * Calculates total number of names per gender, given a year.
    * Requires file, gender.
    * @return total number of unique names per gender for given year.
    */
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
 
}

