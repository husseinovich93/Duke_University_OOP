
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences (String stringa  , String stringb )
    {
        int len1 = stringa.length();
        int len2 = stringb.length();
        int count = 0;
        int val = stringb.indexOf(stringa);
        while(val != -1 && val < len2)
        {
            count++;
            val = stringb.indexOf(stringa , val+len1);
        }
        if(count >= 2)
        {   System.out.println("Str1: "+stringa+","+"Str2: "+stringb);
            System.out.println("True");
            return true;
        }
        return false;
    }
    public void testing()
    {
        boolean s1 = twoOccurrences("by" ,"A story by Abby Long");
        boolean s2 = twoOccurrences("a" , "banana");
        boolean s3 = twoOccurrences("atg" , "ctgtatgta"); 
        
        String e1 = lastPart("an", "banana");
        System.out.println("The part of the string after an in banana is "+e1+".");
        String e2 = lastPart("zoo","forest");
        System.out.println("The part of the string after zoo in forest is "+e2+".");
    }
    public String lastPart(String stringa  , String stringb)
    {
        int index = stringb.indexOf(stringa);
        if(index == -1 )
        {
            //System.out.println("he part of the string after "+stringa+" in "+stringb+" is "+stringb);
            return stringb ;
        }
        //System.out.println("he part of the string after "+stringa+" in "+stringb+" is "+stringb);
        return stringb.substring(index + stringa.length());
    }
}
