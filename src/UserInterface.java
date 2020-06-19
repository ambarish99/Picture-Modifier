import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.filechooser.*;

import org.opencv.core.*;
import org.opencv.imgcodecs.*;

//import com.mortennobel.imagescaling.ResampleOp;

public class UserInterface implements ActionListener
{
private int initialWidth;
private	int initialHeight;
private int red,green,blue,hue,contrast,brightness;
private float panelHeight;
private float panelWidth;     



private JMenuItem grayScaleMenuItem,newMenuItem,saveMenuItem,saveAsMenuItem,negativeMenuItem,TVCaptureMenuItem,dotPortraitMenuItem,pencilSketchMenuItem;
private JMenuBar mb;
private JMenu editMenu,fileMenu;
private JLabel imageLabel,editOptionsLabel,zoomLabel,redLabel,greenLabel,blueLabel,hueLabel,contrastLabel,brightnessLabel;
private JPanel imagePanel,sidePanel,bottomEditPanel,rotatePanel;
private BufferedImage image;
private BufferedImage resetImg;
private Image tempImg;
private JScrollPane scrollPane;
private double zoom = 1.0;
private JFrame mainFrame;
private JSlider zoomSlider,redSlider,blueSlider,greenSlider,hueSlider,brightnessSlider,contrastSlider;
private Icon rotateLeftIcon,rotateRightIcon;
private JButton rotateLeftButton,rotateRightButton;
private File saveFile;
private  String extension;
UserInterface() 
{
System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

mainFrame=new JFrame();
mb=new JMenuBar();

newMenuItem=new JMenuItem("new");
newMenuItem.addActionListener(this);
saveMenuItem=new JMenuItem("save");
saveMenuItem.addActionListener(this);
saveMenuItem.setEnabled(false);
saveAsMenuItem=new JMenuItem("save as");
saveAsMenuItem.addActionListener(this);
saveAsMenuItem.setEnabled(false);

grayScaleMenuItem=new JMenuItem("gray scale");
grayScaleMenuItem.addActionListener(this);
negativeMenuItem=new JMenuItem("negative");
negativeMenuItem.addActionListener(this);
TVCaptureMenuItem=new JMenuItem("TV Capture");
TVCaptureMenuItem.addActionListener(this);
dotPortraitMenuItem=new JMenuItem("dot portrait");
dotPortraitMenuItem.addActionListener(this);
pencilSketchMenuItem=new JMenuItem("pencil sketch");
pencilSketchMenuItem.addActionListener(this);

fileMenu=new JMenu("file");
editMenu=new JMenu("edit");
editMenu.add(grayScaleMenuItem);
editMenu.add(negativeMenuItem);
editMenu.add(TVCaptureMenuItem);
editMenu.add(dotPortraitMenuItem);
editMenu.add(pencilSketchMenuItem);
fileMenu.add(newMenuItem);
fileMenu.add(saveMenuItem);
fileMenu.add(saveAsMenuItem);
mb.add(fileMenu);
mb.add(editMenu);
mainFrame.setJMenuBar(mb);

Border blackLine = BorderFactory.createLineBorder(Color.black);
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
mainFrame.setTitle("Image editor");
mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
mainFrame.setLayout(new BorderLayout());


imageLabel=new JLabel(" ");
//imageLabel.setPreferredSize(new Dimension(5*screenSize.width/6, 29*screenSize.height/30));
imagePanel=new JPanel();
imagePanel.setBorder(blackLine);
imagePanel.setBackground(Color.BLACK);
//imagePanel.setPreferredSize(new Dimension(5*screenSize.width/6, 29*screenSize.height/30));
imagePanel.add(imageLabel,BorderLayout.CENTER);
mainFrame.add(imagePanel,BorderLayout.CENTER);

scrollPane = new JScrollPane(imagePanel);
scrollPane.setViewportView(imagePanel);
//scrollPane.setPreferredSize(new Dimension(5*screenSize.width/6, 29*screenSize.height/30));
mainFrame.add(scrollPane, BorderLayout.CENTER);

/*        
        imagePanel.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                double temp = zoom - (notches * 0.2);
                // minimum zoom factor is 1.0
                temp = Math.max(temp, 1.0);
                if (temp != zoom) {
                    zoom = temp;
                    resizeImage();
                }
            }
        });
*/

editOptionsLabel=new JLabel("Edit options",SwingConstants.CENTER);
editOptionsLabel.setPreferredSize(new Dimension(screenSize.width/6, 30));
editOptionsLabel.setBorder(blackLine);
editOptionsLabel.setFont(new Font("Arial", Font.PLAIN, 25));

zoomSlider=new JSlider(JSlider.HORIZONTAL,0,200,100);
zoomSlider.setMinorTickSpacing(5);  
zoomSlider.setMajorTickSpacing(20);  
zoomSlider.setPaintTicks(true);  
zoomSlider.setPaintLabels(true);
zoomSlider.addChangeListener(e -> resizeImage());
zoomSlider.setEnabled(false);  

zoomLabel=new JLabel("zoom",SwingConstants.CENTER);

rotateLeftIcon=new ImageIcon("left2.png");
rotateLeftButton=new JButton(rotateLeftIcon);
rotateLeftButton.addActionListener(this);

rotateRightIcon=new ImageIcon("right2.png");
rotateRightButton=new JButton(rotateRightIcon);
rotateRightButton.addActionListener(this);

rotatePanel=new JPanel();
rotatePanel.setLayout(new GridLayout(1,2));
rotatePanel.add(rotateLeftButton);
rotatePanel.add(rotateRightButton);


redSlider=new JSlider(JSlider.HORIZONTAL,-100,100,0);
redSlider.setMinorTickSpacing(5);  
redSlider.setMajorTickSpacing(20);  
redSlider.setPaintTicks(true);  
redSlider.setPaintLabels(true);
redSlider.addChangeListener(e -> redComponent());
redSlider.setEnabled(false);  

redLabel=new JLabel("red",SwingConstants.CENTER);


greenSlider=new JSlider(JSlider.HORIZONTAL,-100,100,0);
greenSlider.setMinorTickSpacing(5);  
greenSlider.setMajorTickSpacing(20);  
greenSlider.setPaintTicks(true);  
greenSlider.setPaintLabels(true);
greenSlider.addChangeListener(e -> greenComponent());
greenSlider.setEnabled(false);  

greenLabel=new JLabel("green",SwingConstants.CENTER);



blueSlider=new JSlider(JSlider.HORIZONTAL,-100,100,0);
blueSlider.setMinorTickSpacing(5);  
blueSlider.setMajorTickSpacing(20);  
blueSlider.setPaintTicks(true);  
blueSlider.setPaintLabels(true);
blueSlider.addChangeListener(e -> blueComponent());
blueSlider.setEnabled(false);  

blueLabel=new JLabel("blue",SwingConstants.CENTER);


/*
hueSlider=new JSlider(JSlider.HORIZONTAL,-100,100,0);
hueSlider.setMinorTickSpacing(5);  
hueSlider.setMajorTickSpacing(20);  
hueSlider.setPaintTicks(true);  
hueSlider.setPaintLabels(true);
hueSlider.addChangeListener(e -> hueComponent());
hueSlider.setEnabled(false);  
hueLabel=new JLabel("hue",SwingConstants.CENTER);
*/


contrastSlider=new JSlider(JSlider.HORIZONTAL,0,100,20);
contrastSlider.setMinorTickSpacing(5);  
contrastSlider.setMajorTickSpacing(20);  
contrastSlider.setPaintTicks(true);  
contrastSlider.setPaintLabels(true);
contrastSlider.addChangeListener(e -> contrastComponent());
contrastSlider.setEnabled(false);  

contrastLabel=new JLabel("contrast",SwingConstants.CENTER);



brightnessSlider=new JSlider(JSlider.HORIZONTAL,0,200,100);
brightnessSlider.setMinorTickSpacing(5);  
brightnessSlider.setMajorTickSpacing(20);  
brightnessSlider.setPaintTicks(true);  
brightnessSlider.setPaintLabels(true);
brightnessSlider.addChangeListener(e -> brightnessComponent());
brightnessSlider.setEnabled(false);  

brightnessLabel=new JLabel("brightness",SwingConstants.CENTER);



bottomEditPanel=new JPanel();
bottomEditPanel.setLayout(new GridLayout(15,1));
bottomEditPanel.add(rotatePanel);
//bottomEditPanel.add(hueLabel);
//bottomEditPanel.add(hueSlider);
bottomEditPanel.add(contrastLabel);
bottomEditPanel.add(contrastSlider);
bottomEditPanel.add(brightnessLabel);
bottomEditPanel.add(brightnessSlider);

bottomEditPanel.add(redLabel);
bottomEditPanel.add(redSlider);
bottomEditPanel.add(greenLabel);
bottomEditPanel.add(greenSlider);
bottomEditPanel.add(blueLabel);
bottomEditPanel.add(blueSlider);
bottomEditPanel.add(zoomLabel);
bottomEditPanel.add(zoomSlider);

sidePanel=new JPanel();
sidePanel.setLayout(new BorderLayout());
sidePanel.setBorder(blackLine);
//sidePanel.setBackground(Color.GRAY);
sidePanel.add(editOptionsLabel,BorderLayout.NORTH);
sidePanel.add(bottomEditPanel,BorderLayout.SOUTH);
mainFrame.add(sidePanel,BorderLayout.EAST);

     


mainFrame.setVisible(true);
}//constructer ends






public void actionPerformed(ActionEvent ev)
{

if(ev.getSource()==newMenuItem)
{
 JFileChooser fc = new JFileChooser();
 int result = fc.showOpenDialog(null);
 if (result == JFileChooser.APPROVE_OPTION) 
 {
   saveAsMenuItem.setEnabled(true);
   File file = fc.getSelectedFile();
   try 
   {
     this.image=ImageIO.read(file);
     this.imageLabel.setIcon(null);
this.panelHeight=(float)imagePanel.getHeight();
this.panelWidth=(float)imagePanel.getWidth();
     fitImage(image);
     imagePanel.setBackground(Color.BLACK);
   resetAll();
     imagePanel.revalidate();
     imagePanel.updateUI();

   } catch (IOException e) 
   {
      e.printStackTrace();
   }
 }
}


if(ev.getSource()==grayScaleMenuItem)
{
this.image=GrayScale.convert(this.image);
//this.resetImg=GrayScale.convert(this.resetImg);
//Icon imageIcon = new ImageIcon(resetImg);
//imageLabel.setIcon(imageIcon);
fitImage(this.image);
imagePanel.updateUI();
}


if(ev.getSource()==negativeMenuItem)
{
NegativeImage.convert(image);
fitImage(image);
imagePanel.updateUI();
}

if(ev.getSource()==TVCaptureMenuItem)
{
TVCapture.create(image);
fitImage(image);
imagePanel.updateUI();
}


if(ev.getSource()==dotPortraitMenuItem)
{
DotPortrait.convert(image);
fitImage(image);
imagePanel.updateUI();
}

if(ev.getSource()==pencilSketchMenuItem)
{
this.image=PencilSketch.convert(this.image);
fitImage(this.image);
imagePanel.updateUI();
}



if(ev.getSource()==rotateLeftButton)
{
this.image=Rotate.left(this.image);
fitImage(image);
imagePanel.updateUI();
}

if(ev.getSource()==rotateRightButton)
{
image=Rotate.right(image);
fitImage(image);
imagePanel.updateUI();
}

if(ev.getSource()==saveAsMenuItem)
{
JFileChooser fc = new JFileChooser();
fc.setDialogTitle("Specify a file to save");
fc.setCurrentDirectory(new File("E:\\Camera"));
fc.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
fc.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
fc.addChoosableFileFilter(new FileNameExtensionFilter("jpeg", "jpeg"));

int result = fc.showSaveDialog(null);
if(result==JFileChooser.APPROVE_OPTION)
{
saveMenuItem.setEnabled(true);
String ext= "";
extension= fc.getFileFilter().getDescription();
  if (extension.equals("jpg")) 
  {
    ext= ".jpg";
  } else if (extension.equals("png")) 
   {
    ext= ".png";
   } else if (extension.equals("jpeg")) 
    {
    ext= ".jpeg";
    }
try
{
saveFile=fc.getSelectedFile();
saveFile=new File(saveFile.getAbsolutePath()+ext);
System.out.println("save file path=="+saveFile.getAbsolutePath());
ImageIO.write(image,extension,saveFile);
}catch(Exception e)
{
e.printStackTrace();
}
}

}


if(ev.getSource()==saveMenuItem)
{
try
{
ImageIO.write(image,extension,saveFile);
}catch(Exception e)
{
e.printStackTrace();
}
}

}


public void resizeImage() 
{ 
     int zoomHeight=0,zoomWidth=0;
     zoomSlider.setEnabled(false);
    //ResampleOp  resampleOp = new ResampleOp((int)(resetImg.getWidth()*zoom), (int)(resetImg.getHeight()*zoom));
    //this.resetImg = resampleOp.filter(resetImg, null);
        if(zoomSlider.getValue()==0)
        {
          zoomHeight=(int)(initialHeight*0.01);
          zoomWidth=(int)(initialWidth*0.01); 
        } 
        else
        {
         zoomHeight=(int)(this.initialHeight*zoomSlider.getValue()*0.01);
         zoomWidth=(int)(this.initialWidth*zoomSlider.getValue()*0.01);
        }
System.out.println("zoom width="+zoomWidth+",  zoom height"+zoomHeight);
        this.tempImg = resetImg.getScaledInstance(zoomWidth, zoomHeight,Image.SCALE_FAST); 
        //int w=tempImg.getWidth(null);
        //int h=tempImg.getHeight(null);
        this.resetImg=new BufferedImage(zoomWidth,zoomHeight,BufferedImage.TYPE_INT_ARGB);
	  Graphics2D bGr = resetImg.createGraphics();
          bGr.drawImage(tempImg, 0, 0, null);
          bGr.dispose();

Icon imageIcon = new ImageIcon(resetImg);
imageLabel.setIcon(imageIcon);
imagePanel.updateUI();
zoomSlider.setEnabled(true);
resetImg=image;
}




public void redComponent()
{
if(!redSlider.getValueIsAdjusting())
{
imagePanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
redSlider.setEnabled(false);
BufferedImage temp=copyImage(image);
this.red=(int)(redSlider.getValue());
temp=RGB.setRGB(temp,red,green,blue);
fitImage(temp);
imagePanel.updateUI();
redSlider.setEnabled(true);
imagePanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
}
}

public void greenComponent()
{
if(!greenSlider.getValueIsAdjusting())
{
greenSlider.setEnabled(false);
BufferedImage temp=copyImage(image);
this.green=(int)(greenSlider.getValue());
temp=RGB.setRGB(temp,red,green,blue);
fitImage(temp);
imagePanel.updateUI();
greenSlider.setEnabled(true);
}
}

public void blueComponent()
{
if(!blueSlider.getValueIsAdjusting())
{
BufferedImage temp=copyImage(image);
blueSlider.setEnabled(false);
this.blue=(int)(blueSlider.getValue());
temp=RGB.setRGB(temp,red,green,blue);
fitImage(temp);
imagePanel.updateUI();
blueSlider.setEnabled(true);
}
}


/*
public void hueComponent()
{
if(!hueSlider.getValueIsAdjusting())
{
BufferedImage temp=copyImage(image);
hueSlider.setEnabled(false);
this.hue=(int)(hueSlider.getValue());
temp=HSB.setHSB(temp,hue,contrast,brightness);
fitImage(temp);
imagePanel.updateUI();
hueSlider.setEnabled(true);
}
}
*/

public void contrastComponent()
{
if(!contrastSlider.getValueIsAdjusting())
{
BufferedImage temp=copyImage(image);
contrastSlider.setEnabled(false);
this.contrast=(int)(contrastSlider.getValue());
temp=HSB.setHSB(temp,hue,contrast,brightness);
fitImage(temp);
imagePanel.updateUI();
contrastSlider.setEnabled(true);
}
}



public void brightnessComponent()
{
if(!brightnessSlider.getValueIsAdjusting())
{
BufferedImage temp=copyImage(image);
brightnessSlider.setEnabled(false);
this.brightness=(int)(brightnessSlider.getValue());
temp=HSB.setHSB(temp,hue,contrast,brightness);
fitImage(temp);
imagePanel.updateUI();
brightnessSlider.setEnabled(true);
}
}






public void fitImage(BufferedImage image)
{

     float height=(float)image.getHeight();
     float width=(float)image.getWidth();
//this.panelHeight=(float)imagePanel.getHeight();
//this.panelWidth=(float)imagePanel.getWidth();
     float extraHeightPercent=((height/panelHeight)*100)-100;
     float extraWidthPercent=((width/panelWidth)*100)-100;


     if(extraHeightPercent>0 || extraWidthPercent>0)
     {
       if((height>width && panelWidth>panelHeight) || (height<width && panelWidth<panelHeight))
       {
	if(height>width && panelWidth>panelHeight)
	{
         int resetHeight=(int)(panelHeight);
         int resetWidth=(int)((width)*(panelHeight/height));
         this.tempImg = image.getScaledInstance(resetWidth, resetHeight,Image.SCALE_SMOOTH); 
         int w=tempImg.getWidth(null);
         int h=tempImg.getHeight(null);
         this.resetImg=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);

	  Graphics2D bGr = resetImg.createGraphics();
          bGr.drawImage(tempImg, 0, 0, null);
          bGr.dispose();
     this.initialWidth=resetImg.getWidth();
     this.initialHeight=resetImg.getHeight();

         ImageIcon ii=new ImageIcon(resetImg);
         imageLabel.setIcon(ii);     
	}

        if(height<width && panelWidth<panelHeight)
	{
         //int resetHeight=(int)((height/100)*(panelWidth*100/width));
         //int resetWidth=(int)((width/100)*(panelWidth*100/width));
         int resetWidth=(int)(panelWidth);
         int resetHeight=(int)((height)*(panelWidth/width));
         this.tempImg = image.getScaledInstance(resetWidth, resetHeight,Image.SCALE_SMOOTH); 
         int w=tempImg.getWidth(null);
         int h=tempImg.getHeight(null);
         this.resetImg=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);

	  Graphics2D bGr = resetImg.createGraphics();
          bGr.drawImage(tempImg, 0, 0, null);
          bGr.dispose();
     this.initialWidth=resetImg.getWidth();
     this.initialHeight=resetImg.getHeight();

         ImageIcon ii=new ImageIcon(resetImg);
         imageLabel.setIcon(ii);
	}
       }
       else
       {
        if(extraHeightPercent>extraWidthPercent) 
        {
         //int resetHeight=(int)(height-((height/100)*extraHeightPercent));
	 int resetHeight=(int)(panelHeight);
         //int resetWidth=(int)(width-((width/100)*extraHeightPercent));
	 int resetWidth=(int)((width/height)*(panelHeight));
         this.tempImg = image.getScaledInstance(resetWidth, resetHeight,Image.SCALE_SMOOTH); 
         int w=tempImg.getWidth(null);
         int h=tempImg.getHeight(null);
         this.resetImg=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);

	  Graphics2D bGr = resetImg.createGraphics();
          bGr.drawImage(tempImg, 0, 0, null);
          bGr.dispose();
     this.initialWidth=resetImg.getWidth();
     this.initialHeight=resetImg.getHeight();

         ImageIcon ii=new ImageIcon(resetImg);
         imageLabel.setIcon(ii);     
        }

