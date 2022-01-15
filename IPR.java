import javax.swing.JFrame;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

public class IPR {

    /**
     * Displays the image in a window and keeps it open till the user closes it.
     * 
     * @param title  the title of the image
     * @param pixels the image to be displayed as a 2D array of pixels
     */
    public static void imageShow(String title, int[][] pixels) {

        var h = pixels.length;
        var w = pixels[0].length;

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < w; ++x) {
            for (int y = 0; y < h; ++y) {
                img.setRGB(x, y, pixels[y][x]); // flip u,v here!
            }
        }

        JFrame frame = new JFrame();
        frame.setSize(512, 512 * h / w);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title + " (" + w + "x" + h + ")");
        var label = new JLabel();
        var icon = new ImageIcon(img.getScaledInstance(512, 512 * h / w, java.awt.Image.SCALE_FAST));
        label.setIcon(icon);
        label.setSize(512, 512);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        frame.repaint();
    }

}
