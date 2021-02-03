/*
 *
 The purpose of exercise 3 is to learn how to execute a CREATE/INSERT/SELECT/UPDATE/DELETE statement in Java over a table located at a local MySQL database server. 
     1) Execute each SQL statement from ch2's slides in Java. You will need to understand how method writeResultSet() works and modify it to display your results correctly. 
     2) Understand the difference between dynamic SQLs and static SQLs. 
 *
 * */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class test3  {
  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;



 public static void main(String[] args) {

    String sql1 = "DROP TABLE IF EXISTS Student";
    String sql2 = "CREATE TABLE IF NOT EXISTS Student " +
                   "(id INTEGER not NULL AUTO_INCREMENT, " +
                   " Name VARCHAR(20), " + 
                   " Address VARCHAR(50), " + 
                   " Status VARCHAR(10), " + 
                   " PRIMARY KEY ( id ))"; 
    String sql3 = "insert into  Student(Name, Address, Status) values (?, ?, ?)";
    String sql4 = "insert into Student(Name, Address, Status) values (\"John Liu\", \"345 Database Road, Troy, MI 48083\", \"Junior\")";
    String sql5 = "UPDATE Student set Address=\"123 New Main Street, Troy, MI 48083\" WHERE Name=\"Shiyong Lu\"";
    String sql6 = "DELETE FROM Student WHERE Name=\"Shiyong Lu\"";

    try {
      System.out.println("Select a table and then print out its content.");
      // This will load the MySQL driver, each DB has its own driver
      // Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/testdb?"
              + "useSSL=false&user=john&password=john1234");

        

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();

      statement.executeUpdate(sql1);
      statement.executeUpdate(sql2);


      preparedStatement = connect
          .prepareStatement(sql3);
      preparedStatement.setString(1, "Shiyong Lu");
      preparedStatement.setString(2, "123 Main Street, Troy, MI 48083");
      preparedStatement.setString(3, "Senior");
      preparedStatement.executeUpdate();
      
      statement.executeUpdate(sql4);


      // see the results 
      resultSet = statement.executeQuery("select * from Student");
      writeResultSet(resultSet);

      System.out.println("After the update statement is executed.");
      statement.executeUpdate(sql5);
      // see the results 
      resultSet = statement.executeQuery("select * from Student");
      writeResultSet(resultSet);

      System.out.println("After the delete statement is executed.");
      statement.executeUpdate(sql6);
      // see the results 
      resultSet = statement.executeQuery("select * from Student");
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
      Integer id = resultSet.getInt("id");
      String name = resultSet.getString("Name");
      String address = resultSet.getString("Address");
      String status = resultSet.getString("Status");
      System.out.println("id " + id);
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
