import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.core.Size;

import org.opencv.highgui.*;
import org.opencv.imgproc.*;

import org.opencv.core.*;
import org.opencv.imgcodecs.*;

public class PencilSketch
{
private static int colordodge(int in1, int in2) 
{
      float image = (float)in2;
       float mask = (float)in1;
       return ((int) ((image == 255) ? image:Math.min(255, (((long)mask << 8 ) / (255 - image)))));
}
  
private static BufferedImage colorDodgeMerge(BufferedImage blur,BufferedImage gray)
{
BufferedImage outputImage=new BufferedImage(gray.getWidth(),gray.getHeight(),BufferedImage.TYPE_INT_RGB);
Graphics2D graphic=outputImage.createGraphics();
graphic.drawImage(gray,0,0,Color.WHITE,null);
int width=gray.getWidth();
int height=gray.getHeight();
int rg,gg,bg,rb,gb,bb,pg,pb,ro,go,bo,po;
for(int x=0;x<width;x++)
{
for(int y=0;y<height;y++)
{
pb=blur.getRGB(x,y);
rb= (pb>>16) & 0xff;
gb=(pb>>8) & 0xff;
bb=(pb) & 0xff;

pg=gray.getRGB(x,y);
rg= (pg>>16) & 0xff;
gg=(pg>>8) & 0xff;
bg=(pg) & 0xff;

ro=colordodge(rb,rg);
go=colordodge(gb,gg);
bo=colordodge(bb,bg);

po=(255<<24 | ro<<16 | go<<8 | bo);

outputImage.setRGB(x,y,po);
}
}
return outputImage;
}


//code to convert bufferedimage to mat image

	private static Mat img2Mat(BufferedImage in) {
		Mat out = new Mat(in.getHeight(), in.getWidth(), CvType.CV_8UC3);
		byte[] data = new byte[in.getWidth() * in.getHeight() * (int) out.elemSize()];
		int[] dataBuff = in.getRGB(0, 0, in.getWidth(), in.getHeight(), null, 0, in.getWidth());
		for (int i = 0; i < dataBuff.length; i++) {
			data[i * 3] = (byte) ((dataBuff[i]));
			data[i * 3 + 1] = (byte) ((dataBuff[i]));
			data[i * 3 + 2] = (byte) ((dataBuff[i]));
		}
		out.put(0, 0, data);
		return out;
	}




private static BufferedImage gaussianBlur(BufferedImage invert)
{
Mat inputMat=img2Mat(invert);
//gaussian blur of size 15
         Mat destination = new Mat(inputMat.rows(),inputMat.cols(),inputMat.type());
         Imgproc.GaussianBlur(inputMat, destination,new Size(21,21), 0);

//convert mat to bufferedimage
    MatOfByte mob=new MatOfByte();
    Imgcodecs.imencode(".jpg", destination, mob);
    byte ba[]=mob.toArray();
try
{
    invert=ImageIO.read(new ByteArrayInputStream(ba));
}catch(Exception e)
{
e.printStackTrace();
}
return invert;
}

private static BufferedImage copyImage(BufferedImage source){
    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
    Graphics2D g = b.createGraphics();
    g.drawImage(source, 0, 0, null);
    g.dispose();
    return b;
}

public static BufferedImage convert(BufferedImage image)
{
System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
BufferedImage result=null;
try
{
BufferedImage bwImage=GrayScale.convert(image);
BufferedImage invert=GrayScale.convert(image);
NegativeImage.convert(invert);
invert=gaussianBlur(invert);
result=colorDodgeMerge(bwImage,invert);
}catch(Exception e)
{
e.printStackTrace();
}
return result;
}


}