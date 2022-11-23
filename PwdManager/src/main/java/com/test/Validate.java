package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validate {
    public static boolean checkUser(String username,String password) 
    {
	
    	Connection con = null;
        boolean st =false;
        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pwmanager","root","root");
            PreparedStatement ps = con.prepareStatement("select * from users where username=?"
            		+ " and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
            st = rs.next();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    }   
}