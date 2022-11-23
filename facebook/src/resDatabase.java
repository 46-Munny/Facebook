import java.sql.*;
import java.util.Scanner;

public class resDatabase {

    void insertdata()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/restaurant_system";
            String userName="root";
            String passward="";
            Connection con =DriverManager.getConnection(url,userName,passward);
            String query="insert into chef values(?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            Scanner input=new Scanner(System.in);
            String cid=input.next();
            String cname=input.next();
            String csalary=input.next();
            ps.setString(1,cid);
            ps.setString(2,cname);
            ps.setString(3,csalary);
            ps.execute();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void deletedata()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/restaurant_system";
            String userName="root";
            String passward="";
            Connection con =DriverManager.getConnection(url,userName,passward);
            String query="delete from chef where chef_id=?";
            PreparedStatement ps=con.prepareStatement(query);
            Scanner input=new Scanner(System.in);
            System.out.println("Enter chef_id for deletion:");
            String cid=input.next();
            ps.setString(1,cid);
            ps.execute();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void updatedata()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/restaurant_system";
            String userName="root";
            String passward="";
            Connection con =DriverManager.getConnection(url,userName,passward);
            String query="update chef set chef_name=? where chef_id=?";
            PreparedStatement ps=con.prepareStatement(query);
            Scanner input=new Scanner(System.in);
            System.out.println("Enter chef_name:");
            String cname=input.next();
            System.out.println("Enter chef_id:");
            String cid=input.next();

            ps.setString(1,cname);

            ps.setString(2,cid);

            ps.execute();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void showdata() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/restaurant_system";
            String userName = "root";
            String passward = "";

            Connection con = DriverManager.getConnection(url, userName, passward);
            Statement smt = con.createStatement();
            String query = "select * from chef";
            ResultSet rs = smt.executeQuery(query);
            while (rs.next()) {
                String x = rs.getString("chef_id");
                String y = rs.getString("chef_name");
                String z = rs.getString("chef_salary");

                System.out.println(x + " " + y + " " + z);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        resDatabase obj=new resDatabase();
        obj.insertdata();
        obj.showdata();
        obj.deletedata();
        obj.showdata();
        obj.updatedata();
        obj.showdata();


    }
}
