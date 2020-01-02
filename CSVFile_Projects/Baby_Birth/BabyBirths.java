
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyBirths {
    public void totalBirths (FileResource fr)
    {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int boyCount = 0;
        int girlCount = 0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).contains("M"))
            {
                totalBoys += numBorn;
                boyCount++;
            }
            else if(rec.get(1).contains("F")) 
            {
                totalGirls += numBorn;
                girlCount++;
            }
            else
            {
                totalBoys += 0;
                totalBoys += 0;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("girls nums = " + girlCount);
        System.out.println("boys nums = " + boyCount);
    }  
    public int getRank(int year, String name, String gender)
    {
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int rank = 0;
        for(CSVRecord record: fr.getCSVParser(false))
        {
            if(record.get(1).equals(gender))
            {
                rank++;
                if(record.get(0).equals(name))
                {
                    return rank;
                }
            }
        }
        return -1 ; 
    }
    public int getRank(String fileName, String name, String gender)
    {
        FileResource fr = new FileResource("data/"+fileName);
        int rank = 0;
        for(CSVRecord record: fr.getCSVParser(false))
        {
            if(record.get(1).equals(gender))
            {
                rank++;
                if(record.get(0).equals(name))
                {
                    return rank;
                }
            }
        }
        return -1 ; 
    }
    public String getName(int year, int rank, String gender)
    {
        int count = 0;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        for(CSVRecord record: fr.getCSVParser(false))
        {
            if(record.get(1).equals(gender))
            {
                count++;
                if(count == rank)
                {
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year,name,gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear+".");
    }
    public int yearOfHighestRank(String name, String gender)
    {
        int maxRank = -1;
        int compare = -1;
        int year = 0;
        String yearString = null ;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles())
        {
            String fileName = f.getName();
            compare = getRank(fileName, name, gender);
            if(compare == -1 && maxRank == -1)
            {
                yearString = null;
            }
            if(maxRank == -1)
            {
                maxRank = compare;
                yearString = fileName;
            }
            //if(compare == maxRank)
            //{
            //    compare =  maxRank;
            //}
            if(compare < maxRank && compare != -1 )
            {
                maxRank = compare ; 
                yearString = fileName;
            }
        }
        if(yearString == null)
        {
            return 0;
        }
        yearString = yearString.substring(3,7);
        year = Integer.parseInt(yearString);
        return year;
    }
    public double getAverageRank(String name, String gender)
    {
        double avg = 0.0;
        double filesCoun = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles())
        {
            String fileName = f.getName();
            int rank = getRank(fileName,name, gender);
            if(rank != -1)
            {
                avg+= rank;
            }
            filesCoun++;
        }
        avg/= filesCoun ; 
        if(avg == 0.0)
        {
            return -1.0;
        }
        return avg;
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        int inputRank = getRank(year, name, gender);
        int totalBirths = 0;
        int counter = 0;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record: parser)
        {
            //String currentName = record.get(0);
            String currentGender = record.get(1);
            int numBorn = Integer.parseInt(record.get(2));
            if(currentGender.equals(gender))
            {
                counter++;
                if(counter == inputRank)
                {
                    return totalBirths;
                }
                totalBirths += numBorn;
                //int currentRank =getRank(year, currentName, gender);
                //if(currentRank < inputRank)
                //{
                    //totalBirths += numBorn;
                    //if(currentName.equals(name))
                    //{
                     //   return totalBirths;
                    //}
                //}
            }
        }
        return totalBirths;
    }
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob1905.csv");
        totalBirths(fr);
    }
    public void testGetRank()
    {
        System.out.println(getRank(1971,"Frank","M"));
    }
    public void testgetName()
    {
        System.out.println(getName(1982, 450, "M"));
    }
    public void testwhatIsNameInYear()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    public void testyearOfHighestRank()
    {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    public void testGetAverageRank()
    {
        System.out.println(getAverageRank("Robert", "M"));
    }
    public void testgetTotalBirthsRankedHigher()
    {
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
}
    

