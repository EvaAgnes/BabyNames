
/**
 * Gets name based on given input. 
 * Requires name and gender and/or year.
 * Depending on CountCalculator and RankCalculator.
 * 
 * @return name
 *
 * @Eva
 * @Version 2, July 2019
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class NamePrinter {

    CountCalculator countCalculator;
    RankCalculator rankCalculator;

    NamePrinter() {
        countCalculator = new CountCalculator();
        rankCalculator = new RankCalculator();
    }

    /**
    * Gets all names, gender, number of births in file, given a year.
    * Requires file.
    * @print all name, gender, number of births.
    */
    public void printAllNamesOfFile(){
        FileResource fr = new FileResource();
        
        for (CSVRecord record : fr.getCSVParser(false)){
            System.out.print("Name: " + record.get(0));
            System.out.print("Gender: " + record.get(1));
            System.out.println("Num Born: " + record.get(2));
        }
    }
    
    /**
    * Gets name given file, gender, and rank.
    * Requires file, gender, rank.
    * @return name or "NO NAME" if gender and rank are not valid.
    */
    public String getName(FileResource fr, String gender, int rank){
        int rankStart = 0;
        int rankF = 0;
        int rankM = 0;
        int numberGirls = countCalculator.numberNamesGender(fr, "F");

        for (CSVRecord record : fr.getCSVParser(false)){
            rankStart += 1;
            if (gender == "F"){
                rankF = rankStart;
                if (rankF == rank){
                    return record.get(0);
                }
            }
            else{
                rankM = rankStart - numberGirls;
                if (rankM == rank){
                    return record.get(0);
                }
            }
        }

        return "NO NAME";
    }

    /**
    * Tests getName().
    * Gets automatically file.
    * Requires rank, gender, year.
    * @return name.
    */
    public String printName(int rank, String gender, int year){
        FileResource fr = new FileResource("us_babynames/yob"+year+".csv");
        String name = getName(fr, gender, rank);

        return name;
    }

    /**
    * Gets name in year b based on rank of name in year a.
    * Requires name, year a, year b, gender.
    * @return name in year b.
    */
    public String whatIsNameInYear(String name, int year, int NewYear, String gender){

        FileResource yearStart = new FileResource("us_babynames/yob"+year+".csv");
        FileResource yearCompare = new FileResource("us_babynames/yob"+NewYear+".csv");

        int rankNameStart = rankCalculator.getRank(yearStart, name, gender);
        String answer = getName(yearCompare, gender, rankNameStart);

        /*if (gender == "F"){
            System.out.println(name + " born in " + year + " would be " + answer
                               + " if she was born in " + NewYear);
        }
        else {
            System.out.println(name + " born in " + year + " would be " + answer
                               + " if he was born in " + NewYear);
        }*/
        return answer;
    }
}
