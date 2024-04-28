import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //create account page 
        JFrame create = new JFrame("WebWise Create Account");
        create.setSize(500,500);
        create.setLayout(null);

        JLabel createAccount = new JLabel("Create Account");
        createAccount.setBounds(50,50,300,30);
         JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50,100,90,30);
        JTextField createUsername = new JTextField();
        createUsername.setBounds(175,100,200,30);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50,150,90,30);
        JTextField createPassword = new JPasswordField();
        createPassword.setBounds(175,150,200,30);
        JLabel verifyLabel = new JLabel("Verify Password:");
        verifyLabel.setBounds(50,200,150,30);
        JTextField verify = new JPasswordField();
        verify.setBounds(175,200,200,30);

        //if username invalid errorUsername.setVisible(true) or create.add(errorUsername)
        JLabel errorUsername = new JLabel("Username already exists please try again");
        errorUsername.setBounds(50,250,300,30);
        //same for errorPassword
        JLabel errorPassword = new JLabel("Password must be longer than 5 characters");
        errorPassword.setBounds(50,300,300,30);
        errorPassword.setBackground(Color.red);

        create.add(createAccount); 
        create.add(userLabel); create.add(createUsername);
        create.add(passLabel); create.add(createPassword);
        create.add(verifyLabel); create.add(verify);
        create.setVisible(true);

        //create login page 
        //once account is created, create.setVisible(false), login.setVisible(true)
        JFrame login = new JFrame("WebWise Login Page");
        login.setSize(500,500);
        login.setLayout(null);
         JLabel loginLabel = new JLabel("Login:");
        loginLabel.setBounds(50,50,300,30);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50,100,90,30);
        JTextField username = new JTextField();
        username.setBounds(150,100,200,30);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50,150,90,30);
        JPasswordField password = new JPasswordField();
        password.setBounds(150,150,200,30);

        //show based on if login is verified login.add(success) or login.add(error)
        JLabel success = new JLabel("Success :D");
        success.setBounds(50,200,150,30);
        JLabel error = new JLabel("Bad :(");
        error.setBounds(50,200,150,30);
        
        login.add(loginLabel);
        login.add(usernameLabel); login.add(username);
        login.add(passwordLabel); login.add(password);
        login.setVisible(true);

        Scanner sc = new Scanner(System.in);
        AccountHandler acc = new AccountHandler();


        System.out.println("\n\nthis is a test of the systems and this is not real user data");
        System.out.println("current data:");
      acc.importUsers();
      acc.dangerMethod();

        // this is setup to test creating acc and logging in
        //create user (createAccount)
        //then log in attempt (LoginAttempt)
        //then display all stored usernames and pass (dangerMethod)
        for (int i = 0; i < 5; i++) {
            System.out.println("\n\nCreate Account");
            acc.createAccount();


            System.out.println("\n\n\nlogin:");
            System.out.println("enter username: ");
            String user = sc.nextLine();
            System.out.println("enter password: ");
            String pass = sc.nextLine();
            if (acc.loginAttempt(user, pass)) {
                System.out.println("Sucessfull Login");
            } else {
                System.out.println("Login Failed");
            }

        acc.dangerMethod();

        }

        //homepage
        //after logged in, bring user to homepage 
        JFrame homepage = new JFrame("WebWise HomePage");
        homepage.setSize(750,500);
        homepage.setLayout(null);

        JLabel workwise = new JLabel("WORKWISE");
        workwise.setFont(new Font("Open Sans", Font.BOLD, 22));
        workwise.setForeground(Color.RED);
        workwise.setBounds(300,50,300,30);
        String companies[] = {"Search","Google", "Amazon", "Meta"};
        JComboBox searchBar = new JComboBox<>(companies);
        searchBar.setBounds(200,100,300,30);

        JLabel jobImageLabel = new JLabel();
        ImageIcon jobImageIcon = new ImageIcon("src/Images/WorkwiseJobImage.jpeg");
        Image jobImage = jobImageIcon.getImage().getScaledInstance(275,250, Image.SCALE_SMOOTH);
        jobImageLabel.setBounds(75,175,275,250);
        jobImageIcon = new ImageIcon(jobImage);
        jobImageLabel.setIcon(jobImageIcon);
        JLabel jobImageCaption = new JLabel("Find the Best Fit Company for You");
        jobImageCaption.setBounds(95,385,275,100);

        JLabel jobMarketLabel = new JLabel();
        ImageIcon jobMarketImageIcon = new ImageIcon("src/Images/jobMarketImage.jpeg");
        Image jobMarketImage = jobMarketImageIcon.getImage().getScaledInstance(275, 250, Image.SCALE_SMOOTH);
        jobMarketLabel.setBounds(385, 175, 275, 250);
        jobMarketImageIcon = new ImageIcon(jobMarketImage);
        jobMarketLabel.setIcon(jobMarketImageIcon);
        JLabel jobMarketCaption = new JLabel("Maneuver the Job Market");
        jobMarketCaption.setBounds(430,385,275,100);

        homepage.add(workwise);
        homepage.add(searchBar);
        homepage.add(jobImageLabel); homepage.add(jobMarketLabel);
        homepage.add(jobImageCaption); homepage.add(jobMarketCaption);
        homepage.setVisible(true);

        //opens up review page if a company is selected 
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String searchInput = (String) searchBar.getSelectedItem();
                if (searchInput != "Search") {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            MockupReviewPage reviewView = new MockupReviewPage(searchInput);
                            reviewView.setVisible(true);
                            //new WorkWiseReviewPage(searchInput);
                        }
                    });
                }
            }
        });

    }
}