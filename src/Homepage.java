import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

public class Homepage {
    
    public Homepage() {
        JFrame homepage = new JFrame("WebWise HomePage");
        homepage.setSize(750,500);
        homepage.setLayout(null);

        //creates workwise title
        JLabel workwise = new JLabel("WORKWISE");
        workwise.setFont(new Font("Open Sans", Font.BOLD, 22));
        workwise.setForeground(Color.RED);
        workwise.setBounds(300,50,300,30);
        
        //company list and search bar creation
        String companies[] = {"Search","Google", "Amazon", "Meta","Microsoft"};
        JComboBox searchBar = new JComboBox<>(companies);
        searchBar.setBounds(200,100,300,30);

        //image creation on homepage
        JLabel jobImageLabel = new JLabel();
        ImageIcon jobImageIcon = new ImageIcon("src/Images/WorkwiseJobImage.jpeg");
        Image jobImage = jobImageIcon.getImage().getScaledInstance(275,250, Image.SCALE_SMOOTH);
        jobImageLabel.setBounds(75,175,275,250);
        jobImageIcon = new ImageIcon(jobImage);
        jobImageLabel.setIcon(jobImageIcon);
        JLabel jobImageCaption = new JLabel("Find the Best Fit Company for You");
        jobImageCaption.setBounds(95,385,275,100);

        //image creation on homepage
        JLabel jobMarketLabel = new JLabel();
        ImageIcon jobMarketImageIcon = new ImageIcon("src/Images/jobMarketImage.jpeg");
        Image jobMarketImage = jobMarketImageIcon.getImage().getScaledInstance(275, 250, Image.SCALE_SMOOTH);
        jobMarketLabel.setBounds(385, 175, 275, 250);
        jobMarketImageIcon = new ImageIcon(jobMarketImage);
        jobMarketLabel.setIcon(jobMarketImageIcon);
        JLabel jobMarketCaption = new JLabel("Maneuver the Job Market");
        jobMarketCaption.setBounds(430,385,275,100);

        //add created pages to homepage
        homepage.add(workwise);
        homepage.add(searchBar);
        homepage.add(jobImageLabel); homepage.add(jobMarketLabel);
        homepage.add(jobImageCaption); homepage.add(jobMarketCaption);
        homepage.setVisible(true);
        
        //handle searching for company
        searchCompany(searchBar);
    }

    //opens up review page if a company is selected
    public void searchCompany(JComboBox searchBar) {
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String searchInput = (String) searchBar.getSelectedItem();
                if (searchInput != "Search") {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            MockupReviewPage reviewView = new MockupReviewPage(searchInput);
                            reviewView.setVisible(true);
                        }
                    });
                }
            }
        });
    }
}

