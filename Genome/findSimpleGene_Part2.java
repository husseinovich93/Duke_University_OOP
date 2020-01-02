
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Part2 {
    public String findSimpleGene(String dna,String startCodon,String stopCodon)
    {
        //Create an Object from Scanner class to read inputs
        //Scanner ob = new Scanner(System.in);
        String Gene = "";
        // Ask user to enter the Start Codon
        //System.out.print("Enter Codon start: "); 
        //String CStart = ob.nextLine();
        // The index of Codon start.
        if(dna.equals(dna.toLowerCase()))
        {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int codonStart = dna.indexOf(startCodon);
        if(codonStart == -1)
        {
            System.out.println("Codon Start Is Not Found");
            return Gene;
        }
        // Ask user to enter the end Codon
        //System.out.print("Enter Codon End: "); 
        //String CEnd = ob.nextLine();
        // The index of Codon End.
        int codonEnd = dna.indexOf(stopCodon ,codonStart + 3 );
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
        else{
            System.out.println("This Gene Is Not Valid");
        }
        return Gene;
    }
     public void testSimpleGene()
     {
        String Start = "ATG";
        String End = "TAA";
        
        String DNA1 = "aatgcgtaatatggt";
        System.out.println("DNA1 Strand is : "+ DNA1);
        System.out.println("Gene is : " + findSimpleGene(DNA1 , Start , End));
        
        String DNA2 = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA2 Strand is : "+ DNA2);
        System.out.println("Gene is : " + findSimpleGene(DNA2, Start , End));
        
        String DNA3 = "ACTAGGGTAT";
        System.out.println("DNA3 Strand is : "+ DNA3);
        System.out.println("Gene is : " + findSimpleGene(DNA3, Start , End));
        
        String DNA4 = "AATGCTAGGGTATGGT";
        System.out.println("DNA4 Strand is : "+ DNA4);
        System.out.println("Gene is : " + findSimpleGene(DNA4, Start , End));
        
        String DNA5 = "ACTAGGGTATGGT";
        System.out.println("DNA5 Strand is : "+ DNA5);
        System.out.println("Gene is : " + findSimpleGene(DNA5, Start , End));
        
        String DNA6 = "aatgcggtaatatggt";
        System.out.println("DNA6 Strand is : "+ DNA6);
        System.out.println("Gene is : " + findSimpleGene(DNA6 , Start , End));
     }
}