        if(extraWidthPercent>extraHeightPercent) 
        {
         //int resetHeight=(int)(height-((height/100)*extraWidthPercent));
         //int resetWidth=(int)(width-((width/100)*extraWidthPercent));
	 int resetWidth=(int)(panelWidth);
         int resetHeight=(int)((height/width)*(panelWidth));
         this.tempImg = image.getScaledInstance(resetWidth, resetHeight,Image.SCALE_SMOOTH); 
         int w=tempImg.getWidth(null);
         int h=tempImg.getHeight(null);
         this.resetImg=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
 
	  Graphics2D bGr = resetImg.createGraphics();
          bGr.drawImage(tempImg, 0, 0, null);
          bGr.dispose();

     this.initialWidth=resetImg.getWidth();
     this.initialHeight=resetImg.getHeight();  

         ImageIcon ii=new ImageIcon(resetImg);
         imageLabel.setIcon(ii);     
        }
       }            
     }
     else
     {
      resetImg=image;

     this.initialWidth=resetImg.getWidth();
     this.initialHeight=resetImg.getHeight();

     ImageIcon ii=new ImageIcon(resetImg);
     imageLabel.setIcon(ii);     
     }
}



private BufferedImage copyImage(BufferedImage source){
    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
    Graphics2D g = b.createGraphics();
    g.drawImage(source, 0, 0, null);
    g.dispose();
    return b;
}


private void resetAll()
{
     zoomSlider.setEnabled(true);
     redSlider.setEnabled(true);
     greenSlider.setEnabled(true);
     blueSlider.setEnabled(true);
//     hueSlider.setEnabled(true);
     contrastSlider.setEnabled(true);
     brightnessSlider.setEnabled(true);
zoomSlider.setValue(100);
     redSlider.setValue(0);     
     greenSlider.setValue(0);
     blueSlider.setValue(0);
//     hueSlider.setValue(0);
     contrastSlider.setValue(20);
     brightnessSlider.setValue(100);
red=0;green=0;blue=0;hue=0;contrast=0;brightness=0;
      
}


}

class test
{
public static void main(String aa[])
{
UserInterface ui;
ui=new UserInterface();
}
}


