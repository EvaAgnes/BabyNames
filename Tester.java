
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Tester {
    
    CountCalculator countCalculator;
    NamePrinter namePrinter; 
    RankCalculator rankCalculator;
    int totalNumberOfSucceededTests;
    
    Tester() {
        countCalculator = new CountCalculator();
        namePrinter = new NamePrinter();
        rankCalculator = new RankCalculator();
        totalNumberOfSucceededTests = 0;
    }
    
    public void autoTest(){
        testNumberNamesGender();
        testRank();
        testPrintName();
        testWhatIsNameInYear();    
        testYearOfHighestRank();  
        testGetAverageRank();
        testGetTotalBirthsRankedHigher(); 
        if (totalNumberOfSucceededTests == 7){
            System.out.println("All tests are successful!");
        }
    }
    
    public void testNumberNamesGender(){
        String inputGender = "M";
        int inputYear = 1905;
        
        FileResource fr = new FileResource("us_babynames/yob"+inputYear+".csv");
        int answer = countCalculator.numberNamesGender(fr, inputGender);
        if (answer == 1421){
            totalNumberOfSucceededTests += 1;
        }
        else {
            System.out.println("testNumberNamesGender FAILED");
        }
    }
    
      public void testRank(){
        String inputName = "Emily";
        String inputGender = "F";
        int inputYear = 1960;
        
        int answer = rankCalculator.rank(inputName, inputGender, inputYear);
        
        if (answer == 251){
            totalNumberOfSucceededTests += 1;
        }
        else {
            System.out.println("testRank FAILED");
        }
    }
    
    public void testPrintName(){
        int inputRank = 450;
        String inputGender = "M";
        int inputYear = 1982;
        
        String answer = namePrinter.printName(inputRank, inputGender, inputYear);
        if (answer.equals("Forrest")){
            totalNumberOfSucceededTests += 1;
        }
        else {
            System.out.println("testPrintName FAILED");
        }
    }
    
    public void testWhatIsNameInYear(){
        String inputName = "Owen";
        String inputGender = "M";
        int inputFirstYear = 1974;
        int inputSecondYear = 2014;
        
        String answer = namePrinter.whatIsNameInYear(inputName, inputFirstYear, inputSecondYear, inputGender);
        if (answer.equals("Leonel")){
            totalNumberOfSucceededTests += 1;
        }
        else {
            System.out.println("testWhatIsNameInYear FAILED");
        }
    }  
    
    public void testYearOfHighestRank(){
        String inputName = "Mich";
        String inputGender = "M";
        
        int answer = rankCalculator.yearOfHighestRank(inputName, inputGender);
        if (answer == 1960){
            totalNumberOfSucceededTests += 1;
        }
        else {
            System.out.println("testYearOfHighestRank FAILED");
        }
    }
    
    public void testGetAverageRank(){
        String inputName = "Robert";
        String inputGender = "M";
        
        double answer = rankCalculator.getAverageRank(inputName, inputGender);
         if (answer == 10.0){
            totalNumberOfSucceededTests += 1;
        }
        else {
            System.out.println("testGetAverageRank FAILED");
        }
    }
    
    public void testGetTotalBirthsRankedHigher(){
        String inputName = "Drew";
        String inputGender = "M";
        int inputYear = 1990;
        
        int answer = rankCalculator.getTotalBirthsRankedHigher(inputName, inputGender, inputYear);
        if (answer == 1498074){
            totalNumberOfSucceededTests += 1;
        }
        else {
            System.out.println("testGetTotalBirthsRankedHigher FAILED");
        }
    }
    
}
