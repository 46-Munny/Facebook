package com.company;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class databasee {

    void show()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/fbdb";
            String userName="root";
            String passward="";
            Connection con=DriverManager.getConnection(url,userName,passward);
            Statement stmt=con.createStatement();
            String sql="select * from friendship";
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next())
            {
                String x=rs.getString("who");
                String y=rs.getString("whom");
                String z=rs.getString("status");

                System.out.println(x+" "+y+" "+" "+z);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void deletion()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/fbdb";
            String userName="root";
            String passward="";
            Connection con=DriverManager.getConnection(url,userName,passward);
            String sql="delete from friendship where status=?";
            PreparedStatement  ps=con.prepareStatement(sql);
            String status;
            Scanner input=new Scanner(System.in);
            System.out.println("enter status:");
            status=input.next();
            ps.setString(1,status);
           ps.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        databasee obj=new databasee();
        obj.show();
        System.out.println("-----------");
        obj.deletion();
        obj.show();

    }

}
