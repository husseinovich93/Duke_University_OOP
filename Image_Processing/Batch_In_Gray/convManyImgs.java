
/**
 * Write a description of convManyImgs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class convManyImgs {
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            //set pixel's red to average
            pixel.setRed(average);
            //set pixel's green to average
            pixel.setGreen(average);
            //set pixel's blue to average
            pixel.setBlue(average);
        }
        //outImage is your answer
        return outImage;
    }
    public void doSave(){
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()){
		ImageResource image = new ImageResource(f);
		ImageResource Newgray = makeGray(image);
		String fname = Newgray.getFileName();
		String newName = "gray-" + fname;
		Newgray.setFileName(newName);
		//image.draw();
		Newgray.draw();
		Newgray.save();
		//ImageResource newImgGray = new ImageResource("images/"+newName);
		//image.draw();
		
		
		//Newgray.save();
		//makeGray();
		//Newgray.save();
		//Newgray.draw();
			
	}
    }
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }
    public void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    public void saveFinal()
    {
        doSave();
    }
}
