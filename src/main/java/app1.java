import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class app1 {
    static Scanner sc=new Scanner(System.in);
  private static dao d=new dao();
    public app1() throws SQLException {
    }

    public static void display_Menu( ){
        System.out.println("University Enrollment System");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Add Course");
        System.out.println("5. Update Course");
        System.out.println("6. Delete Course");
        System.out.println("7. Enroll Student");
        System.out.println("8. Unenroll Student");
        System.out.println("9. Assign Faculty to Course");
        System.out.println("10. Exit");
    }
    public static void choose_menu(Connection con) throws SQLException {
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                addStudent(con);
                break;
            case 2:
                updateStudent(con);
                break;
            case 3:
                deleteStudent(con);
                break;
            case 4:
                addCourse(con);
                break;
            case 5:
                updateCourse(con);
                break;
            case 6:
                deleteCourse(con);
                break;
            case 7:
                enrollStudent(con);
                break;
            case 8:
                unenrollStudent(con);
                break;
            case 9:
                assignFaculty(con);
                break;
            case 10:
                System.out.println("Exiting...");
                return ;
            default:
                System.out.println("Invalid choice. Please try again.");
        }



    }




    public static void addStudent(Connection con) throws SQLException {

        System.out.println("1. Add Student");
        System.out.println("1. Enter Student name :");
        String name = sc.next();
        System.out.println("1. Enter Student dob (yyyy-MM-dd):");

        String dateStr = sc.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = dateFormat.parse(dateStr);
            System.out.println("Date entered: " + utilDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");

        }


        java.sql.Date dob = new java.sql.Date(utilDate.getTime());

        System.out.println("1. Enter Student address :");
        String address = sc.next();
        d.addstudent(con, name, dob, address);
    }
    public  static  void  updateStudent(Connection con) throws SQLException {
        System.out.println("2. Update Student");
        System.out.println("Enter roll number:");
        int roll_num= sc.nextInt();
        System.out.println("if there any changes ,kindly update it");
        System.out.println("1. Enter Student name :");
        String name = sc.next();
        System.out.println("1. Enter Student dob (yyyy-MM-DD):");
        String dateStr = sc.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
        java.util.Date utilDate = null;
        try {
            utilDate = dateFormat.parse(dateStr);
            System.out.println("Date entered: " + utilDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");

        }


        assert utilDate != null;
        java.sql.Date dob = new java.sql.Date(utilDate.getTime());

        System.out.println("1. Enter Student address :");
        String address = sc.next();

        d.updateStudent(con,roll_num,name,dob,address);


    }
    public  static  void deleteStudent(Connection con ) throws SQLException {
        System.out.println("3. deleteStudent");
        System.out.println("Enter roll number:");
        int roll_num= sc.nextInt();
        d.deleteStudent(con,roll_num);

    }
    public  static  void addCourse(Connection con) throws SQLException {
        System.out.println("4. addCourse");
        System.out.println("Enter CourseName :");
        String CourseName=sc.next();
        System.out.println("Enter CourseDuration in Months:");
        int CourseDuration=sc.nextInt();
        System.out.println("Enter CourseFacultyId :");
        int CourseFacultyId=sc.nextInt();
        d.addCourse(con,CourseName,CourseDuration,CourseFacultyId);
    }
    public static void deleteCourse(Connection con) throws SQLException {
        System.out.println("6. Delete Course");
        System.out.println("Enter Course ID:");
        int courseId = sc.nextInt();
        d.deleteCourse(con, courseId);
    }

    public static void enrollStudent(Connection con) throws SQLException {
        System.out.println("7. Enroll Student");
        System.out.println("Enter Student Roll Number:");
        int rollNumber = sc.nextInt();
        System.out.println("Enter Course ID:");
        int courseId = sc.nextInt();
        d.enrollStudent(con, rollNumber, courseId);
    }

    public static void unenrollStudent(Connection con) throws SQLException {
        System.out.println("8. Unenroll Student");
        System.out.println("Enter Student Roll Number:");
        int rollNumber = sc.nextInt();
        System.out.println("Enter Course ID:");
        int courseId = sc.nextInt();
        d.unenrollStudent(con, rollNumber, courseId);
    }

    public static void assignFaculty(Connection con) throws SQLException {
        System.out.println("9. Assign Faculty to Course");
        System.out.println("Enter Faculty ID:");
        int facultyId = sc.nextInt();
        System.out.println("Enter Course ID:");
        int courseId = sc.nextInt();
        d.assignFaculty(con, facultyId, courseId);
    }
    public static void updateCourse(Connection con) throws SQLException {
        System.out.println("5. Update Course");
        System.out.println("Enter Course ID:");
        int courseId = sc.nextInt();
        System.out.println("Enter new Course Name:");
        String courseName = sc.next();
        System.out.println("Enter new Course Duration (in months):");
        int courseDuration = sc.nextInt();
        System.out.println("Enter new Faculty ID:");
        int facultyId = sc.nextInt();

        d.updateCourse(con, courseId, courseName, courseDuration, facultyId);
    }


    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","Deep2005@");
            System.out.println("University Enrollment System");
            while(true){
                display_Menu();
                choose_menu(con);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
