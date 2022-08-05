import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static String getScanner() {
        String name = scanner.nextLine();
        return name;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BookRepo bookRepo = new BookRepo();
        MemberRepo memberRepo = new MemberRepo();
        BookMemberRepo bookMemberRepo = new BookMemberRepo();
        System.out.println("From the menu enter the action you want:");
        int choice;
        do {
            displayMenu();
            choice = Integer.parseInt(scanner.nextLine());
            // choosing number 0-6 from the menu
            switch (choice) {
                // adding books
                case 1:
                    System.out.println("Enter a book name:");
                    String bookName = scanner.nextLine();
                    System.out.println("Enter author name:");
                    String authorName = scanner.nextLine();
                    bookRepo.addBook(bookName, authorName);
                    break;
                // Showing All available Books
                case 2:
                    bookRepo.showAvailableBooks();
                    break;
                // adding members
                case 3:
                    System.out.println("Enter your first name:");
                    String fname = scanner.nextLine();
                    System.out.println("Enter your last name:");
                    String lname = scanner.nextLine();
                    memberRepo.addMember(fname, lname);

                    break;
                // Showing All Registered Students
                case 4:
                    memberRepo.showAllMembers();
                    break;
                // borrowing the Books
                case 5:
                    System.out.println("Enter your last name:");
                    String lastName = scanner.nextLine();
                    if (Objects.equals(lastName, "true")) {
                        bookMemberRepo.verifyMember(lastName);
                        System.out.println("Enter book name:");
                        String borrowBookName = scanner.nextLine();
                        bookMemberRepo.verifyAndBorrowBook(borrowBookName);
                        bookMemberRepo.insertingBookMemberTbl(lastName,
                                borrowBookName);
                    }

                    break;
                // giving back the books
                case 6:
                    //  borrowBooksRepo.makingBookAvailabilityZero();
                    break;
                // if above cases does not match do the default one
                default:
                    System.out.println("ENTER BETWEEN 0 TO 6.");
            }
        } while (choice != 0);
    }

    public static void displayMenu() {
        // Display menu
        System.out.println(
                "-----------------------------------------");
        System.out.println("Press 0 to Exit Application.");
        System.out.println("Press 1 to Add new Book.");
        System.out.println("Press 2 to Show All available Books.");
        System.out.println("Press 3 to Register member.");
        System.out.println(
                "Press 4 to Show All Registered Members.");
        System.out.println("Press 5 to borrow the Book. ");
        System.out.println("Press 6 to give the Book back");
        System.out.println(
                "----------------------------------------");
    }
}
