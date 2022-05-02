import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public interface essentialFunctions {

    public static String stringFromURL(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static ImageIcon getImgFromURL(String stringURL, int width, int height){

        BufferedImage img;
        Image resized;
        ImageIcon posterIcon = null;


        try {
            URL url = new URL(stringURL);
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return posterIcon;
        }

        resized = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);


        posterIcon = new ImageIcon(resized);
        return posterIcon;

    }

}
