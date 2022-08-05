import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberRepo extends ConnectionToDB {
    public void addMember(String fname,String lname) {
        try {
            //insert statement to add members
            PreparedStatement sqlInsert = connection.prepareStatement(
                    "INSERT INTO User (firstname, lastname) " +
                            "VALUES (?, ?)");
            sqlInsert.setString(1, fname);
            sqlInsert.setString(2, lname);

            int rows = sqlInsert.executeUpdate();
            if (rows > 0) {
                System.out.println("a row has been inserted.");
            }
            sqlInsert.close();

        } catch (SQLException e) {
            System.out.println("error...!");
            e.printStackTrace();
        }
    }
    // displaying all members
    public void showAllMembers() {
        try {
            //Open the connection
            Statement selectStatement = connection.createStatement();
            ResultSet selectResult = selectStatement.executeQuery(
                    "SELECT * FROM User");
            while (selectResult.next()) {
                System.out.println(selectResult.getString(1));  //First Column
                System.out.println(selectResult.getString(2));  //Second Column
                System.out
                        .println(selectResult.getString(3));  //Third Column
            }
            selectStatement.close();

        } catch (SQLException e) {
            System.out.println("error...!");
            e.printStackTrace();
        }
    }
}
