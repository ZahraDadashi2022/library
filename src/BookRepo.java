
import java.sql.*;
import java.util.Scanner;

public class BookRepo extends ConnectionToDB {
    public static Scanner scanner = new Scanner(System.in);
    public void addBook(String bookName,String authorName) {
        try {
            //insert statement to add books
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Book (bookName, authorName,bookStatus) " +
                            "VALUES (?, ?,? )");
            insertStatement.setString(1, bookName);
            insertStatement.setString(2, authorName);
            insertStatement.setInt(3, Status.RETURNED.ordinal());
            int rows = insertStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("a row has been inserted.");
            }
            insertStatement.close();

        } catch (SQLException e) {
            System.out.println("error...!");
            e.printStackTrace();
        }
    }
    // displaying all available books
    public void showAvailableBooks() {
        try {
            //select statement to show all books
            Statement selectStatement = connection.createStatement();
            ResultSet selectResult = selectStatement.executeQuery(
                    "SELECT * FROM book WHERE bookStatus  = 1");
            while (selectResult.next()) {
                System.out.println(selectResult.getString(1));  //First Column
                System.out.println(selectResult.getString(2));  //Second Column
                System.out.println(selectResult.getString(3));  //Third Column
                System.out.println(selectResult.getString(4));  //Fourth Column
                System.out.println("-----------------------");
            }
            selectStatement.close();
        } catch (SQLException e) {
            System.out.println("error...!");
            e.printStackTrace();
        }
    }
}
