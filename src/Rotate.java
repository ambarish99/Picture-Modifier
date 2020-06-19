import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Rotate{
private static BufferedImage result;

    public static BufferedImage left(BufferedImage image) {

        try {
                                                           //height at the place of width and width at the placeof height
            result = new BufferedImage(image.getHeight(),image.getWidth(),BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) 
	    {
                for (int j = 0; j < image.getWidth(); j++) 
		{
                    int p=image.getRGB(j,i);
                    result.setRGB(i, j, p);
                }
            }


        }  catch (Exception e) {
            e.printStackTrace();
        }

           return result;
    }




    public static BufferedImage right(BufferedImage image) {

        try {
                                                           //height at the place of width and width at the placeof height
            result = new BufferedImage(image.getHeight(),image.getWidth(),BufferedImage.TYPE_INT_RGB);
            int height=image.getHeight();
            int width=image.getWidth();
            for (int i = 0; i < image.getHeight(); i++) 
	    {
                for (int j = 0; j < image.getWidth(); j++) 
		{
                    int p=image.getRGB(j,i);
                    result.setRGB(height-i-1, width-j-1, p);
                }
            }



        }  catch (Exception e) {
            e.printStackTrace();
        }

           return result;
    }








}