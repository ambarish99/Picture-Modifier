import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
/*
//import javafx.scene.effect.Effect;
//import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.*;
import javafx.scene.*;
//import javafx.scene.image.Image;  
//import javafx.scene.image.ImageView;  
import javafx.scene.image.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.application.Application;  
*/

//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.highgui.HighGui;
//import org.opencv.imgcodecs.Imgcodecs;

import org.opencv.core.*;
import org.opencv.imgcodecs.*;

public class HSB
{

public static BufferedImage setHSB(BufferedImage bufferedImage,int hue,int contrast,int brightness)
{
double alphaValue =(double)contrast/ 20.0;
double betaValue=(double)(brightness-100);

  Mat inputMat = new Mat(bufferedImage.getHeight(), bufferedImage.getWidth(), CvType.CV_8UC3);
  byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
  inputMat.put(0, 0, data);

Mat img = new Mat();
inputMat.convertTo(img, -1, alphaValue, betaValue);

    MatOfByte mob=new MatOfByte();
    Imgcodecs.imencode(".jpg", img, mob);
    byte ba[]=mob.toArray();

try
{
    bufferedImage=ImageIO.read(new ByteArrayInputStream(ba));
}catch(Exception e)
{
e.printStackTrace();
}
return bufferedImage;
}






}












/*
public static BufferedImage setHSB(BufferedImage bufferedImage,int hue,int saturation,int brightness)
{

double h=(double)hue/100.0;
double s=(double)saturation/100.0;
double b=(double)brightness/100.0;

System.out.println("s="+s+"  b="+b);


 ColorAdjust colorAdjust = new ColorAdjust();
 colorAdjust.setContrast(0.1);
 colorAdjust.setHue(-0.05);
 colorAdjust.setBrightness(b);
 colorAdjust.setSaturation(s);
 
javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
 ImageView imageView = new ImageView(image);
// imageView.setFitWidth(200);
 imageView.setPreserveRatio(true);
 imageView.setEffect(colorAdjust);

BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
return bImage;
}




public static BufferedImage setHSB(BufferedImage image,int hue,int saturation,int brightness)
{
int width = image.getWidth();
int height = image.getHeight();
int p,r,g,b,a;
float h,s,bn;

float h2=(float)(hue/100);
float s2=(float)(saturation/100);
float bn2=(float)(brightness/100);

for(int x=0;x<width;x++)
{
for(int y=0;y<height;y++)
{
    p=image.getRGB(x,y);
    //get alpha
    a = (p>>24) & 0xff;
    //get red
    r = (p>>16) & 0xff;

    //get green
    g = (p>>8) & 0xff;

    //get blue
    b = p & 0xff;

 
float[] hsb = Color.RGBtoHSB(r, g, b, null);

h= hsb[0];
s= hsb[1];
bn= hsb[2];

if(h+h2>0 && h+h2<1.0)
{
h=h+h2;
}
if(s+s2>0 && s+s2<1.0)
{
s=s+s2;
}
if(bn+bn2>0 && bn+bn2<1.0)
{
bn=bn+bn2;
}

int rgb = Color.HSBtoRGB(h, s, b);

a = (p>>24) & 0xFF;
r = (rgb>>16) & 0xFF;
g = (rgb>>8) & 0xFF;
b = rgb & 0xFF;

p = (a<<24) | (r<<16) | (g<<8) | b;
image.setRGB(x, y, p);
}
}
return image;
}
*/
