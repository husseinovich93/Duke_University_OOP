
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class Part3 {
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
        while(true)
        {
            String gene = findGene(dna , startIndex );
            if(gene.isEmpty())
            {
                break;
            }

            sr.add(gene);
            startIndex = dna.indexOf(gene , startIndex) + gene.length();
        }
        return sr;
    }
    public float cgRatio(String dna)
    {
        int dnaLen = dna.length();
        int count = 0;
        for(char x  : dna.toCharArray())
        {
            if(x == 'C' || x == 'G')
            {
                count++;
            }
        }
        return (float)count / dnaLen ;
    }
    public void processGenes(StorageResource sr)
    {
        int count1 = 0 ;
        int count2 = 0 ; 
        int max = 0;
        for(String s : sr.data())
        {
            if(s.length() > 60)
            {
                count1++; 
                System.out.println(s);
            }
            if(cgRatio(s) > 0.35)
            {
                count2++;
                System.out.println(s);
            }
            if(s.length() > max)
            {
                max = s.length();
            }
        }
        System.out.println("#Of Strs length > 60 : "+count1);
        System.out.println("#Of Strs C-G Ratio > 0.35 : "+count2);
        System.out.println("Length of the longest gene is : "+max);
    }
    public void testProcessGenes()
    {
                       //Gene1...42//
        //String Dna1 = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccac";
        //String Dna2 = "ATGGGGTAA";
        //String Dna3 = "ATGCCCTAA";
        //String Dna4 = "ATGTAA";
        //String Dna5 = "ATGGGGTTTTAATATATGTTTCCCTTTCCCTAA";
        URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        StorageResource sr = getAllGenes(ur.asString());
        System.out.println(sr.size());
        processGenes(sr);
        //processGenes(sr);
        //sr = getAllGenes(Dna2);
        //processGenes(sr);
        //sr = getAllGenes(Dna3);
        //processGenes(sr);
        //sr = getAllGenes(Dna4);
        //processGenes(sr);
        //sr = getAllGenes(Dna5);
        //processGenes(sr);
        
        
    }
}
