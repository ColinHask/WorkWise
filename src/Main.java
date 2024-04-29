
public class Main {
    public static void main(String[] args) {

        AccountHandler createAccount = new AccountHandler();
        createAccount.createAccount();

        System.out.println("\n\nthis is a test of the systems and this is not real user data");
        System.out.println("current data:");
        createAccount.importUsers();
        createAccount.dangerMethod();
    }
}