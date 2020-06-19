import java.awt.image.*;

public class RGB
{
public static BufferedImage setRGB(BufferedImage image,int red,int green,int blue)
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

    if(r+red>0 && r+red<255)
    {
     r=r+red;
    }
    if(g+green>0 && g+green<255)
    {
     g=g+green;
    }
    if(b+blue>0 && b+blue<255)
    {
     b=b+blue;
    }
    p = (a<<24) | (r<<16) | (g<<8) | b;
    image.setRGB(x, y, p);
}
}
return image;
}

}