package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    void showData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String userName = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, userName, password);
            if (connection.isClosed()) {
                System.out.printf("Not Connected");
            } else {
                System.out.println("Connected");
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM logIn");
            while (resultSet.next()) {
                //System.out.println("First Name:     Last Name:     User Id: Password:  Date Of Birth:  Gender: Country:  Phone No: Address");
                System.out.println(resultSet.getString("firstName") + "    " + resultSet.getString("lastName") + "    " + resultSet.getString("userId") + "    " + resultSet.getString("password") + "    " + resultSet.getString("dateOfBirth") + "    " + resultSet.getString("gender") + "    " + resultSet.getString("country") + "    " + resultSet.getString("phoneNo") + "   " + resultSet.getString("address"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exp: Find Out" + e);
        }
    }


    String userLogIn(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Your User Id");
        String userUserId=input.nextLine();
        System.out.println("Enter Your Password");
        String userPassword=input.nextLine();

        boolean flag=false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String userName = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM logIn");
            while (resultSet.next()) {
                String x = resultSet.getString("userId").toString();
                String y = resultSet.getString("password").toString();


                if(userUserId.equals(x)&&userPassword.equals(y)){
                    flag = true;
                    System.out.print("Welcome ");
                    System.out.println(resultSet.getString("firstName") + "  " + resultSet.getString("lastName") + "   \nYour User Id:   " + resultSet.getString("userId") +  "\nDate of Birth:    " + resultSet.getString("dateOfBirth") + "\nGender:    " + resultSet.getString("gender") + "\nCountry:    " + resultSet.getString("country") + "\nPhone No:    " + resultSet.getString("phoneNo") + "\nAddress:   " + resultSet.getString("address"));
                    return x;
                }

            }
            if(flag==false){
                System.out.println("");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exp: Find Out" + e);
        }
        return "Not Register";
    }

    void userRagistration() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);


            Scanner input = new Scanner(System.in);
            System.out.println("Enter Your First Name:");
            String firstName = input.nextLine();
            System.out.println("Enter Your Last Name:");
            String lastName = input.nextLine();
            System.out.println("Enter Your User Id:");
            String userId = input.nextLine();
            System.out.println("Enter Your Password:");
            String pass = input.nextLine();
            System.out.println("Enter Your Date Of Birth:");
            String dateOfBarth = input.nextLine();
            System.out.println("Enter Your Gender:");
            String gender = input.nextLine();
            System.out.println("Enter Your Country:");
            String country = input.nextLine();
            System.out.println("Enter Your Phone No:");
            String phoneNo = input.nextLine();
            System.out.println("Enter Your Address  like(Dhaka, Bangladesh) :");
            String address = input.nextLine();


            String query = " insert into logIn (firstName, lastName, userId, password, dateOfBirth, gender, country, phoneNo, address)"
                    + " values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, userId);
            ps.setString(4, pass);
            ps.setString(5, dateOfBarth);
            ps.setString(6, gender);
            ps.setString(7, country);
            ps.setString(8, phoneNo);
            ps.setString(9, address);
            ps.execute();
            connection.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);

        }
    }


    void addFriend(String myUserId, String [] friend){
        Scanner input = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String userName = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM logIn");
            System.out.print("Please Choise and Click User Id as Input: \nName: ");

            while (resultSet.next()) {
                boolean flag = true;
                for (int i=0; i<friend.length; i++){
                    if(resultSet.getString("userId").toString().equals(friend[i])||resultSet.getString("userId").toString().equals(myUserId)){
                        flag = false;
                    }
                }
                if(flag==true){
                    System.out.println(resultSet.getString("firstName") + "  " + resultSet.getString("lastName") + ",   User Id: " + resultSet.getString("userId"));
                }
            }
            System.out.println("Enter User Id");
            String userId = input.nextLine();
            String query = " insert into friendShip (who, whom, status)"
                    + " values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);


            ps.setString(1, myUserId);
            ps.setString(2,userId);
            ps.setString(3, "1");
            ps.execute();
            PreparedStatement ps1 = connection.prepareStatement(query);
            ps1.setString(2, myUserId);
            ps1.setString(1,userId);
            ps1.setString(3, "0");
            ps1.execute();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exp: Find Out" + e);
        }
    }






    void acceptRequest(String myUserId){
        Scanner input = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String userName = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM friendShip");
            System.out.print("Request For Friends: \n");

            while (resultSet.next()) {
                String x =resultSet.getString("who").toString();
                String y = resultSet.getString("whom").toString();
                String z = resultSet.getString("status").toString();
                if(x.equals(myUserId)&&z.equals("0")){
                    System.out.println(resultSet.getString("whom") );
                }
            }
            System.out.println("Enter User Id for accept");
            String userId = input.nextLine();
            String query = " update friendShip set status = ? where who = ? and whom = ?";
            PreparedStatement ps = connection.prepareStatement(query);


            ps.setString(2, myUserId);
            ps.setString(3,userId);
            ps.setString(1, "1");
            ps.execute();
            connection.close();
            System.out.println("One Friend Request is accepted");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exp: Find Out" + e);
        }
    }


    void showFriend(String myUserId, String [] friend){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String userName = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM friendShip");

            int i=0;
            while (resultSet.next()) {
                String x =resultSet.getString("who").toString();
                String y = resultSet.getString("whom").toString();
                String z = resultSet.getString("status").toString();
                if(x.equals(myUserId)&&z.equals("0")){
                    System.out.println(resultSet.getString("whom") );
                }
                else if(x.equals(myUserId)&&z.equals("1")){
                    friend[i]=y;
                    i++;
                }
            }
            System.out.println("Your Friends:");
            for(int j=i-1; j>=0; j--){
                System.out.println(friend[j]);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exp: Find Out" + e);
        }
    }










    void sendMessage(String myUserId){
        Scanner input = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String userName = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM logIn");
            System.out.print("Please Choise and Click User Id as Input for send Message: \nName: ");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstName") + "  " + resultSet.getString("lastName") + ",   User Id: " + resultSet.getString("userId"));
            }

            System.out.println("Enter User Id");
            String userId = input.nextLine();
            System.out.println("Enter a Message:");
            String message = input.nextLine();
            String query = " insert into message (who, whom, messages, status)"
                    + " values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);


            ps.setString(1, myUserId);
            ps.setString(2,userId);
            ps.setString(3, message);
            ps.setString(4, "2");
            ps.execute();
            PreparedStatement ps1 = connection.prepareStatement(query);
            ps1.setString(2, myUserId);
            ps1.setString(1, userId);
            ps1.setString(3, message);
            ps1.setString(4, "0");
            ps1.execute();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exp: Find Out" + e);
        }
    }



    void cheackMessage(String myUserId){
        Scanner input = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String userName = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM message");
            System.out.print("Your Have Message From: \n");

            while (resultSet.next()) {
                String x =resultSet.getString("who").toString();
                String y = resultSet.getString("whom").toString();
                String z = resultSet.getString("status").toString();
                if(x.equals(myUserId)&&z.equals("0")){
                    System.out.println(resultSet.getString("whom")+" (Not Seen Yet)" );
                }
                else if(x.equals(myUserId)&&z.equals("1")){
                    System.out.println(resultSet.getString("whom")+" (Already Seen)");
                }
                else if(x.equals(myUserId)&&z.equals("2")){
                    System.out.println(resultSet.getString("whom")+" (You Send)");
                }
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }


    void readMessage(String myUserId){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fbdb";
            String userName = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM message");
            Scanner input = new Scanner(System.in);
            System.out.println("Enter User Id for read");
            String userId = input.nextLine();
            String z="10";
            while (resultSet.next()) {
                String x =resultSet.getString("who").toString();
                String y = resultSet.getString("whom").toString();
                z = resultSet.getString("status").toString();
                if(x.equals(myUserId)&&y.equals(userId)){

                    if(z.equals("2")){
                        System.out.print("You : ");
                    }
                    else {
                        System.out.print(y+" : ");
                    }
                    System.out.println(resultSet.getString("messages") );
                    if(z.equals("0")){
                        String query = " update message set status = ? where who = ? and whom = ?";
                        PreparedStatement ps = connection.prepareStatement(query);
                        ps.setString(2, myUserId);
                        ps.setString(3,userId);
                        ps.setString(1,"1");
                        ps.execute();
                    }
                }

            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exp: Find Out" + e);
        }
    }









    public static void main (String[]args){

        // write your code here
        Main obj = new Main();

        System.out.println("Click 1 For User Ragistration:");
        System.out.println("Click 2 For Log In and Visit Your Profile:");
        System.out.println("Click 3 For Add Friend");
        System.out.println("Click 4 For See Friends");
        System.out.println("Click 5 For Accept Friends");
        System.out.println("Click 6 For Send Message:");
        System.out.println("Click 7 For Check Messages:");
        System.out.println("Click 8 For Read Message");
        System.out.println("Click Password as Admin For show Data:");
        System.out.println("Click 0 to Exit");
        int password = 12345;
        int key;
        Scanner input = new Scanner(System.in);
        String isLogIn = "Not Register";
        while (true) {
            System.out.println("Enter Your Choice:");
            key = input.nextInt();
            if (key == 1) {
                obj.userRagistration();
            } else if (key == 2) {
                isLogIn = obj.userLogIn();
            }
            else if(key==3){
                if(!isLogIn.equals("Not Register"))
                {
                    String [] friend = new String[100];
                    obj.showFriend(isLogIn, friend);
                    obj.addFriend(isLogIn,friend);
                }
                else {
                    System.out.println("Register In First");
                }
            }
            else if(key==4){
                String [] friend = new String[100];
                if(!isLogIn.equals("Not Register"))
                    obj.showFriend(isLogIn, friend);
                else {
                    System.out.println("Registr In First");
                }
            }

            else if(key==5){
                if(!isLogIn.equals("Not Register"))
                    obj.acceptRequest(isLogIn);
                else {
                    System.out.println("Register In First");
                }
            }
            else if(key==6){
                if(!isLogIn.equals("Not Register"))
                {

                    obj.sendMessage(isLogIn);
                }
                else {
                    System.out.println("Enter Log In First");
                }
            }

            else if(key==7){
                if(!isLogIn.equals("Not Register"))
                {

                    obj.cheackMessage(isLogIn);
                }
                else {
                    System.out.println("Enter Log In First");
                }
            }

            else if(key==8){
                if(!isLogIn.equals("Not Register"))
                {

                    obj.readMessage(isLogIn);
                }
                else {
                    System.out.println("Enter Log In First");
                }
            }





            else if (key == password) {
                obj.showData();
            }
            else if(key ==0 ){
                return;
            }
        }
    }
}
