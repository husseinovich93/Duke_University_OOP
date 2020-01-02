
/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGeneWhile {
    public String fingGene(String dna )
    {
        // Find first occurence of "ATG" call it's index startIndex
        int startIndex = dna.indexOf("ATG");
        // Find TAA Starting from (startIndex+3), call it currIndex
        int currIndex = dna.indexOf("TAA",startIndex + 3);
        // As long as the currIndex is not equal to -1 do:
        while(currIndex != -1)
        {
            //Check if (currIndex - startIndex) is a multiple of 3
            if((currIndex - startIndex) % 3 == 0)
            {
                // If so return the text between start and current+3
                return dna.substring(startIndex , currIndex + 3);
            }
            // If not update the curr to start from curr+3
            else
            {
                currIndex = dna.indexOf("TAA",currIndex + 1);
            }
        }
        return "" ;
    }
    public void testSimpleGene()
     {
        String DNA1 = "AATGCGTAATATGGT";
        System.out.println("DNA1 Strand is : "+ DNA1);
        System.out.println("Gene is : " + fingGene(DNA1));
        
        String DNA2 = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA2 Strand is : "+ DNA2);
        System.out.println("Gene is : " + fingGene(DNA2));
        
        String DNA3 = "ACTAGGGTAT";
        System.out.println("DNA3 Strand is : "+ DNA3);
        System.out.println("Gene is : " + fingGene(DNA3));
        
        String DNA4 = "AATGCTAGGGTATGGT";
        System.out.println("DNA4 Strand is : "+ DNA4);
        System.out.println("Gene is : " + fingGene(DNA4));
        
        String DNA5 = "ACTAGGGTATGGT";
        System.out.println("DNA5 Strand is : "+ DNA5);
        System.out.println("Gene is : " + fingGene(DNA4));
     }
}

