import java.sql.*;

public class dao {

    private  String query;
    private  ResultSet rs;
    private  int res;
    private  PreparedStatement ps;
    public  void addCourse(Connection con ,String cName,int cDuration,int cFacultyId) throws SQLException {

        query="SELECT  facultyName from faculty where facultyId=?";
        ps=con.prepareStatement(query);
        ps.setInt(1,cFacultyId);
        rs =ps.executeQuery();
        if(rs.next()){
            query="insert into course(courseName,courseDuration,courseFaculty,facultyId) values(?,?,?,?)";
            ps= con.prepareStatement(query);
            ps.setString(1,cName);
            ps.setInt(2,cDuration);
            ps.setString(3,rs.getString(1));
            ps.setInt(4,cFacultyId);
            res=ps.executeUpdate();
                if(res>0){
                    query="select courseId from course where courseName=?";
                    ps=con.prepareStatement(query);
                    ps.setString(1,cName);
                    rs=ps.executeQuery();
                    if(rs.next()){
                        System.out.println("Course Added");
                        System.out.println("Course id :"+rs.getString(1));
                    }

            }
            else{
                System.out.println("Course not yet added please try again");
            }
        }
        else{
            System.out.println(" Sry no faculty Found in given Id Please try another");
        }

    }
    public  void deleteStudent(Connection con ,int rollNumber) throws SQLException {
        query ="delete from student where rollNumber=?";
        ps=con.prepareStatement(query);
        ps.setInt(1,rollNumber);
        res=ps.executeUpdate();
        if(res>0){
            System.out.println("Student Data Deleted");
        }
        else{
            System.out.println("Student data not yet Deleted");
        }
    }
    public  void updateStudent(Connection con ,int rollNumber, String name, Date dob, String address) throws SQLException {
        query="update  student set name=?,dob=?,address=? where rollNumber=?";
        ps= con.prepareStatement(query);
        ps.setString(1,name);
        ps.setDate(2,dob);
        ps.setString(3,address);
        ps.setInt(4,rollNumber);
        res=ps.executeUpdate();
        if(res>0){
            System.out.println("your Details has Successfully Updated");
        }
        else{
            System.out.println("your Details has not yet Updated");
        }



    }
    public  void addstudent(Connection con , String name, Date dob, String address) throws SQLException {
        query="insert into student(name,dob,address) values(?,?,?)";
        ps= con.prepareStatement(query);
        ps.setString(1,name);
        ps.setDate(2,dob);
        ps.setString(3,address);
        res=ps.executeUpdate();
        if(res>0)
        {
            query="select rollNumber from student where name=?";
            ps=con.prepareStatement(query);
            ps.setString(1,name);
            rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("Student add your roll number :"+rs.getInt(1));
            }

        }
        else {
            System.out.println("Something went wrong");
        }



    }
    public void deleteCourse(Connection con, int courseId) throws SQLException {
        query = "DELETE FROM course WHERE courseId=?";
        ps = con.prepareStatement(query);
        ps.setInt(1, courseId);
        res = ps.executeUpdate();
        if (res > 0) {
            System.out.println("Course Deleted Successfully");
        } else {
            System.out.println("Failed to delete course. Course ID may be invalid.");
        }
    }

    public void enrollStudent(Connection con, int rollNumber, int courseId) throws SQLException {
        query = "INSERT INTO Enrollments (rollNumber, courseId, EnrollmentDate) VALUES (?, ?, NOW())";
        ps = con.prepareStatement(query);
        ps.setInt(1, rollNumber);
        ps.setInt(2, courseId);
        res = ps.executeUpdate();
        if (res > 0) {
            System.out.println("Student Enrolled Successfully");
        } else {
            System.out.println("Failed to enroll student. Check Student ID and Course ID.");
        }
    }

    public void unenrollStudent(Connection con, int rollNumber, int courseId) throws SQLException {
        query = "DELETE FROM Enrollments WHERE rollNumber=? AND courseId=?";
        ps = con.prepareStatement(query);
        ps.setInt(1, rollNumber);
        ps.setInt(2, courseId);
        res = ps.executeUpdate();
        if (res > 0) {
            System.out.println("Student Unenrolled Successfully");
        } else {
            System.out.println("Failed to unenroll student. Check Student ID and Course ID.");
        }
    }

    public void assignFaculty(Connection con, int facultyId, int courseId) throws SQLException {
        query="SELECT facultyName from faculty where facultyId=?";
        ps=con.prepareStatement(query);
        ps.setInt(1,facultyId);
        rs=ps.executeQuery();
        if(rs.next()){
            query = "UPDATE Course SET facultyId=? ,courseFaculty=? WHERE courseId=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, facultyId);
            ps.setString(2,rs.getString(1));
            ps.setInt(3, courseId);
            res = ps.executeUpdate();
            if (res > 0) {
                System.out.println("Faculty Assigned to Course Successfully");
            } else {
                System.out.println("Failed to assign faculty to course. Check Faculty ID and Course ID.");
            }
        }
        else{
            System.out.println("Fauculty not found");
        }




    }
    public void updateCourse(Connection con, int courseId, String courseName, int courseDuration, int courseFacultyId) throws SQLException {
        // First, check if the provided Faculty ID exists
        query = "SELECT facultyId FROM Faculty WHERE facultyId=?";
        ps = con.prepareStatement(query);
        ps.setInt(1, courseFacultyId);
        rs = ps.executeQuery();
        if (rs.next()) {
            query = "UPDATE course SET courseName=?, courseDuration=?, facultyId=? WHERE courseId=?";
            ps = con.prepareStatement(query);
            ps.setString(1, courseName);
            ps.setInt(2, courseDuration);
            ps.setInt(3, courseFacultyId);
            ps.setInt(4, courseId);
            res = ps.executeUpdate();
            if (res > 0) {
                System.out.println("Course Updated Successfully");
            } else {
                System.out.println("Failed to update course. Course ID may be invalid.");
            }
        } else {
            System.out.println("Failed to update course. Faculty ID may be invalid.");
        }
    }

}
