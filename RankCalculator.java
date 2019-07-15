
/**
 * Write a description of RankCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class RankCalculator {

    CountCalculator countCalculator;

    RankCalculator() {
        countCalculator = new CountCalculator();
    }

    public int getRank(FileResource fr, String name, String gender){
        int rankStart = 0;
        int rankNameInput = 0;

        for (CSVRecord record : fr.getCSVParser(false)){
            rankStart += 1;
            if (record.get(0).equals(name) && record.get(1).equals(gender)){

                if (gender.equals("F")){
                    rankNameInput = rankStart;
                }
                else {
                    rankNameInput = rankStart - (countCalculator.numberNamesGender(fr, "F"));
                }
            }
        }

        if (rankNameInput > 0){
            return rankNameInput;
        }

        return -1;
    }

    public int rank(String name, String gender, int year){
        FileResource fr = new FileResource("us_babynames/yob"+year+".csv");
        int answer = getRank(fr, name, gender);
        return answer;
    }

    public int yearOfHighestRank(String name, String gender){
        int currentRank = 0;
        int highestRank = 0;
        int previousRankF = 0;
        int previousRankM = 0;
        String nameCurrentFile = "";
        String nameHighestFile = "";

        DirectoryResource dr = new DirectoryResource();

        File folder = new File("us_babynames");
        File[] listOfFiles = folder.listFiles();

        for (File f : listOfFiles){

            FileResource fr = new FileResource(f);
            currentRank = getRank(fr, name, gender);

            if (highestRank == 0 && currentRank != -1){
                if (currentRank < countCalculator.numberNamesGender(fr, gender)){
                    highestRank = currentRank;
                    nameCurrentFile = f.getName();
                    nameHighestFile = nameCurrentFile;
                }
                else {
                    highestRank = highestRank;
                }
            }
            else{
                if (currentRank < highestRank && currentRank !=-1){
                    highestRank = currentRank;
                    nameCurrentFile = f.getName();
                    nameHighestFile = nameCurrentFile;
                }
                else {
                    highestRank = highestRank;
                }
            }
        }

        if (highestRank <= 0){
                return -1;
            }
        nameHighestFile = nameHighestFile.replaceAll("\\D+","");
        int outputHighestFile = Integer.parseInt(nameHighestFile);
        return outputHighestFile;
    }

    public double getAverageRank(String name, String gender){
        int currentRank = 0;
        int totalRank = 0;
        double averageRank = 0.0;
        int numberOfFiles = 0;

        DirectoryResource dr = new DirectoryResource();

        File folder = new File("us_babynames");
        File[] listOfFiles = folder.listFiles();

        for (File f : listOfFiles){
            numberOfFiles += 1;

            FileResource fr = new FileResource(f);

            currentRank = getRank(fr, name, gender);
            totalRank += currentRank;
        }

        averageRank = totalRank / numberOfFiles;
        return averageRank;
        }

    public int getTotalBirthsRankedHigher(String name, String gender, int year){
        int rankStart = 0;
        int nameInputRank = 0;
        int compareRank = 0;
        int totalBirth = 0;

        FileResource fr = new FileResource("us_babynames/yob"+year+".csv");
        int totalNamesF = countCalculator.numberNamesGender(fr, "F");

        nameInputRank = getRank(fr, name, gender);

        for (CSVRecord record : fr.getCSVParser(false)){
            rankStart += 1;
            if (gender == "F"){
                compareRank = rankStart;
                if(compareRank < nameInputRank && compareRank > 0){
                   totalBirth += Integer.parseInt(record.get(2));
                }
                else{
                    totalBirth = totalBirth;
                }
            }
            else {
                compareRank = rankStart - totalNamesF;
                if(compareRank < nameInputRank && compareRank > 0){
                   totalBirth += Integer.parseInt(record.get(2));
                }
                else{
                    totalBirth = totalBirth;
                }
            }
        }

        return totalBirth;
    }
}




