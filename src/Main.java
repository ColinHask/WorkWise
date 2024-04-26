import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountHandler acc = new AccountHandler();


        System.out.println("\n\nthis is a test of the systems and this is not real user data");
        System.out.println("current data:");
      acc.importUsers();
      acc.dangerMethod();

        // this is setup to test creating acc and logging in
        // create user (createAccount)
        // then log in attempt (LoginAttempt)
        // then display all stored usernames and pass (dangerMethod)
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