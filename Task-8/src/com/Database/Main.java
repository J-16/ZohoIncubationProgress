package com.Database;

import java.sql.*;

public class Main {

    private static final String CONNECTION_NAME = "jdbc:mysql://172.19.255.130:3306/users";

    public static void main(String ...args) throws SQLException {
            Connection conn = DriverManager.getConnection(CONNECTION_NAME, "temp", "pass");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_info");
            while(rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(3));
    }
}