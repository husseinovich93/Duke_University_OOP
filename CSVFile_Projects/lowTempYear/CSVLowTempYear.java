
/**
 * Write a description of CSVLowTempYear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVLowTempYear {
    public String filePath = null;
    public String resFileName = null;
    public CSVRecord getLow(CSVRecord Low, CSVRecord Current,String operand)
    {
        if(Low == null)
        {
              Low = Current;
        }
        else
        {
             String x1 = Low.get(operand);
             String x2 = Current.get(operand);
             if(x2.contains("N/A"))
             {
                 x2 = x1;
             }
             double LowOperand = Double.parseDouble(x1);
             double CurrentOperand = Double.parseDouble(x2);
             if(CurrentOperand < LowOperand)
             {
                 if(operand == "TemperatureF" && CurrentOperand == -9999 || CurrentOperand == LowOperand)
                 {
                     Current = Low;
                 }
                 Low = Current; 
             }
        }
            return Low;
    }
    public CSVRecord getLow(CSVRecord Low, CSVRecord Current,String operand,String Filename, String Path)
    {
        if(Low == null)
        {
              Low = Current;
        }
        else
        {
             String x1 = Low.get(operand);
             String x2 = Current.get(operand);
             if(x2 == "N/A")
             {
                 x2 = x1;
             }
             double LowOperand = Double.parseDouble(x1);
             double CurrentOperand = Double.parseDouble(x2);
             if(CurrentOperand < LowOperand)
             {
                 if(operand == "TemperatureF" && CurrentOperand == -9999 || CurrentOperand == LowOperand)
                 {
                     Current = Low;
                 }
                 Low = Current; 
                 filePath = Path;
                 resFileName = Filename;
             }
        }
            return Low;
    }
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord Low = null;
        for(CSVRecord Current: parser)
        {
            Low= getLow(Low, Current,"TemperatureF");
        }
        return Low;
    }
    public CSVRecord ColdestTempInManyFiles()
    {
        CSVRecord Low = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord Current = coldestHourInFile(fr.getCSVParser());
            Low= getLow(Low, Current,"TemperatureF");
        }   
        return Low;
    }
    public String fileWithColdestTemperature()
    {
        CSVRecord Low = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord Current = coldestHourInFile(fr.getCSVParser());
            Low= getLow(Low, Current,"TemperatureF",f.getName(),f.getPath());
        }
        return resFileName;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord Low = null;
        for(CSVRecord Current: parser)
        {
            Low= getLow(Low, Current,"Humidity");
        }
        return Low;
    }
    public double averageTemperatureInFile(CSVParser parser)
    {
        double Avg = 0;
        int count = 0;
        for(CSVRecord Current: parser)
        {
            double check = Double.parseDouble(Current.get("TemperatureF"));
            if(check != -9999)
            {
                Avg+= check;
                count++;
            }
        }
        return Avg/count;
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser , int value)
    {
        double Avg = 0;
        int count = 0;
        for(CSVRecord Current: parser)
        {
            String s = Current.get("Humidity");
            if(s != "N/A")
            {
                double check = Double.parseDouble(Current.get("TemperatureF"));
                double checkHum = Double.parseDouble(s);
                if(checkHum >= value)
                {
                    if(check != -9999)
                    {
                        Avg+= check;
                        count++;
                    }
                }
            }
        }
        if(count == 0)
        {
            return 0;
        }
        return Avg/count;
    }
    public CSVRecord lowestHumidityInManyFiles()
    {
        CSVRecord Low = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord Current = lowestHumidityInFile(fr.getCSVParser());
            Low= getLow(Low, Current,"Humidity");
        }   
        return Low;
    }
    public String fileWithLowestHumidity()
    {
        CSVRecord Low = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord Current = coldestHourInFile(fr.getCSVParser());
            Low= getLow(Low, Current,"Humidity",f.getName(),f.getPath());
        }
        return resFileName;
    }
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord res = coldestHourInFile(parser);
        System.out.println("The Coldest Temperature Was "+res.get("TemperatureF")+" At "+res.get(0));
        
    }
    public void testColdestHourInManyFiles()
    {
        CSVRecord Result = ColdestTempInManyFiles();
        System.out.println("The Coldest Temperature Was "+Result.get("TemperatureF")+" At "+Result.get("DateUTC"));
    }
    public void testFileWithColdestTemperature()
    {
        System.out.println("Coldest day was in file "+fileWithColdestTemperature());
        FileResource fr = new FileResource(filePath);
        CSVRecord res = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was "+res.get("TemperatureF"));
    }
    public void testLowestHumidityInFile() 
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord res = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+res.get("Humidity")+" at "+res.get("DateUTC"));
    }
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is "+averageTemperatureInFile(parser));
    }
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double ch = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(ch == 0)
        {
            System.out.println("No temperatures with that humidity");
        }
        else
        {
            System.out.println("Average Temp when high Humidity is "+ ch);
        }
    }
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord Result = lowestHumidityInManyFiles();
        System.out.println("The Lowest Humidity Was "+Result.get("Humidity")+" At "+Result.get("DateUTC"));
    }
    public void testFileWithLowestHumidity()
    {
        System.out.println("The Lowest Humidity Was in file "+fileWithLowestHumidity());
        FileResource fr = new FileResource(filePath);
        CSVRecord res = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("The Lowest Humidity In That Day Was "+res.get("Humidity"));
    }
}
