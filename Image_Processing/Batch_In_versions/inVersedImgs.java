 /**
 * Write a description of inVersedImgs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class inVersedImgs {
    public ImageResource makeInversion(ImageResource inImage){
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //set pixel's red to average
            pixel.setRed(255-inPixel.getRed());
            //set pixel's green to average
            pixel.setGreen(255-(inPixel.getGreen()));
            //set pixel's blue to average
            pixel.setBlue(255-(inPixel.getBlue()));
        }
        //outImage is your answer
        return outImage;
    }
    public void doSave(){ 
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            ImageResource Newconvert = makeInversion(image);
            String fname = Newconvert.getFileName();
            String newName = "inverted-" + fname;
            Newconvert.setFileName(newName);
            Newconvert.draw();
            Newconvert.save();
        }
    }
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource invert = makeInversion(inImage);
            invert.draw();
            invert.save();
        }
    }
    public void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource invert = makeInversion(ir);
        invert.draw();
    }
    public void saveFinal()
    {
        doSave();
    }
}
