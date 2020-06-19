import java.awt.image.*;

public class NegativeImage
{
public static BufferedImage convert(BufferedImage image)
{
int width=image.getWidth();
int height=image.getHeight();
int p,a,r,g,b;
for(int x=0;x<width;x++)
{
for(int y=0;y<height;y++)
{
p=image.getRGB(x,y);
a=(p>>24) & 0xff;
r=255-((p>>16) & 0xff);
g=255-((p>>8) & 0xff);
b=255-(p & 0xff);
p=(a<<24) | (r<<16) | (g<<8) | b;
image.setRGB(x,y,p);
}
}
return image;
}

}