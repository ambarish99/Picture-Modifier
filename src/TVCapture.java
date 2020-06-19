import java.awt.image.*;

public class TVCapture
{
public static BufferedImage create(BufferedImage image)
{
int width = image.getWidth();
int height = image.getHeight();
int p,r,g,b,a;
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

if(y%2==0 && x%2==0)     p = (a<<24) | (0<<16) | (0<<8) | 0;
else     p = (a<<24) | (r<<16) | (g<<8) | b;

    image.setRGB(x, y, p);
}
}
return image;
}

}