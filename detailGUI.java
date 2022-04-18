import com.google.gson.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class detailGUI extends JPanel {
    public movieDetails details;
    String requestString;
    String responseString;

    public detailGUI(){

    }

    public detailGUI(String id){
        requestString = "https://imdb-api.com/en/API/Title/k_89q53wtm/" + id.strip();
        requestString = requestString.replaceAll(" ", "%20");
        System.out.println("API Request:: " + requestString);

        try{
            responseString = stringFromURL(requestString);
            System.out.println("API Response:: " + responseString);
            stringToObject(responseString);
            System.out.println("movieDetails object created");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.add(new JLabel("Hello"));

    }

    //parses json string to appropriate objects
    public void stringToObject(String raw) throws Exception{
        Gson gson = new Gson();
        details = gson.fromJson(raw,movieDetails.class);

       // System.out.println("The title: " + details.title);

        try (Writer writer = new FileWriter("Output.json")) {
            Gson ggson = new GsonBuilder().create();
            ggson.toJson(details, writer);
        }
    }



    /*
        1-performs GET request to the indicated URL.
        2-returns the response as a raw,unparsed string
     */
    private String stringFromURL(String urlString) throws Exception {
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

    private ImageIcon getImgFromURL(String stringURL){

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

        resized = img.getScaledInstance(200, 400, Image.SCALE_SMOOTH);


        posterIcon = new ImageIcon(resized);
        return posterIcon;

    }
}
