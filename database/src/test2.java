/*
 *
 *
 * 

1: Download MySQL Server on https://dev.mysql.com/downloads/windows/

Then install using default settings. You can refer to following links for the detail of installation if neended:

MAC:
How To Install MySQL on Mac OS X (Links to an external site.)Links to an external site.
How To Install MySQL on Mac OS X

Win 10: 

How to download install and configure MySQL on Windows 10 - Tutorial (Links to an external site.)Links to an external site.
How to download install and configure MySQL on Windows 10 - Tutorial

 

2: Create a local user "john" with the password "pass1234" on MySQL Workbench and open it, run the following code:



CREATE DATABASE testdb;

USE testdb;

 

 

CREATE TABLE member (

     name varchar(50),

     email varchar(50),

     country varchar(50)

);

 

 

INSERT INTO member VALUES ('John Smith','shiyonglu@gmail.com', 'USA');

INSERT INTO member VALUES ('Kathy Brown','kathy@gmail.com', 'USA');

INSERT INTO member VALUES ('Newton Franklin','newton@gmail.com', 'Turkey');

SELECT * from member;

 

3: Change the content of user and password (xxxx) in the statement below to your ones in the code in test2.java that you can download at bottom.



          .getConnection("jdbc:mysql://127.0.0.1:3306/test?"

              + "user=xxxx&password=xxxx");



 

4: Download the files from the link at the bottom: test2.java and the mysql-connector-java-5.1.32-bin.jar (the newest version of connector jar file is suggested, 
you can visit https://mvnrepository.com/artifact/mysql/mysql-connector-java to download the version that corresponds to your MySQL Workbench)

 

 

5: Compile the test1.java by using the command. Notice you may need to change the name regarding the version you are using for the jar file

 

 javac -cp .:mysql-connector-java-5.1.32-bin.jar test2.java on Mac, version is only an example here;

 

or

 

 javac -cp .;mysql-connector-java-8.0.13.jar test2.java  on Windows 10, version is only an example here.

 

6: Run the java program by using the command

 

 java -cp .:mysql-connector-java-5.1.32-bin.jar test2  on Mac, version is only an example here;

 

or

 

"java -cp .;mysql-connector-java-8.0.13.jar test2  on Windows 10, version is only an example here.

 

 

Submission:

 

A screen shot of the program result will be sent to TA and Dr.Lu.

 

 

 

Please prepare for the video for the exercise 2 by referring the previous one.

 

Attached please find the two videos link for working on Exercise 1 (https://youtu.be/sjXQe8_9XR8) and Exercise 2 (https://youtu.be/lN7e9xDSvSQ).
 *
 *
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
              + "user=john&password=john1234");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from member");

      writeResultSet(resultSet);

      // PreparedStatements can use variables and are more efficient


/*  test 2 */
      preparedStatement = connect
          .prepareStatement("insert into  member values (?, ?, ?)");
      preparedStatement.setString(1, "Newton John");
      preparedStatement.setString(2, "newton@gmail.com	");
      preparedStatement.setString(3, "U.S.A.");


      preparedStatement.executeUpdate();
      
      resultSet = statement
          .executeQuery("select * from member");

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
