import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import com.mortennobel.imagescaling.ResampleOp;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageZoom {

    private double zoom = 1.0;  

    private void initialize() throws IOException {
        
        JScrollPane scrollPane = new JScrollPane();---------------------------------
        getContentPane().add(scrollPane, BorderLayout.CENTER);------------------------
        
        
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
        scrollPane.setViewportView(imagePanel);
    }
    
    public void resizeImage() {
           System.out.println(zoom+"----------------------");
           ResampleOp  resampleOp = new ResampleOp((int)(resetImg.getWidth()*zoom), (int)(resetImg.getHeight()*zoom));
              this.resetImg = resampleOp.filter(resetImage, null);
           Icon imageIcon = new ImageIcon(resetImg);
           imageLabel.setIcon(imageIcon);
        }


}