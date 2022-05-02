import com.google.gson.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class detailGUI extends JPanel{
    public movieDetails details;
    String requestString;
    String responseString;

    public detailGUI(){

    }

    public detailGUI(String id){
        requestString = "https://imdb-api.com/en/API/Title/k_89q53wtm/" + id.strip();
        requestString = requestString.replaceAll(" ", "%20");
        System.out.println("API Request:: " + requestString);
        this.setMaximumSize(new Dimension(700,700));

        try{
            responseString = essentialFunctions.stringFromURL(requestString);
            System.out.println("API Response:: " + responseString);
            stringToObject(responseString);
            System.out.println("movieDetails object created");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        JButton haveWatched = new JButton("I have watched this");
        haveWatched.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listitem itemToAdd = new listitem();
                itemToAdd.description = details.year;
                itemToAdd.id = details.id;
                itemToAdd.image = details.image;
                itemToAdd.title = details.title;
                System.out.println("Id:" + itemToAdd.id);
                Main.masterList.addToList(itemToAdd);
                Main.masterList.writeToFile();
                Main.myListPanel.populateMyListGUI();
            }
        });

        this.setLayout(new MigLayout());
        this.add(new JLabel(essentialFunctions.getImgFromURL(details.image, 200, 400)), "span 2 2");
        this.add(new JLabel("Title: " + details.title), "wrap");
        this.add(new JLabel("IMDb Rating: " + details.imDbRating), "wrap");
        this.add(new JLabel("Description: " + details.plot), "wrap");
        this.add(new JLabel("Cast: " + details.fullCast), "wrap");
        this.add(new JLabel("Director: " + details.directors), "wrap");
        this.add(new JLabel("Run Time:" + details.runtimeMins), "wrap");
        this.add(new JLabel("Awards: " + details.awards), "wrap");
        this.add(new JLabel("Trailer Link: " + details.trailer), "wrap");
        this.add(haveWatched, "wrap");

    }

    //parses json string to appropriate objects
    public void stringToObject(String raw) throws Exception{
        Gson gson = new Gson();
        details = gson.fromJson(raw,movieDetails.class);

    }


}
