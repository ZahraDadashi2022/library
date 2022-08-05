
import java.sql.*;
import java.util.Scanner;

public class BookMemberRepo extends ConnectionToDB {
    static final String QUERY_SELECTBOOKTBL = "SELECT bookId," +
            " bookName, authorName" + "  FROM Book";
    java.util.Date date = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    public static Scanner scanner = new Scanner(System.in);
    int validation;

    //verify member name
    public boolean verifyMember(String lastName) {
        try {
            //select statement to verify member name
            Statement selectStatement = connection.createStatement();
            ResultSet selectResult = selectStatement.executeQuery(
                    "SELECT * FROM User " +
                            "WHERE lastname ='" + lastName + "'; ");
            while (selectResult.next()) {
                //First Column
                System.out.println(selectResult.getString(1));
                //Second Column
                System.out.println(selectResult.getString(2));
                //Third Column
                System.out.println(selectResult.getString(3));

                System.out.println("is this information yours?" +
                        "please type 1 if Yes and type 0 if N0");
                validation = scanner.nextInt();
                if (validation == 1) {
                    System.out.println("awesome!");
                }
                return true;
            }
            selectStatement.close();

        } catch (SQLException e) {
            System.out.println("error...!");
            e.printStackTrace();
        }
        System.out.println("you are not registered Yet!");
        return false;
    }

    // verify book name
    public boolean verifyAndBorrowBook(String bookName) {
        try {
            //select statement to verify book name
            Statement selectStatement = connection.createStatement();
            ResultSet selectResult = selectStatement.executeQuery(
                    "SELECT * FROM Book " +
                            "WHERE bookName ='" + bookName + "' " +
                            "and bookStatus ='" + Status.RETURNED.ordinal()
                            + "'; ");
            while (selectResult.next()) {
                //First Column
                System.out.println(selectResult.getString(1));
                //Second Column
                System.out.println(selectResult.getString(2));
                //Third Column
                System.out.println(selectResult.getString(3));
                //Fourth Column
                System.out.println(selectResult.getString(4));

                System.out.println("this book is available to borrow, " +
                        "do you want to borrow?" +
                        "please type 1 if Yes and type 0 if No");
                validation = scanner.nextInt();
                if (validation == 1) {
                    //updating status in book table
                    Statement updateStatement = connection.createStatement();
                    String sqlUpdate = "UPDATE Book " +
                            "SET bookStatus = '" + Status.BORROWED.ordinal()
                            + "'" +
                            " WHERE bookName ='" + bookName + "' ";
                    updateStatement.executeUpdate(sqlUpdate);
                    ResultSet updateResult = updateStatement.
                            executeQuery(QUERY_SELECTBOOKTBL);
                    updateResult.close();
                    System.out.println("this book is borrowed by you");
                }
                return true;
            }
            selectStatement.close();
        } catch (SQLException e) {
            System.out.println("error...!");
            e.printStackTrace();
        }
        System.out.println("this book is not available!");
        return false;
    }

    //inserting data in Book_Member
    public void insertingBookMemberTbl(String lastName, String selectedBookName) {
        try {
            PreparedStatement selectStatementUser = connection.prepareStatement(
                    "SELECT * FROM User WHERE lastname ='" + lastName + "'; ");
            ResultSet resultSetSelectUser = selectStatementUser
                    .executeQuery();
            resultSetSelectUser.next();
            int userFk = Integer.parseInt(resultSetSelectUser.getString(1));
            PreparedStatement selectStatementBook = connection.prepareStatement(
                    "SELECT * FROM Book WHERE bookName ='"+selectedBookName+"';");
            ResultSet resultSetSelectBook = selectStatementBook
                    .executeQuery();
            resultSetSelectBook.next();
            int bookFK = Integer.parseInt(resultSetSelectUser.getString(1));
            PreparedStatement preparedStatementBookMember = connection
                    .prepareStatement("insert into Book_Member(" +
                            "createDate ,status ,bookFk ,userFk)" +
                            " values(?,?,?,?)");

            preparedStatementBookMember.setDate(1, sqlDate);
            preparedStatementBookMember.setInt(2, Status.BORROWED.ordinal());
            preparedStatementBookMember.setInt(3, bookFK);
            preparedStatementBookMember.setInt(4, userFk);
            preparedStatementBookMember.executeUpdate();

            System.out.println("table Book_Member updated successfully :)");
            connection.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());

        }
    }

}