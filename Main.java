import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;


public class Main extends JFrame implements essentialFunctions, ActionListener{
    Container contentPane;
    JTabbedPane myTabbedPane;
    searchGUI searchPanel;
    public static myListGUI myListPanel;
    public static myList masterList;
    JLabel timeStamp;
    Main me;
    JMenuItem eng, esp, jap, chi;
    Locale mylocale;
    ResourceBundle bundle;
    
    public ResourceBundle getBundle(){
        return bundle;
    }
    
    public void refresh(String language){
        if (language == "zh"){
            mylocale = new Locale("zh", "CN");
        }
        else if (language == "ja"){
            mylocale = new Locale("ja", "JP");
        }
        else if (language == "es"){
            mylocale = new Locale("es", "MX");
        }
        else {
            mylocale = Locale.getDefault();
        }
        contentPane.removeAll();
        this.generateGUI(mylocale);
        this.setSize(700,700);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    public void generateGUI(Locale mylocale){
        GridBagConstraints frameConstraints = new GridBagConstraints();

        //creates the main container for the entire GUI
        bundle = ResourceBundle.getBundle("CNIT325/words", mylocale);
        
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        masterList = new myList();
        
        searchPanel = new searchGUI(this, bundle);
        myListPanel = new myListGUI();
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu languageMenu = new JMenu(bundle.getString("language"));
        menuBar.add(languageMenu);
        System.out.println("here");  
        eng = new JMenuItem(bundle.getString("English"));
        eng.addActionListener(this);
        languageMenu.add(eng);
        esp = new JMenuItem(bundle.getString("Spanish"));
        esp.addActionListener(this);
        languageMenu.add(esp);
        jap = new JMenuItem(bundle.getString("Japanese"));
        jap.addActionListener(this);
        languageMenu.add(jap);
        chi = new JMenuItem(bundle.getString("Chinese"));
        chi.addActionListener(this);
        languageMenu.add(chi);
        
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
        myTabbedPane.addTab(bundle.getString("Discover"), scrollPaneSearch);
        myTabbedPane.addTab(bundle.getString("ML"), scrollPaneMyList);
        myTabbedPane.setBackgroundAt(0, new Color(114, 50, 50));
        myTabbedPane.setBackgroundAt(1, new Color(39, 108, 108));
        myTabbedPane.setBorder(BorderFactory.createEmptyBorder());
        myTabbedPane.setForegroundAt(0, new Color(255, 255, 255));
        myTabbedPane.setForegroundAt(1, new Color(255, 255, 255));
        contentPane.add(myTabbedPane,frameConstraints);

        timeStamp = new JLabel();
        timeStamp.setText(bundle.getString("LS") + ":");
        frameConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameConstraints.ipady = 10;       //reset to default
        frameConstraints.weighty = 1.0;   //request any extra vertical space
        frameConstraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
        frameConstraints.gridx = 0;       //aligned with button 2
        frameConstraints.insets = new Insets(0,450,0,0);  //top padding
        this.add(timeStamp, frameConstraints);
    }


    public Main(String language){
        System.out.println("here");
        Locale mylocale; 
        // Create frame and output to screen
        if (language == "zh"){
            mylocale = new Locale("zh", "CN");
        }
        if (language == "ja"){
            mylocale = new Locale("ja", "JP");
        }
        else if (language == "es"){
            mylocale = new Locale("es", "MX");
        }
        else {
            mylocale = Locale.getDefault();
        }
        this.generateGUI(mylocale);

    }
    
    public void actionPerformed(ActionEvent evt) {
        
        if (evt.getSource() == eng) {
            this.refresh("en");
        }
        if (evt.getSource() == esp) {
            this.refresh("es");
        }
        if (evt.getSource() == jap) {
            this.refresh("ja");
        }
        if (evt.getSource() == chi) {
            this.refresh("zh");
        }
    }

    public static void main(String[] args) {
        Main me;
        if (args.length != 1) {
            me = new Main("en"); 
        }else{
            me = new Main(args[0]);
        }
        me.setSize(700,700);
        me.setVisible(true);
        me.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        me.setLocationRelativeTo(null);
    }
}

