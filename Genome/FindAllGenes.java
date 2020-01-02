
/**
 * Write a description of FindAllGenes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindAllGenes {
    String convert(String s ,int x)
    {
        return s.substring( x + 3);
    }
    public int findStopCodon(String dnaStr , int startIndex , String stopCodon)
    {
        // Find stopCodon starting from (startIndex+3)
        int currIndex = dnaStr.indexOf(stopCodon , startIndex + 3);
        while(currIndex != -1)
        {
            if((currIndex - startIndex ) % 3 == 0)
            {
                return currIndex ;
            }
            currIndex = dnaStr.indexOf(stopCodon,currIndex + 1 );
        }
        return -1;
    }
    public void findGene(String dna )
    {
        int startCodonIndex = dna.indexOf("ATG");
        if(startCodonIndex == -1 )
        {
            System.out.println("");
        }
        int x1 = findStopCodon(dna ,startCodonIndex , "TAA");
        int x2 = findStopCodon(dna ,startCodonIndex , "TAG");
        int x3 = findStopCodon(dna ,startCodonIndex , "TGA");
        int minIndex = 0;
        if(x1 == -1 ||
          (x2 != -1 && x2 < x1 ))
        {
            minIndex = x2;
        }
        else
        {
            minIndex = x1 ;
        }
        if(minIndex == -1 ||
          (x3 != -1 && x3 < minIndex))
        {
            minIndex = x3;
        }
        if(minIndex == -1 )
        {
             System.out.println("");
        }
        else
        {
           System.out.println("Gene is "+dna.substring(startCodonIndex , minIndex + 3));
           String v1 = convert(dna , startCodonIndex);
           findGene(v1);
        }
    }
    public void testSimpleGene()
     {
        String DNA1 = "ATGATGCGGTAATATGGT";
        System.out.println("DNA1 Strand is : "+ DNA1);
        findGene(DNA1);
        String DNA2 = "ATGCTAGGGTGATAATATGGT";
        System.out.println("DNA2 Strand is : "+ DNA2);
        findGene(DNA2);
        
        String DNA3 = "ATGTGATAAAGGGTAT";
        System.out.println("DNA3 Strand is : "+ DNA3);
        findGene(DNA3);
        
        String DNA4 = "AATGCTAGGGTATGGT";
        System.out.println("DNA4 Strand is : "+ DNA4);
        findGene(DNA4);
        
        String DNA5 = "ACTAGGGTATGATGTAGATGTGA";
        System.out.println("DNA5 Strand is : "+ DNA5);
        findGene(DNA5);
     }
}
