import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrayScale {

    public static BufferedImage convert(BufferedImage image) {

            BufferedImage result = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);

            for (int i = 0; i < result.getHeight(); i++) 
	    {
                for (int j = 0; j < result.getWidth(); j++) 
		{
                    Color c = new Color(result.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color newColor = new Color(red + green + blue,red + green + blue,red + green + blue);
                    result.setRGB(j, i, newColor.getRGB());      
                }
            }

System.out.println("converted to gray scale");
return result;
    }

}