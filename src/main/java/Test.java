import com.google.cloud.firestore.Firestore;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Test {

    private static Scanner sc = new Scanner(System.in);
    private static Firestore db;

    //creates an object from WestminsterMusicStoreManager
    private static WestminsterMusicStoreManager westminsterMusicStoreManager;

    public static void main(String[] args) {
        westminsterMusicStoreManager = new WestminsterMusicStoreManager();
        try {
            welcome();
        } catch (StoreFullException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void welcome() throws StoreFullException, ExecutionException, InterruptedException {

        int choice = 0;
        //get the user input for the choice
        do {
            try {
                System.out.print("\n_________Welcome_________\n" +
                        "\nPlease select your choice : " +
                        "\n 1) Add item" +
                        "\n 2) Delete item " +
                        "\n 3) List of items" +
                        "\n 4) Sort the items" +
                        "\n 5) Generate the report" +
                        "\n 6) Buy item" +
                        "\n 7) Search item" +
                        "\n 8) Exit the program" +
                        "\n Enter here :: \n");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again");
            }
        } while (choice > 8 || choice < 1);

        //options for the choice
        switch (choice) {
            case 1:
                addNewItem();
                welcome();
            case 2:
                deleteItem();
                welcome();
            case 3:
                try {
                    listOfItems();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                welcome();
            case 4:
                sortItems();
                welcome();
            case 5:
                generateReport();
                welcome();
            case 6:
                buyItem();
                welcome();
            case 7:
                searchItem();
                welcome();
            case 8:
                System.out.println("\n ________Thank You________");
                System.exit(0);

            default:
                System.out.println("you might have mistaken, check again");
        }
    }

    //method to add an item
    private static void addNewItem() {

        System.out.println("Do you want to ad  a CD or a Vinyl? " +
                "\n 1) Add a CD" +
                "\n 2) Add a Vinyl" +
                "\nEnter here : ");
        int choice = sc.nextInt();
        if (choice == 1) {

            System.out.println("please enter the following information :");

            //get the item id
            System.out.println("  Enter the item ID : ");
            int itemId = sc.nextInt();

            //get the item title
            System.out.println("  Enter the item Title : ");
            String title = sc.next();

            //get the item genre
            System.out.println("  Enter the item Genre : ");
            String genre = sc.next();

            //enter date
            System.out.println("  Enter release day : ");
            int day = sc.nextInt();

            System.out.println("  Enter release month : ");
            int month = sc.nextInt();

            System.out.println("  Enter release year : ");
            int year = sc.nextInt();

            //enter artist
            System.out.println("  Enter Artist Name : ");
            String artist = sc.next();

            //enter price
            System.out.println("  Enter the price [in $] : ");
            int price = sc.nextInt();

            //enter duration
            System.out.println("  Enter duration [minutes] : ");
            int duration = sc.nextInt();

            //reference to the object
            MusicItem cd = new CD(itemId, title, genre, new Date(day, month, year), artist, price, duration);

            westminsterMusicStoreManager.addItem(cd);


        } else if (choice == 2) { //option for the vinyl

            System.out.println("please enter the following information :");

            //get the item id
            System.out.println("  Enter the item ID : ");
            int itemId = sc.nextInt();

            //get the item title
            System.out.println("  Enter the item Title : ");
            String title = sc.next();

            //get the item genre
            System.out.println("  Enter the item Genre : ");
            String genre = sc.next();

            //enter date
            System.out.println("  Enter release day : ");
            int day = sc.nextInt();

            System.out.println("  Enter release month : ");
            int month = sc.nextInt();

            System.out.println("  Enter release year : ");
            int year = sc.nextInt();

            //enter artist
            System.out.println("  Enter Artist Name : ");
            String artist = sc.next();

            //enter price
            System.out.println("  Enter the price [in $]: ");
            int price = sc.nextInt();

            //enter speed
            System.out.println("  Enter the speed [KB/sec] : ");
            int speed = sc.nextInt();

            //enter diameter
            System.out.println("  Enter the Diameter [cm]: ");
            int dia = sc.nextInt();

            //create the object
            MusicItem vinyl = new Vinyl(itemId, title, genre, new Date(day, month, year), artist, price, speed, dia);

            //redirects into the method in westminsterMusicStoreManager
            westminsterMusicStoreManager.addItem(vinyl);

        } else {
            System.out.println("You entered an invalid input. Please re-enter");
            //recursive calling
            addNewItem();
        }

    }

    //method to delete an item
    private static void deleteItem() {
        System.out.println("Enter the item ID you want to delete : ");
        int deleteThis = sc.nextInt();
        westminsterMusicStoreManager.deleteItem(deleteThis);
    }

    //displays the items which are currently in the store
    private static void listOfItems() throws ExecutionException, InterruptedException {
        westminsterMusicStoreManager.printList();
    }

    //sort the items in the order of title
    private static void sortItems() throws ExecutionException, InterruptedException {
        westminsterMusicStoreManager.sortItems();
    }

    //buy items method
    private static void buyItem() throws ExecutionException, InterruptedException {
        //view list of items before buying
        westminsterMusicStoreManager.printList();
        System.out.println("How many items do you want to buy? ");
        int noOfItems = sc.nextInt();
        //loop for number of items entered
        for (int i = 0; i < noOfItems; i++) {
            System.out.println("Enter the item ID :");
            int id = sc.nextInt();
            westminsterMusicStoreManager.checkData(id);
        }
        westminsterMusicStoreManager.buyItems();
    }

    //displays the bought items
    private static void generateReport() throws ExecutionException, InterruptedException {
        westminsterMusicStoreManager.generateReport();
    }

    //prompts for the user to search an item
    public static void searchItem() throws ExecutionException, InterruptedException {
        System.out.println("Enter the item title you want to search : ");
        String search = sc.next();
        westminsterMusicStoreManager.searchItem(search);
    }
}
