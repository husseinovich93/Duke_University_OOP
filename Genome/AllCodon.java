
/**
 * Write a description of AllCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllCodon {
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
        return dnaStr.length();
    }
    public String findGene(String dna )
    {
        int startCodonIndex = dna.indexOf("ATG");
        System.out.println(startCodonIndex);
        if(startCodonIndex == -1 )
        {
            return "";
        }
            int x1 = findStopCodon(dna ,startCodonIndex , "TAA");
            int x2 = findStopCodon(dna ,startCodonIndex , "TAG");
            int x3 = findStopCodon(dna ,startCodonIndex , "TGA");
            int temp = Math.min(x1 , x2 );
            int minIndex = Math.min(temp , x3);
            if(minIndex == dna.length())
            {
                return "";
            }
            //if( x1 < x2 && x2 < x3 )
            //{
            //    return dna.substring(startCodonIndex , x1 + 3);
            //}
            //else if(x2 < x1 && x1 < x3)
            //{
            //    return dna.substring(startCodonIndex , x2 + 3);
            //}
            //else if(x3 < x1 && x1 < x2 )
            //{
            //    return dna.substring(startCodonIndex , x3 + 3);
            //}
            return dna.substring(startCodonIndex , minIndex + 3);
    }
    public void testSimpleGene()
     {
        String DNA1 = "ATGCGGTAATATGGT";
        System.out.println("DNA1 Strand is : "+ DNA1);
        System.out.println("Gene is : " + findGene(DNA1));
        
        String DNA2 = "ATGCTAGGGTGATAATATGGT";
        System.out.println("DNA2 Strand is : "+ DNA2);
        System.out.println("Gene is : " + findGene(DNA2));
        
        String DNA3 = "ATGTGATAAAGGGTAT";
        System.out.println("DNA3 Strand is : "+ DNA3);
        System.out.println("Gene is : " + findGene(DNA3));
        
        String DNA4 = "AATGCTAGGGTATGGT";
        System.out.println("DNA4 Strand is : "+ DNA4);
        System.out.println("Gene is : " + findGene(DNA4));
        
        String DNA5 = "ACTAGGGTATGTAG";
        System.out.println("DNA5 Strand is : "+ DNA5);
        System.out.println("Gene is : " + findGene(DNA5));
     }
}
