
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class Part4 {
    String Conv(String word){
        return word.toLowerCase();
    }
    public void FindingWebLinks(URLResource ur){
        for(String word: ur.words())
        {
            String x = Conv(word);
            int i = x.indexOf("youtube");
            if(i != -1)
            {
                int start = x.lastIndexOf("\"" , i);
                int end = x.indexOf("\"" , i);
                if(start != -1 && end != -1)
                {
                    String urlLink = x.substring(start+1 , end);
                    if(urlLink.startsWith("http://www.youtube.com") || urlLink.startsWith("https://www.youtube.com"))
                    {
                        System.out.println(word.substring(start+1 , end));
                    }
                }
            }
        }
    }
    public void testingWebLinks()
    {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        FindingWebLinks(ur);
    }
}
