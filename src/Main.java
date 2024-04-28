import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        //create account page 
        //this creates and sets the frame of the create account page
        JFrame create = new JFrame("WebWise Create Account");
        create.setSize(500,500);
        create.setLayout(null);

        //the label for create account and its placement
        JLabel createAccount = new JLabel("Create Account");
        createAccount.setBounds(50,50,300,30);

        //the label and its placement for the username input field
         JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50,100,90,30);
        //username input field
        JTextField createUsername = new JTextField();
        createUsername.setBounds(175,100,200,30);
        //label and placement for password field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50,150,90,30);
        //creating password input field and its placement
        JTextField createPassword = new JPasswordField();
        createPassword.setBounds(175,150,200,30);
        //label and placement for verify password field
        JLabel verifyLabel = new JLabel("Verify Password:");
        verifyLabel.setBounds(50,200,150,30);
        //verify password input field and placement
        JTextField verify = new JPasswordField();
        verify.setBounds(175,200,200,30);

        //text label for error username if it already exists
        //if username invalid errorUsername.setVisible(true) or create.add(errorUsername)
        JLabel errorUsername = new JLabel("Username already exists please try again");
        errorUsername.setBounds(50,250,300,30);
        //text label for error password if password is not long enough
        //if the password is not valid then errorPassword.setVisible or create.add(errorPassword)
        JLabel errorPassword = new JLabel("Password must be longer than 5 characters");
        errorPassword.setBounds(50,300,300,30);
        errorPassword.setBackground(Color.red);

        //create account button
        JButton createUserAccount = new JButton("Create Account");
        createUserAccount.setBounds(250,250,125,40);

        //create account button action listener
        //basically when it is clicked, it will run whatever code is in this function
        createUserAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {

            }
        });

        //adds all fields created to the create account page/frame 
        create.add(createAccount); 
        create.add(userLabel); create.add(createUsername);
        create.add(passLabel); create.add(createPassword);
        create.add(verifyLabel); create.add(verify);
        create.add(createUserAccount);
        //sets the create page to visible
        create.setVisible(true);

        //create login page 
        //once account is created, create.setVisible(false), login.setVisible(true)
        //login page and set size and layout
        JFrame login = new JFrame("WebWise Login Page");
        login.setSize(500,500);
        login.setLayout(null);
        //login label for page
         JLabel loginLabel = new JLabel("Login:");
        loginLabel.setBounds(50,50,300,30);
        //username label and placement
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50,100,90,30);
        //username text field creation and placement
        JTextField username = new JTextField();
        username.setBounds(150,100,200,30);
        //password label and placement
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50,150,90,30);
        //password text field creation and placement 
        JPasswordField password = new JPasswordField();
        password.setBounds(150,150,200,30);

        //create account button
        JButton userLogin = new JButton("Login");
        userLogin.setBounds(275,200,75,40);

        //userLogin button action listener
        //whenever userLogin button is clicked, this function will run
        //likely do if login is verified then login.add(success)
        userLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new Homepage();
            }
        });
        //maybe put this section inside of the actionListener
        //show based on if login is verified login.add(success) or login.add(error)
        //success label message and placement 
        JLabel success = new JLabel("Success :D");
        success.setBounds(50,200,150,30);
        //error label message and placement 
        JLabel error = new JLabel("Bad :(");
        error.setBounds(50,200,150,30);
        
        //adds created fields to the login page/frame
        login.add(loginLabel);
        login.add(usernameLabel); login.add(username);
        login.add(passwordLabel); login.add(password);
        login.add(userLogin);
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

    }
}