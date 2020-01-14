/*
 
1: Download Java JDK at http://www.oracle.com/technetwork/java/javase/downloads/index.html

2: Make sure you have the global java running enviornment by typing java under any folder in the terminal

3: Configure your Windows 10 environmen by checking the web: https://www.mkyong.com/java/how-to-set-java_home-on-windows-10/
Then: restart the command line to activate the new settings.

4: Download the two files from the link at the bottom: test1.java and the mysql-connector-java-5.1.32-bin.jar


5: Compile the test1.java by using the command on Mac

 javac -cp .:mysql-connector-java-5.1.32-bin.jar test1.java

or on Windows

 javac -cp .;mysql-connector-java-5.1.32-bin.jar test1.java

6: Run the java program by using the command on Mac

 java -cp .:mysql-connector-java-5.1.32-bin.jar test1

or on Windows

java -cp .;mysql-connector-java-5.1.32-bin.jar test1



To successfully run the commands above, you need to connect with the school network (WSU WIFI), or to use a machine on campus that connects to the same network (in any classroom that has computers)!

 


Submission:

A screen shot of the program result will be sent to TA and Dr.Lu.

 

Please prepare for the video for the exercise 1 by referring the previous one.

Attached please find the two videos link for working on Exercise 1 (https://youtu.be/sjXQe8_9XR8) and Exercise 2 (https://youtu.be/lN7e9xDSvSQ).


 *
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class test1 {
  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;

 public static void main(String[] args) {
    try {
      System.out.println("Select a table and then print out its content.");
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://141.217.48.128:3306/dataview?"
              + "user=csc4710&password=view1234");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from member");

      writeResultSet(resultSet);

      // PreparedStatements can use variables and are more efficient
/*
      preparedStatement = connect
          .prepareStatement("insert into  member values (?, ?, ?, ? , ?, ?)");
      preparedStatement.setString(1, "Test");
      preparedStatement.setString(2, "TestEmail");
      preparedStatement.setString(3, "TestWebpage");
      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
      preparedStatement.setString(5, "TestSummary");
      preparedStatement.setString(6, "TestComment");
      preparedStatement.executeUpdate();

      preparedStatement = connect
          .prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from feedback.comments");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);

      // Remove again the insert comment
      preparedStatement = connect
      .prepareStatement("delete from feedback.comments where myuser= ? ; ");
      preparedStatement.setString(1, "Test");
      preparedStatement.executeUpdate();
      
      resultSet = statement
      .executeQuery("select * from feedback.comments");
      writeMetaData(resultSet);
*/      
    } catch (Exception e) {
         System.out.println(e);
    } finally {
      close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private static void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    System.out.println("print result from a table..");
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String name = resultSet.getString("name");
      String email = resultSet.getString("email");
      String country = resultSet.getString("country");
      System.out.println("name: " + name);
      System.out.println("email: " + email);
      System.out.println("country: " + country);
      System.out.println("");
    }
  }

  // You need to close the resultSet
  private static void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }
} 
