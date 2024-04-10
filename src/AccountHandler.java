
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountHandler {

    ArrayList<String> usernameList = new ArrayList<String>();
    ArrayList<String> passwordList = new ArrayList<String>();

    String username;
    private String pass1;
    private String pass2;
    private String password;
    public void createAccount() {
        Scanner sc = new Scanner(System.in);

        // valid is false until pass is above 5 chars and password has been confirmed and username has been checked
        boolean valid = false;
        while (!valid) {
            System.out.print("\nEnter Username: ");
            username = sc.nextLine();
            // checks to see if username is 5 long
            if (usernameExists(username)) {
                System.out.println("Username already exists please try again");
            } else {
                System.out.print("\nEnter Password: ");
                pass1 = sc.nextLine();
                System.out.print("\nVerify Password: ");
                pass2 = sc.nextLine();
                // checks to see that both passwords match for verification
                if(pass1.length() <5){
                    System.out.println("password must be longer than 5 characters");
                }else if (pass1.equals(pass2)) {
                    valid = true;
                    password = pass1;


                    // temporarily using arraylist in place of database
                    usernameList.add(username);
                    passwordList.add(password);


                    // write passwords to database along with usernames
                    storeUser(username,password);

                } else {
                    System.out.println("Passwords do not match");
                }
            }
        }

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
        // File myObj = new File("filename.txt"); // Specify the filename
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\Colin\\IdeaProjects\\WorkWise\\src\\Users.txt");
            myWriter.write("#u#" + user + "\n");
            myWriter.write("#p#" + pass + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    // imports users and passwords from file (use when app is opened)
    // unfinished
    public void importUsers(){
        try {
            File myObj = new File("C:\\Users\\Colin\\IdeaProjects\\WorkWise\\src\\Users.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
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