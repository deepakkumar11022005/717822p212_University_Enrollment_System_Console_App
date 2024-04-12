Abstract: 
• The University Enrollment System offers a comprehensive solution for managing student 
enrollments, courses, and faculty assignments. Built to streamline administrative tasks, it provides 
functionalities for adding, updating, and deleting records in the Students, Courses, and Faculty 
entities. Additionally, the system enables administrators to enroll and unenroll students from 
courses, as well as assign faculty members to teach specific courses. Implemented with a user
friendly console interface, this system enhances efficiency in university administration by 
ensuring proper database design and seamless interaction with the system. 
 
1. Introduction: 
• Educational Institution Management: Educational institutions, particularly universities, face the 
challenge of efficiently managing student enrollments, course offerings, and faculty assignments. 
These tasks are crucial for maintaining the academic integrity and operational effectiveness of the 
institution. 
• Need for Streamlined Processes: With a myriad of administrative responsibilities, including 
adding, updating, and deleting student, course, and faculty records, there arises a need for a 
systematic and streamlined approach to handle these tasks. Manual management systems 
often prove to be inefficient and prone to errors. 
• University Enrollment System: In response to these challenges, the University Enrollment System 
is developed. This system aims to provide administrators with a comprehensive toolset to manage 
student enrollments, courses, and faculty assignments effectively. 
By centralizing administrative tasks and automating processes such as enrolling and unenrolling 
students from courses, the system promises to enhance efficiency and productivity within 
educational institutions. It empowers administrators to focus more on strategic initiatives rather 
than routine administrative tasks. 
2 
University Enrollment System 
2. System Analysis: 
Requirements Analysis:   
Stakeholder Interviews: Conduct interviews with stakeholders including university 
administrators, faculty members, and students to gather their requirements and expectations from 
the enrollment system. Identify key functionalities needed to streamline administrative tasks and 
improve user experience. 
Use Case Development: Develop use cases to capture the interactions between users 
(administrators, faculty, students) and the system. Define scenarios for adding, updating, and 
deleting student, course, and faculty records, as well as enrolling and unenrolling students from 
courses. 
Functional Requirements: Identify and document the functional requirements of the system, 
including the ability to add, update, and delete records in the Students, Courses, and Faculty 
entities. Specify the requirements for enrolling and unenrolling students from courses, as well as 
assigning faculty to teach courses. 
Non-functional Requirements: Determine the non-functional requirements such as 
performance, scalability, security, and usability. Define the expected response times for various 
operations, the system's ability to handle a large number of concurrent users, data security 
measures, and the user interface design principles to ensure ease of use. 
Validation and Verification: Validate the requirements with stakeholders to ensure that they 
accurately reflect their needs and expectations. Verify the requirements against industry best 
practices and standards to ensure completeness and correctness. Update the requirements 
documentation based on feedback and validation results. 
3. System Design: 
Database Schema Design: 
Table: student 
Columns: - rollNumber (INT, PRIMARY KEY): Unique identifier for each student - Name (VARCHAR): Name of the student. - dob(DATE): DOB of the student. - address (VARCHAR):  Address of the student. 
Table: Faculty 
Columns: - facultyId (INT, PRIMARY KEY): Unique identifier for each faculty member. - facultyName (VARCHAR): Name of the faculty member. 
Table: Course 
Columns: - courseId (INT, PRIMARY KEY): Unique identifier for each course. 
3 
University Enrollment System 
- courseName (VARCHAR): Name of the course. - courseDuration (INT): Duration of the course in months. - courseFaculty (VARCHAR): Name of the faculty teaching the course. - facultyId (INT, FOREIGN KEY): Foreign key referencing the facultyId in the Faculty 
table. 
Table: Enrollments 
Columns: - enrollmentId (INT, PRIMARY KEY): Unique identifier for each enrollment. - rollNumber (INT, FOREIGN KEY): Roll number of the student enrolled in the course. - courseId (INT, FOREIGN KEY): Course ID of the course in which the student is 
enrolled. - enrollmentDate (DATE): Date of enrollment. 
Application Architecture Design: 
Presentation Layer: - User Interface: Command-line interface (CLI) for user interaction. 
Business Logic Layer: - Java classes responsible for handling business logic, including input validation, database 
operations (CRUD operations), and enrollment management. 
Data Access Layer: - Database connectivity and interaction through JDBC (Java Database Connectivity). - Prepared statements for executing SQL queries and interacting with the MySQL database. 
UML Diagrams: 
Since the system architecture is relatively simple and the components are described above, UML 
diagrams such as Class Diagrams or Sequence Diagrams may not be necessary for this design. 
However, if needed, we can create basic diagrams to illustrate the relationships between classes 
or the flow of interactions between system components. 
4. Implementation: 
Environment Setup: 
• 
• 
JDBC Setup 
: 
Ensure you have the JDBC driver for MySQL. You can download it from the MySQL 8. 
Add the JDBC driver JAR file to your project's classpath. 
Database Choice 
: 
MySQL8 is used for this project. Make sure you have MySQL8 installed on your system or 
have access to a MySQL server.
