
/**
 * Write a description of CSVFileCountryInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class CSVFileCountryInfo {
    public String countryInfo(CSVParser parser , String country)
    {
        String str = country;
        for(CSVRecord record: parser)
        {
            if(record.get(0).contains(country))
            {
                str+=": "+ record.get("Exports")+": "+record.get("Value (dollars)");
                return str;
            }
        }
            return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser , String exportItem1, String exportItem2)
    {
        for(CSVRecord record: parser)
        {
            if(record.get(1).contains(exportItem1) && record.get(1).contains(exportItem2))
            {
                System.out.println(record.get(0));
            }
        }
    }
    public int numberOfExporters(CSVParser parser , String exportItem)
    {
        int counter = 0;
        for(CSVRecord record : parser)
        {
            if(record.get(1).contains(exportItem)){
                counter++;
            }
        }
        return counter;
    }
    public void bigExporters(CSVParser parser, String amount)
    {
        for(CSVRecord record: parser)
        {
            if(record.get(2).length() > amount.length())
            {
                System.out.println(record.get(0)+" "+record.get(2));
            }
        }
    }
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser ,"Nauru"));
    }
    public void testONExpTwoProducts()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser , "cotton", "flowers");
    }
    public void testONnumberOfExporters()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
    }
    public void testONbigExporters()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser , "$999,999,999,999");
    }
}
