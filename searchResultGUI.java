import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class searchResultGUI extends JPanel implements ActionListener {

    public String id = "";
    public String resultType = "";
    public String image = "";
    public String title = "";
    public String description = "";
    public JLabel posterLabel;
    public JButton titleButton;
    public Main parentClass;


    public searchResultGUI(String imageURL, String Title, String id){
        this.id = id;
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(560,200));
        this.setBackground(Color.WHITE);

        posterLabel = new JLabel();
        posterLabel.setIcon(essentialFunctions.getImgFromURL(imageURL, 133, 200));
        posterLabel.setBorder(LineBorder.createBlackLineBorder());
        posterLabel.setPreferredSize(new Dimension(133,200));

        titleButton = new JButton();
        titleButton.setText("<html>" + Title + "</html>");
        titleButton.setBackground(Color.WHITE);
        titleButton.setBorder(BorderFactory.createEmptyBorder());
        //titleButton.setSize(new Dimension(200,180));
        titleButton.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.ipady=0;
        c.ipadx=0;
        c.weightx = 0;
        c.weighty = 0;
        this.add(posterLabel,c);

        c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=0;
        c.ipady=160;
        c.ipadx=0;
        c.weightx = 1;
        c.weighty = 1;
        this.add(titleButton,c);

        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                Color mycolor = new Color(161, 75, 75);
                titleButton.setBackground(mycolor);
                searchResultGUI.super.setBackground(mycolor);
            }

            public void mouseExited(MouseEvent e) {
                titleButton.setBackground(Color.WHITE);
                searchResultGUI.super.setBackground(Color.WHITE);
            }

        });

        titleButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                Color mycolor = new Color(161, 75, 75);
                titleButton.setBackground(mycolor);
                searchResultGUI.super.setBackground(mycolor);
            }

            public void mouseExited(MouseEvent e) {
                titleButton.setBackground(Color.WHITE);
                searchResultGUI.super.setBackground(Color.WHITE);
            }

        });

    }

    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == titleButton ){
            System.out.println("Title Button pressed!!!!");
            JFrame popUp = new JFrame();
            popUp.setSize(700,700);
            System.out.println(this.id);
            detailGUI testPanel = new detailGUI(this.id);
            JScrollPane scrollPaneDetails = new JScrollPane(testPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPaneDetails.setPreferredSize(new Dimension(700,700));
            scrollPaneDetails.getVerticalScrollBar().setUnitIncrement(10);
            popUp.add(scrollPaneDetails);
            popUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            popUp.setVisible(true);
            popUp.setLocationRelativeTo(null);

        }
    }

}
