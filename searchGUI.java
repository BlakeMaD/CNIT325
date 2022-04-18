import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

import com.google.gson.*;


public class searchGUI extends JPanel implements ActionListener {

    JLabel searchLabel;
    JButton searchButton;
    JTextField searchBar;
    private String responseString, requestString;
    searchResult[] resultArray = new searchResult[50];
    fullSearchResponse responseObj;

    public searchGUI(){
        this.setLayout(new GridBagLayout());
        this.setBorder(LineBorder.createBlackLineBorder());
        populateSearchBar();
    }

    private void clearSearchResults(){
        this.removeAll();
        populateSearchBar();
    }

    private void populateSearchBar(){
        searchLabel = new JLabel();
        searchLabel.setText("Search::");

        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(350, 30));

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(80,20));
        searchButton.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();


        c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=1;
        c.ipadx=0;
        c.ipady=0;
        c.weightx = 1;
        c.weighty = 0;

        this.add(searchLabel, c);

        c.gridx = 1;
        c.ipadx = 10;
        this.add(searchBar);

        c.gridx = 2;
        c.weightx=0;
        this.add(searchButton);
    }

    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == searchButton ){
            String searchString = searchBar.getText();
            //clearSearchResults();
            System.out.println("Search Button pressed!!!!");
            try{
                masterSearch(searchString);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("search worked!!!!");
        }
    }

    // https://i.pinimg.com/originals/37/ec/34/37ec346f9cd8b1097f2cd821e208e72e.jpg

    //clears previous search results


    private void masterSearch(String searchString) throws Exception{
        System.out.println("The search string:" + searchString);
        requestString = "https://imdb-api.com/en/API/SearchMovie/k_89q53wtm/" + searchString.strip();
        requestString = requestString.replaceAll(" ", "%20");
        clearSearchResults();
        System.out.println("API Request:: " + requestString);
        responseString = stringFromURL(requestString);
        System.out.println("API Request:: " + responseString);
        stringToObject(responseString);
        if(responseObj.results == null){
            System.out.println("No Results Found");
            return;
        }
        System.out.println("length of results list:: " + responseObj.results.length);

        int x = 0;
        while(x < responseObj.results.length){
            String myString = responseObj.results[x].title;

            GridBagConstraints c = new GridBagConstraints();
            c.fill=GridBagConstraints.HORIZONTAL;
            c.gridx=0;
            c.gridy=x+1;
            c.gridwidth=3;
            c.gridheight=1;
            c.ipadx=0;
            c.ipady=30;
            c.weightx = 0;
            c.weighty = 1;
            this.add(new searchResultGUI(responseObj.results[x].image,responseObj.results[x].title + "<br>" + responseObj.results[x].description, responseObj.results[x].id), c);
            this.updateUI();
            x++;
        }
    }

    //parses json string to appropriate objects
    public void stringToObject(String raw) throws Exception{
        Gson gson = new Gson();
        responseObj = null;
        responseObj = gson.fromJson(raw,fullSearchResponse.class);
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

        resized = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);


        posterIcon = new ImageIcon(resized);
        return posterIcon;

    }
}


/*

<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.9.0</version>
</dependency>

        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=3;
        c.gridheight=1;
        c.ipadx=0;
        c.ipady=30;
        c.weightx = 0;
        c.weighty = 0;
        this.add(new searchResultGUI("https://i.pinimg.com/originals/37/ec/34/37ec346f9cd8b1097f2cd821e208e72e.jpg", "The BatMAN", "12345"), c);

        c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        c.gridwidth=3;
        c.gridheight=1;
        c.ipadx=0;
        c.ipady=30;
        c.weightx = 0;
        c.weighty = 0;
        this.add(new searchResultGUI("https://i.pinimg.com/originals/37/ec/34/37ec346f9cd8b1097f2cd821e208e72e.jpg", "The BatMAN", "12345"), c);

        c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.gridwidth=3;
        c.gridheight=1;
        c.ipadx=0;
        c.ipady=30;
        c.weightx = 0;
        c.weighty = 1;
        this.add(new searchResultGUI("https://i.pinimg.com/originals/37/ec/34/37ec346f9cd8b1097f2cd821e208e72e.jpg", "The BatMAN", "12345"), c);

        c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=4;
        c.gridwidth=3;
        c.gridheight=1;
        c.ipadx=0;
        c.ipady=30;
        c.weightx = 0;
        c.weighty = 1;
        this.add(new searchResultGUI("https://i.pinimg.com/originals/37/ec/34/37ec346f9cd8b1097f2cd821e208e72e.jpg", "The BatMAN", "12345"), c);

 */
//https://imdb-api.com/en/API/Report/k_89q53wtm/tt0110413