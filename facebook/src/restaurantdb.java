import java.sql.*;

public class restaurantdb {
    void show()
    {
      try {
          Class.forName("com.mysql.jdbc.Driver");
          String url = "jdbc:mysql://localhost:3306/restaurant_system";
          String userName = "root";
          String passward = "";

          Connection con = DriverManager.getConnection(url, userName, passward);
          Statement smt = con.createStatement();
          String query = "select * from chef";
          ResultSet rs = smt.executeQuery(query);
          while (rs.next())
          {
              String x=rs.getString("meal_name");
              String y=rs.getString("meal_price");
              System.out.println(x+" "+y);
          }
      } catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
      }
    }

    public static void main(String[] args) {
        restaurantdb obj=new restaurantdb();
        obj.show();

    }
}
