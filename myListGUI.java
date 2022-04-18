import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class myListGUI extends JPanel {

    myList mainList;

    public myListGUI(){
        this.setLayout(new GridBagLayout());
        this.setBorder(LineBorder.createBlackLineBorder());
        populateMyListGUI();
        //populateMyList();
    }

    private void populateMyList() {
        Gson gson = new Gson();
        try{
            Reader reader = Files.newBufferedReader(Paths.get("samplelist.json"));
            mainList = gson.fromJson(reader, myList.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("myList object created");


    }

    public void populateMyListGUI(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
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
        c.weighty = 1;
        this.add(new searchResultGUI("https://i.pinimg.com/originals/37/ec/34/37ec346f9cd8b1097f2cd821e208e72e.jpg", "The cat", "12345"), c);

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
        this.add(new searchResultGUI("https://i.pinimg.com/originals/37/ec/34/37ec346f9cd8b1097f2cd821e208e72e.jpg", "The rat", "12345"), c);


    }

}
