import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DotPortrait{
private static BufferedImage image;
private static int width,height;
public static void convert(BufferedImage img){
try {

//Graphics2D graphic = result.createGraphics();
//graphic.drawImage(image, 0, 0, Color.WHITE, null);
image=img;
width = image.getWidth();
height = image.getHeight();
int p,r,g,b,a,i,j,x,y,avg,bigAvg;
int ar[]=new int[24];
for(i=0;i<24;i++) ar[i]=1;

int flag=0;
for(x=1;x<width;x+=4)
{
for(y=1;y<height;y+=4)
{
bigAvg=0;

for(i=x;i<x+4 && i<width;i++)
{
for(j=y;j<y+4 && j<height;j++)
{
    p=image.getRGB(i,j);
    a = (p>>24) & 0xff;
    r = (p>>16) & 0xff;
    g = (p>>8) & 0xff;
    b = p & 0xff;
avg=(int)((r+g+b)/3);
bigAvg=bigAvg+avg;
p=(a<<24) | (255<<16) | (255<<8) | 255;
image.setRGB(i,j,p);
}
}
bigAvg=(int)(bigAvg/16);

/*
if(bigAvg<=20)
{
if(ar[0]%3!=0)makeDot(x,y);
ar[0]++;
}
if(bigAvg>20 && bigAvg<=40)
{
if(ar[1]%2==0) makeDot(x,y);
ar[0]++;
}

if(bigAvg>40 && bigAvg<=60)
{
if(ar[2]%2==0) makeDot(x,y);
ar[2]++;
}

if(bigAvg>60 && bigAvg<=80)  // 2 left 2 colored
{
if(ar[3]==3)
{
 makeDot(x,y);
}
if(ar[3]==4)
{
makeDot(x,y);
ar[3]=0;
}
ar[3]++;
}


if(bigAvg>80 && bigAvg<=100) //2 left 1 colored
{
if(ar[4]%3==0) makeDot(x,y);
ar[4]++;
}

if(bigAvg>100 && bigAvg<=120)  //3 left 2 colored
{

if(ar[5]==4)
{
 makeDot(x,y);
}
if(ar[5]==5)
{
makeDot(x,y);
ar[5]=0;
}
ar[5]++;

}

if(bigAvg>120 && bigAvg<=140)  //3 left 1 colored
{
if(ar[6]%4==0) makeDot(x,y);
ar[6]++;
}

if(bigAvg>140 && bigAvg<=160) //4 left 2 colored
{ 
if(ar[7]==5)
{
 makeDot(x,y);
}
if(ar[7]==6)
{
makeDot(x,y);
ar[7]=0;
}
ar[7]++;

}

if(bigAvg>160 && bigAvg<=180)  //4 left 1 colored
{
if(ar[8]%5==0) makeDot(x,y);
ar[8]++;
}


if(bigAvg>180 && bigAvg<=200)  //5 left 2 colored
{

if(ar[9]==6)
{
 makeDot(x,y);
}
if(ar[9]==7)
{
makeDot(x,y);
ar[9]=0;
}
ar[9]++;

}

if(bigAvg>200 && bigAvg<=220) //5 left 1 colored
{
if(ar[10]%6==0) makeDot(x,y);
ar[10]++;
}

if(bigAvg>220 && bigAvg<=240)
{
if(ar[11]%7==0) makeDot(x,y);
ar[11]++;
}
*/

//===========================================================================




if(bigAvg>=0 && bigAvg<10)
{
 if(ar[0]<=6)
 {
 makeDot(x,y);
 ar[0]++;
 }
 else
 {
 ar[0]=1;
 }
}

else if(bigAvg>=10 && bigAvg<20)
{
 if(ar[1]<=11)
 {
 makeDot(x,y);
 ar[1]++;
 }
 else
 {
 ar[1]++;
 }
 if(ar[1]==14)
 {
 ar[1]=1;
 }
}
else if(bigAvg>=20 && bigAvg<30)
{
 if(ar[2]<=5)
 {
 makeDot(x,y);
 ar[2]++;
 }
 else
 {
 ar[2]=1;
 }
}
else if(bigAvg>=30 && bigAvg<40)
{
 if(ar[3]<=9)
 {
 makeDot(x,y);
 ar[3]++;
 }
 else
 {
 ar[3]++;
 }
 if(ar[3]==12)
 {
 ar[3]=1;
 }
}
else if(bigAvg>=40 && bigAvg<50)
{
 if(ar[4]<=4)
 {
 makeDot(x,y);
 ar[4]++;
 }
 else
 {
 ar[4]=1;
 }
}
else if(bigAvg>=50 && bigAvg<60)
{
 if(ar[5]<=7)
 {
 makeDot(x,y);
 ar[5]++;
 }
 else
 {
 ar[5]++;
 }
 if(ar[1]==10)
 {
 ar[5]=1;
 }
}
else if(bigAvg>=60 && bigAvg<70)
{
 if(ar[6]<=3)
 {
 makeDot(x,y);
 ar[6]++;
 }
 else
 {
 ar[6]=1;
 }
}
else if(bigAvg>=70 && bigAvg<80)
{
 if(ar[7]<=5)
 {
 makeDot(x,y);
 ar[7]++;
 }
 else
 {
 ar[7]++;
 }
 if(ar[7]==8)
 {
 ar[7]=1;
 }
}
else if(bigAvg>=80 && bigAvg<90)
{
 if(ar[8]<=2)
 {
 makeDot(x,y);
 ar[8]++;
 }
 else
 {
 ar[8]=1;
 }
}
else if(bigAvg>=90 && bigAvg<100)
{
 if(ar[9]<=3)
 {
 makeDot(x,y);
 ar[9]++;
 }
 else
 {
 ar[9]++;
 }
 if(ar[9]==6)
 {
 ar[9]=1;
 }
}
else if(bigAvg>=100 && bigAvg<110)
{
 if(ar[10]<=1)
 {
 makeDot(x,y);
 ar[10]++;
 }
 else
 {
 ar[10]=1;
 }
}


else if(bigAvg>=110 && bigAvg<120)
{
if(ar[11]%2==0) makeDot(x,y);
ar[11]++;
}
else if(bigAvg>=120 && bigAvg<130)
{
 if(ar[12]<=2)
 {
  makeDot(x,y);
  ar[12]++;
 }
 else ar[12]++;
 if(ar[12]==6)
 {
  ar[12]=1; 
 }
}



else if(bigAvg>=130 && bigAvg<140)
{
if(ar[13]%3==0) makeDot(x,y);
ar[13]++;
}
else if(bigAvg>=140 && bigAvg<150)
{
 if(ar[14]<=2)
 {
  makeDot(x,y);
  ar[14]++;
 }
 else ar[14]++;
 if(ar[14]==8)
 {
  ar[14]=1; 
 }
}

else if(bigAvg>=150 && bigAvg<160)
{
if(ar[15]%4==0) makeDot(x,y);
ar[15]++;

}
else if(bigAvg>=160 && bigAvg<170)
{
 if(ar[16]<=2)
 {
  makeDot(x,y);
  ar[16]++;
 }
 else ar[12]++;
 if(ar[16]==10)
 {
  ar[16]=1; 
 }
}
else if(bigAvg>=170 && bigAvg<180)
{
if(ar[17]%5==0) makeDot(x,y);
ar[17]++;
} 

/*
else if(bigAvg>=180 && bigAvg<190)
{
 if(ar[18]<=2)
 {
  makeDot(x,y);
  ar[18]++;
 }
 else ar[18]++;
 if(ar[18]==12)
 {
  ar[18]=1; 
 }
}
else if(bigAvg>=190 && bigAvg<200)
{
if(ar[19]%6==0) makeDot(x,y);
ar[19]++;
}
else if(bigAvg>=200 && bigAvg<210)
{
 if(ar[20]<=2)
 {
  makeDot(x,y);
  ar[20]++;
 }
 else ar[20]++;
 if(ar[20]==14)
 {
  ar[20]=1; 
 }
}
else if(bigAvg>=210 && bigAvg<220)
{
if(ar[21]%7==0) makeDot(x,y);
ar[21]++;
}

else if(bigAvg>=220 && bigAvg<230)
{
if(ar[22]%8==0) makeDot(x,y);
ar[22]++;
}
else if(bigAvg>=230 && bigAvg<240)
{
if(ar[23]%9==0) makeDot(x,y);
ar[23]++;
}else if(bigAvg>=240 && bigAvg<255)
{

}

*/



//===========================================================================

}
}

  }  catch (Exception e) 
    {
     e.printStackTrace();
    }
}


private static void makeDot(int x,int y)
{
int i,j,k,p,e,l;
for(j=y;j<y+4 && j<height ;j++)
{
for(i=x;i<x+4 && i<width ;i++)
{

 p=(100<<24) | (0<<16) | (0<<8) | 0;
 image.setRGB(i,j,p);
}
}
}


}