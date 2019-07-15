
/**
 * Write a description of NamePrinter here.
 *
 * @author (your name)
 * @version (a version number or a date)
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

    public String printName(int rank, String gender, int year){
        FileResource fr = new FileResource("us_babynames/yob"+year+".csv");
        String name = getName(fr, gender, rank);

        return name;
    }

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
