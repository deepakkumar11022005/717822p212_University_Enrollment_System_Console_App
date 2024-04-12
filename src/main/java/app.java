

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class app {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking","root","Deep2005@");
            String sql8="Select * from room_available";
            String sql7="Select *from user";
            String sql6="update room_available set user_name=null , user_id=null , flag=0 where user_name=? and user_id=? ";
            String sql5="UPDATE user SET outdate = ?,flag=0 WHERE user_id = ? AND user_name = ?;";
            String sql4 = "update  room_available set user_name=?,user_id = ? ,flag=? where Room_no=?";
            String sql3 = "Select * from room_available where flag = 0 limit 1";
            String sql2="select * from user where phone_no=? and user_name = ? ";
            String sql="insert into User(user_name,phone_no,address,indate,flag) values(?,?,?,?,?)";
            Scanner sc=new Scanner(System.in);
            System.out.println("User or Admin(U/A)?");
            String get=sc.next();
            if(get.equals("U")) {
                System.out.println("If you want to Book Rooms(Y/N)?");

                String s = sc.next();
                System.out.println("Enter the name:");
                String name = sc.next();
                if(s.equals("Y")) {
                    System.out.println("Phone number:");
                    int phone=sc.nextInt();

                    System.out.println("Enter the Address:");
                    String address = sc.next();
                    System.out.println("Enter InDate:");
                    String indate = sc.next();
                    System.out.println("Thank You for entering your details!");
                    int flag = 1;
                    //inserting values to the table user
                    PreparedStatement  ps=con.prepareStatement(sql);
                    ps.setString(1,name);
                    ps.setInt(2,phone);
                    ps.setString(3,address);
                    ps.setString(4,indate);
                    ps.setInt(5, flag);
//	System.out.println("Start");
                    PreparedStatement ps2 = con.prepareStatement(sql2);
                    int ca = ps.executeUpdate();
                    //get user id from user
                    ps2.setInt(1, phone);
                    ps2.setString(2, name);
                    ResultSet rs=ps2.executeQuery();
//	System.out.println("Finish");
//	System.out.println(rs);
                    int value=0;
                    if (rs.next()) {
                        // Move the cursor to the first row and retrieve the value
                        value = rs.getInt("user_id");
                        // Process the retrieved value
                        System.out.println("Your User Id is :" + value);
                    } else {
                        System.out.println();
                    }

                    //retrive  room_no from Room_available table
                    PreparedStatement ps3 = con.prepareStatement(sql3);
                    ResultSet rss=ps3.executeQuery();
//	System.out.println(rs1);
                    int value1=0;
                    if (rss.next()) {
                        // Move the cursor to the first row and retrieve the value
                        value1 = rss.getInt("Room_no");
                        // Process the retrieved value
                        System.out.println("Your Room No is : " + value1);
                    } else {
                        System.out.println("Sorry No Room Available");
                    }

                    //Update the Room table
                    PreparedStatement  ps4=con.prepareStatement(sql4);
                    ps4.setString(1,name);
                    ps4.setInt(2,value);
                    ps4.setInt(3, 1);
                    ps4.setInt(4,value1);
                    int c1 = ps4.executeUpdate();
                    System.out.println("Your Data is updated in the table");
                }
                else {
                    System.out.println("Enter Your Id:");
                    int id=sc.nextInt();
                    System.out.println("Enter Out Date:");
                    String outdate=sc.next();
                    PreparedStatement  ps5=con.prepareStatement(sql5);
                    ps5.setString(1,outdate);
                    ps5.setInt(2,id);
                    ps5.setString(3,name);
                    int c1=ps5.executeUpdate();
                    System.out.println("Thank You for Visiting us!");
                    System.out.println("Happy Journey!!");
                    PreparedStatement  ps6=con.prepareStatement(sql6);
                    ps6.setString(1, name);
                    ps6.setInt(2, id);
                    int c3=ps6.executeUpdate();
                }
            }
            else {
                System.out.println("Details of Hotel Booking");
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql8);
                System.out.println("This is Hotel Room Booked or Available List");
                while(rs.next()) {
                    System.out.println("Room_no:"+rs.getInt(1)+" Guest_Name:"+rs.getString(2)+" Guest_Id:"+rs.getInt(3)+" Booked Or Not"+rs.getInt(4));
                }
                System.out.println();
                System.out.println();
                System.out.println("This is Guest Visited Details List");

                ResultSet rs1=st.executeQuery(sql7);
                while(rs1.next()) {
                    System.out.println(" Guest_Name:"+rs1.getString(2)+" Guest_Id:"+rs1.getInt(1)+" Address"+rs1.getString(3)+" Phone_no"+rs1.getInt(4)+" Indate:"+rs1.getString(5));
                }
            }
        }
        catch(Exception e) {System.out.println(e);}
    }
}