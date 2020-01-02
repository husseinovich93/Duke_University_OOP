
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Part1 {
    public String findSimpleGene(String dna)
    {
        //Create an Object from Scanner class to read inputs
        Scanner ob = new Scanner(System.in);
        String Gene = "";
        // Ask user to enter the Start Codon
        System.out.print("Enter Codon start: "); 
        String CStart = ob.nextLine();
        // The index of Codon start.
        int codonStart = dna.indexOf(CStart);
        if(codonStart == -1)
        {
            System.out.println("Codon Start Is Not Found");
            return Gene;
        }
        // Ask user to enter the end Codon
        System.out.print("Enter Codon End: "); 
        String CEnd = ob.nextLine();
        // The index of Codon End.
        int codonEnd = dna.indexOf(CEnd ,codonStart + 3 );
        if(codonEnd == -1)
        {
            System.out.println("Codon End Is Not Found");
            return Gene;
        }
        if((codonEnd - codonStart) % 3 == 0 )
        { 
            Gene = dna.substring(codonStart , codonEnd + 3 );
            System.out.println("This Gene Is Valid");
        }   
        return Gene;
    }
     public void testSimpleGene()
     {
        String DNA1 = "AATGCGTAATATGGT";
        System.out.println("DNA1 Strand is : "+ DNA1);
        System.out.println("Gene is : " + findSimpleGene(DNA1));
        
        String DNA2 = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA2 Strand is : "+ DNA2);
        System.out.println("Gene is : " + findSimpleGene(DNA2));
        
        String DNA3 = "ACTAGGGTAT";
        System.out.println("DNA3 Strand is : "+ DNA3);
        System.out.println("Gene is : " + findSimpleGene(DNA3));
        
        String DNA4 = "AATGCTAGGGTATGGT";
        System.out.println("DNA4 Strand is : "+ DNA4);
        System.out.println("Gene is : " + findSimpleGene(DNA4));
        
        String DNA5 = "ACTAGGGTATGGT";
        System.out.println("DNA5 Strand is : "+ DNA5);
        System.out.println("Gene is : " + findSimpleGene(DNA4));
     }
}
