package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "kanu", "kanu");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );
            while ( rs.next() ) {
                int id = rs.getInt("user_id");
                String  userName = rs.getString("user_name");
                String  userFname = rs.getString("user_fname");
                String  userLname = rs.getString("user_lname");

                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + userName );
                System.out.println( "First NAME = " + userFname );
                System.out.println( "Last NAME = " + userLname );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }
}