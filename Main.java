import java.awt.*;
import javax.swing.*;


public class Main extends JFrame{
    private static final String IMG_PATH = "C:\\Users\\toelk\\Pictures\\batman.jpg";
    Container contentPane;
    JTabbedPane myTabbedPane;
    searchGUI searchPanel;
    myListGUI myListPanel;
    JLabel timeStamp;


    public Main(){
        
        GridBagConstraints frameConstraints = new GridBagConstraints();

        //creates the main container for the entire GUI
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        //creates new blank
        searchPanel = new searchGUI(this);
        myListPanel = new myListGUI();

        //
        JScrollPane scrollPaneSearch = new JScrollPane(searchPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSearch.setPreferredSize(new Dimension(600,600));
        scrollPaneSearch.getVerticalScrollBar().setUnitIncrement(10);

        JScrollPane scrollPaneMyList = new JScrollPane(myListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMyList.setPreferredSize(new Dimension(600,600));
        scrollPaneMyList.getVerticalScrollBar().setUnitIncrement(10);

        frameConstraints.gridx=0;
        frameConstraints.gridy=1;
        frameConstraints.weighty=1;
        UIManager.put("TabbedPane.selected", Color.BLACK);
        myTabbedPane = new JTabbedPane();
        myTabbedPane.setPreferredSize(new Dimension(600, 600));
        myTabbedPane.setMinimumSize(new Dimension(600, 600));
        myTabbedPane.addTab(" Discover ", scrollPaneSearch);
        myTabbedPane.addTab(" My List ", scrollPaneMyList);
        myTabbedPane.setBackgroundAt(0, new Color(114, 50, 50));
        myTabbedPane.setBackgroundAt(1, new Color(39, 108, 108));
        myTabbedPane.setBorder(BorderFactory.createEmptyBorder());
        myTabbedPane.setForegroundAt(0, new Color(255, 255, 255));
        myTabbedPane.setForegroundAt(1, new Color(255, 255, 255));
        contentPane.add(myTabbedPane,frameConstraints);
        //contentPane.add(scrollPane, frameConstraints);
        
        timeStamp = new JLabel();
        timeStamp.setText("Last Search:");
        frameConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameConstraints.ipady = 10;       //reset to default
        frameConstraints.weighty = 1.0;   //request any extra vertical space
        frameConstraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
        frameConstraints.gridx = 0;       //aligned with button 2
        frameConstraints.insets = new Insets(0,450,0,0);  //top padding
        this.add(timeStamp, frameConstraints);
    }


    public static void main(String[] args) {
        Main me = new Main();
        me.setSize(700,700);
        me.setVisible(true);
        me.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        me.setLocationRelativeTo(null);
    }
}

