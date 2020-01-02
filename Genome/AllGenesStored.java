
/**
 * Write a description of AllGenesStored here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class AllGenesStored {
    public int findStopCodon(String dna , int startIndex , String stopCodon)
    {
        int endIndex = dna.indexOf(stopCodon , startIndex + 3);
        while(endIndex != -1)
        {
            if((endIndex - startIndex) % 3 == 0)
            {
                return endIndex;
            }
            endIndex = dna.indexOf(stopCodon , endIndex + 1);
        }
        return -1;
    }
    public String findGene(String dna , int where)
    {
        int startCodon = dna.indexOf("ATG" , where);
        if(startCodon == -1)
        {
            return "";
        }
        int indTAA = findStopCodon(dna , startCodon , "TAA" );
        int indTAG = findStopCodon(dna , startCodon , "TAG" );
        int indTGA = findStopCodon(dna , startCodon , "TGA" );
        int minVal = 0;
        if(indTAA == -1 ||
          (indTAG != -1 && indTAG < indTAA))
        {
            minVal = indTAG ; 
        }
        else
        {
            minVal = indTAA ; 
        }
        if(minVal == -1 ||
          (indTGA != -1 && indTGA < minVal ))
        {
            minVal = indTGA;
        }
        if(minVal == -1)
        {
            return "";
        }
        return dna.substring(startCodon , minVal + 3 );
    }
    public StorageResource getAllGenes(String dna)
    {
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        //int count = 1;
        while(true)
        {
            String gene = findGene(dna , startIndex );
            if(gene.isEmpty())
            {
                break;
            }
            //System.out.println("Gene "+count+" : "+gene);
            //count++;
            sr.add(gene);
            startIndex = dna.indexOf(gene , startIndex) + gene.length();
        }
        return sr;
    }
    public void testOn()
    {
        String dna = "AAATGGTTGATTGAATGGGTTAAAAATGGTTGATTGAATGGGTTAA";
        StorageResource sr = getAllGenes(dna);
        for(String s : sr.data())
        {
            System.out.println(s);
        }
    }
}
