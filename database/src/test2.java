/*
 *
 The purpose of Exercise 2 is to show how to execute a SELECT/INSERT statement on a table located at a local database server. 
    1) Download, install, configure and run MySQL database server locally; 
    2) Learn to use MySQL workbench to run SQL statements over a local MySQL server;
    3) Understand how two methods, writeMetaData and writeResultSet, work to display the result from a SELECT statement. 
 *
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class test2  {
  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;

 public static void main(String[] args) {
    try {
      System.out.println("Select a table and then print out its content.");
      // This will load the MySQL driver, each DB has its own driver
      // Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
              + "useSSL=false&user=john&password=john1234");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from student");

      writeResultSet(resultSet);

      // PreparedStatements can use variables and are more efficient


/*  test 2 */
      preparedStatement = connect
          .prepareStatement("insert into  student values (?, ?, ?, ?)");
      preparedStatement.setInt(1, 0); 
      preparedStatement.setString(2, "Thomas Jefferson");
      preparedStatement.setString(3, "134 Freedom ln, Rochest Hills, MI 49083");
      preparedStatement.setString(4, "Senior");


      preparedStatement.executeUpdate();
      
      resultSet = statement
          .executeQuery("select * from student");

      writeResultSet(resultSet);
      
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
      String address = resultSet.getString("address");
      String status = resultSet.getString("status");
      System.out.println("name: " + name);
      System.out.println("address: " + address);
      System.out.println("status: " + status);
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
