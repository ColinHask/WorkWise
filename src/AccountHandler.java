
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AccountHandler {

    ArrayList<String> usernameList = new ArrayList<String>();
    ArrayList<String> passwordList = new ArrayList<String>();

    public JFrame create;
    public JFrame login;
    public String username;
    public String createPassword;
    public String verifyPassword;
    public String password;
    
    public void createAccount() {
        //create account page 
        //this creates and sets the frame of the create account page
        create = new JFrame("WebWise Create Account");
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
        JTextField createUserPassword = new JPasswordField();
        createUserPassword.setBounds(175,150,200,30);

        //label and placement for verify password field
        JLabel verifyLabel = new JLabel("Verify Password:");
        verifyLabel.setBounds(50,200,150,30);
        //verify password input field and placement
        JTextField verify = new JPasswordField();
        verify.setBounds(175,200,200,30);

        //create account button
        JButton createUserAccount = new JButton("Create Account");
        createUserAccount.setBounds(250,250,125,40);

        createUserAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                username = createUsername.getText();
                createPassword = createUserPassword.getText();
                verifyPassword = verify.getText();
                createUserAccount(createUserAccount, username, createPassword, verifyPassword);
                
            }
        });

        //adds all fields created to the create account page/frame 
        create.add(createAccount); 
        create.add(userLabel); create.add(createUsername);
        create.add(passLabel); create.add(createUserPassword);
        create.add(verifyLabel); create.add(verify);
        create.add(createUserAccount);
        //sets the create page to visible
        create.setVisible(true);
    }

    public void createUserAccount(JButton createUserAccount, String username, String createPassword, String verifyPassword) {
        Scanner sc = new Scanner(System.in);

            // checks to see if username is 5 long
            if (usernameExists(username)) {
            //text label for error username if it already exists
            JLabel errorUsername = new JLabel("Username already exists please try again");
            errorUsername.setBounds(50,300,300,30);
            create.add(errorUsername);
            create.revalidate();
            create.repaint();

            } else {
                //checks to see that both passwords match for verification
                if(createPassword.length() <5){

                    JLabel errorPassword = new JLabel("Password must be longer than 5 characters");
                    errorPassword.setBounds(50,350,300,30);
                    errorPassword.setBackground(Color.red);
                    create.add(errorPassword);
                    create.revalidate();
                    create.repaint();

                }else if (!createPassword.equals(verifyPassword)) {
                    JLabel noMatch = new JLabel("Passwords do not match");
                    noMatch.setBounds(50,400,300,30);
                    create.add(noMatch);
                    create.revalidate();
                    create.repaint();

                } else {
                    password = createPassword;

                    JLabel createButton = new JLabel("Please press the Create Account button again");
                    createButton.setBounds(150,425,300,50);
                    create.add(createButton);

                    // temporarily using arraylist in place of database
                    usernameList.add(username);
                    passwordList.add(password);

                    // write passwords to database along with usernames
                    storeUser(username,password);
                    create.revalidate();
                    create.repaint();

                    createAccountSubmit(createUserAccount, username, verifyPassword);
               }
            }
    }

    public void createAccountSubmit(JButton createUserAccount, String username, String password) {
        //create account button action listener
        createUserAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                LoginCreation(username, password);
            }
        });
    }

    public void LoginCreation(String username, String password) {
        create.setVisible(false);
        //create login page 
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
        JTextField usernameField = new JTextField();
        usernameField.setBounds(150,100,200,30);
        //password label and placement
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50,150,90,30);
        //password text field creation and placement 
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150,150,200,30);

        //create account button
        JButton userLogin = new JButton("Login");
        userLogin.setBounds(275,200,75,40);

        //userLogin button action listener
        //whenever userLogin button is clicked, this function will run
        userLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new Homepage();
            }
        });

        login.add(loginLabel);
        login.add(usernameLabel); login.add(usernameField);
        login.add(passwordLabel); login.add(passwordField);
        login.add(userLogin);
        login.setVisible(true);
    }

    // returns false if username does not exist
    public boolean usernameExists(String input){
        for (String i : usernameList){
            if(i.equals(input)){
                return true;
            }
        }
    return false; }

    // returns true if login is successful
    public boolean loginAttempt(String user, String pass){
        int userNum = -1;
        int count = 0;
        if(!(usernameExists(user))){
            return false;
        }
        // finds place in array for the correct password based on username
        for (String i : usernameList){
            if(i.equals(user)){
                userNum = count;
            }
            count++;
        }
        if(pass.equals(passwordList.get(userNum))){
            return true;
        }
    return false;
    }

    // stores username and password in text file,  find better way to do this using database later
    public void storeUser(String user, String pass) {
        try {
            FileWriter myWriter = new FileWriter("src/Users.txt", true); // true for appending
            myWriter.append("#u#" + user + "\n");
            myWriter.append("#p#" + pass + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    // imports users and passwords from file (use when app is opened)

    public void importUsers(){
        try {
            File myObj = new File("src/Users.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if(data.startsWith("#u#")){

                    usernameList.add(data.substring(3));

                } else if (data.startsWith("#p#")) {

                    passwordList.add(data.substring(3));

                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


// this method is for testing only
// it prints every username and password
    public void dangerMethod(){
        System.out.println(" ");
        for (int i = 0; i < usernameList.size(); i++) {
            System.out.print(usernameList.get(i)+", ");
        }
        System.out.println(" ");
        for (int i = 0; i < passwordList.size(); i++) {
            System.out.print(passwordList.get(i)+", ");
        }
    }



}