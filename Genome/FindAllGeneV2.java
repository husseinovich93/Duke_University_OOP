
/**
 * Write a description of FindAllGeneV2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindAllGeneV2 {
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
    public String findGene(String dna , int where )
    {
        int startCodonIndex = dna.indexOf("ATG" , where);
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
        return dna.substring(startCodonIndex , minIndex + 3);
    }
    public void printAllGens(String dna)
    {
        int start = 0;
        while(true)
        {
            String str = findGene(dna , start);
            if(str.isEmpty())
            {
                break;
            }
            System.out.println(str);
            start = dna.indexOf(str,start)+str.length();
        }
    }
    public void testOn(String dna)
    {
        System.out.println("Testing printAllGens On :"+ dna);
        printAllGens(dna);
    }
    public void test()
    {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCTAAGAAGATAATAGAGGGCCATGTAA");
    }
    
}
