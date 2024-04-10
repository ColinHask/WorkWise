import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountHandler acc = new AccountHandler();

        // this is setup to test creating acc and logging in
        // create user, then log in attempt, then display all stored usernames and pass
        for (int i = 0; i < 5; i++) {
            System.out.println("\n\ncreate account");
            acc.createAccount();


            System.out.println("\n\n\nlogin:");
            System.out.println("enter username: ");
            String user = sc.nextLine();
            System.out.println("enter password: ");
            String pass = sc.nextLine();
            if (acc.loginAttempt(user, pass)) {
                System.out.println("sucess :D");
            } else {
                System.out.println(" bad :(");
            }

        acc.dangerMethod();

        }
    }
}