/*
 *
 *
 *
 *
 *
1: Based on previously created database on your local server, you need to create a table that contains some categories of information through java programming. 
Use the code in the file that you can find at the bottom and modify it to your own settings. You need to modify the name of the database, user name and password, which are marked YYYY, XXX, and XXXX, respectively.

 

 

Try all the SQL statements in Chapter 2's slides by modifying test3.java and running it against your own local MySQL database server. Executing the SQL statements using MySQL Workbench is inadequate.?

Explain line by line how test3.java works, and record a youtube using Apowersoft: https://www.apowersoft.com/free-online-screen-recorder (Links to an external site.)Links to an external site.

While test2.java contains the code snippet to execute an SQL INSERT statement, the following code snippets show how to execute SQL UPDATE and DELETE statements in test3.java.

 

 

"

// update
String query1 = "update Student set Address = ? where Name = ?";
preparedStatement = conn.prepareStatement(query1);
preparedStatement.setString(1, "4500 Cass Ave");
preparedStatement.setString(2, "Shiyong Lu");
preparedStatement.executeUpdate();
resultSet = statement
.executeQuery("select * from Student");
writeResultSet(resultSet);

// Delete
String query2 = "DELETE FROM Student WHERE Name = ?";
preparedStatement = conn.prepareStatement(query2);
preparedStatement.setString(1, "Shiyong Lu");
preparedStatement.executeUpdate();
resultSet = statement
.executeQuery("select * from Student");

writeResultSet(resultSet);

"

 

2: Compile the test3.java by using the command. Notice you may need to change the name regarding the version you are using for the jar file. 
Use the jar file you downloaded from previous exercises.

 

 

 javac -cp .:mysql-connector-java-5.1.32-bin.jar test3.java  on Mac, version is only an example here;

 

or

 

 javac -cp .;mysql-connector-java-8.0.13.jar test3.java on Windows 10, version is only an example here.

 

6: Run the java program by using the command

 

 java -cp .:mysql-connector-java-5.1.32-bin.jar test3  on Mac, version is only an example here;

 

or

 

"java -cp .;mysql-connector-java-8.0.13.jar test3  on Windows 10, version is only an example here.

 

 




      preparedStatement = connect

          .prepareStatement("insert into  member values (?, ?, ?)");

      preparedStatement.setString(1, "Your name");

      preparedStatement.setString(2, "Your email");

      preparedStatement.setString(3, "Your country");

      preparedStatement.executeUpdate();

     

      resultSet = statement

          .executeQuery("select * from member");

 

      writeResultSet(resultSet);


Plus, if you have a warning at the beginning that claims Loading class "com.mysql.jdbc.Driver". 
This is deprecated. The new driver class is "com.mysql.cj.jdbc.Driver". The driver is automatically 
registered via the SPI and manual loading of the driver class is generally unnecessary., 
just simply comment the following line in the code as well, 8.0.x version jar file already contains that driver for you to compile the code:


// Class.forName("com.mysql.jdbc.Driver");

 *
 *
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
              + "user=john&password=john1234");

        

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
