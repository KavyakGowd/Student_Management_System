This Project will build a simple **Student Management System** web application using Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, and MySQL database.

We will build a CRUD operation for the Student entity in our **Student Management System** web application.



**Tools and Technologies Used**

1.Java 17

2.Spring Boot 

3.Spring MVC

4.Spring Data JPA ( Hibernate)

5.MySQL

6.Thymeleaf

 7.STS

**1. Create Spring Boot Project**

Let's open STS ( Spring Suite Tool) IDE to develop and bootstrap the Spring boot project.

Use the below guide to create a Spring boot project in  STS IDE: 

=> Create Spring Boot Project in Spring Tool Suite [STS]

Selected below dependencies while creating spring boot project using spring initializr:

- Spring Web
  
- Thymeleaf
  
- Spring Data JPA
  
- MySQL Driver
  
- Spring Boot Devtools
  
- Create Spring Boot Project in Spring Tool Suite [STS]

Selected below dependencies while creating spring boot project using spring initializr:

- Spring Web
  
- Thymeleaf
  
- Spring Data JPA
  
- MySQL Driver
  
- Spring Boot Devtools

**2. Create Spring Boot Project Structure**
   
Let's create the below packages in our Spring boot project:

- controller
  
- service
  
- repository
  
- entity

 **3. Maven Dependencies**
 
Note that we are using Spring Boot  and it requires minimum Java version 17 or later.

       <dependencies>
	      <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

 **3. Create Student JPA Entity**
    
    
Let's create a Student JPA entity under the entity package and add the following content to it:

It consists of a User class which consists of Entity's used to model our data application.

For each of our data , constructors , getters and setters are created.




@Entity

public class Student {

@Id

@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
 
	private String firstName;
 
	private String lastName;
 
	private String Email;
	
	public Student(long id, String firstName, String lastName, String email) {
 
		super();
  
		this.id = id;
  
		this.firstName = firstName;
  
		this.lastName = lastName;
  
		Email = email;
	}

	public Student() {
		
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", Email=" + Email + "]";
	}
	
	
}

@Entity - This annotation specifies that the class is an entity. 

@Id - The @Id JPA annotation specifies the primary key of the entity.


**4. Create JPA StudentRepository**

 It consists of UserDao which is used to manage the datas.
  
 Let's create a StudentRepository interface under the repository package and add the following content:



![repository](![Screenshot (17)](https://github.com/KavyakGowd/Student_Management_System/assets/156078895/388eb9c1-3fbe-4d4b-a1db-5c3e5d38d9b8)


**5. Configure MySQL Database**

Before configuring the MySQL database configuration in our Spring boot project, first, create a database named SMS in MySQL workbench:

**create database sms**

Let's open the application.properties file and add following content to it:


![database](![Screenshot (18)](https://github.com/KavyakGowd/Student_Management_System/assets/156078895/2691233f-ec51-4fa9-84ce-ce89da662ffa)

**6. Creating Service Layer**

StudentService Interface

Let's create a StudentService interface under the service package and add the following content to it:

import java.util.List;

import com.sbproject.sms.entity.Student;


public interface StudentService {
	
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	
	void deleteStudentById(Long id);
}


![service](![Screenshot (19)](https://github.com/KavyakGowd/Student_Management_System/assets/156078895/b516ac08-8db5-4efe-8855-1154a98e6f15)

**StudentServiceImpl Class**

Let's create a new package called impl inside the service package. 

It consists of UserService class which helps us to write the business functionalities.

@Service annotation is used to make the class as a service layer.

@Service

public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
  
	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);	
	}
}


**7. Controller Layer**

Let's create a StudentController class and add the following content to it:
 
It consists of UserController class which basically control the flow of data.

@RestController annotation is used to make the UserController class as controller layer.

@GetMapping , @PostMapping , @PutMapping , @DeleteMapping annotations are used to perform the CRUD operations



@Controller

public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	// handler method to handle list students and return mode and view
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		// create student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "update_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		
		// get student from database by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		// save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";		
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}	
}


**8. View Layer**

**resources/templates/students.html**

Let's create a students.html file and add the following content to it:

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Student Management System</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>	
  <nav class="navbar navbar-expand-md bg-dark navbar-dark">
	   <a class="navbar-brand" href="#">Student Management System</a>

  <!-- Toggler/collapsibe Button -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" th:href="@{/students}">Student Management</a>
      </li>
     
    </ul>
  </div>
</nav>
    <div class ="container">
		<div class = "row">
			<h1> List Students </h1>
		</div>
		
		<div class = "row">
			<div class = "col-lg-3">
				<a th:href = "@{/students/new}" class = "btn btn-primary btn-sm mb-3"> Add Student</a>
			</div>
		</div>
		<table class = "table table-striped table-bordered">
			<thead class = "table-dark">
				<tr>
					<th> Student First Name</th>
					<th> Student Last Name</th>
					<th> Student Email </th>
					<th> Actions </th>
				</tr>
			</thead>
			
			<tbody>
				<tr th:each = "student: ${students}">
					<td th:text = "${student.firstName}"></td>
					<td th:text = "${student.lastName}"></td>
					<td th:text = "${student.email}"></td>
					<td>
						<a th:href = "@{/students/edit/{id}(id=${student.id})}"
						 class = "btn btn-primary">Update</a>
						
						<a th:href = "@{/students/{id}(id=${student.id})}"
						 class = "btn btn-danger">Delete</a>
						 
					</td>
				</tr>
			</tbody>
		
		</table>
		
	</div>
</body>
</html>

We have used th:each Thymeleaf attribute in our template to iterate the list of students:


***resources/templates/create_student.html***

Let's create a create_student.html file and add the following content to it:

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Student Management System</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="#">Student Management System</a>

  <!-- Toggler/collapsibe Button -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" th:href="@{/students}">Student Management</a>
      </li>
    </ul>
  </div>
</nav>
<br>
<br>
	<div class = "container">
		<div class = "row">
			<div class ="col-lg-6 col-md-6 col-sm-6 container justify-content-center card">
				<h1 class = "text-center"> Create New Student </h1>
				<div class = "card-body">
					<form th:action="@{/students}" th:object = "${student}" method="POST">
						<div class ="form-group">
							<label> Student First Name </label>
							<input
							type = "text"
							name = "firstName"
							th:field = "*{firstName}"
							class = "form-control"
							placeholder="Enter Student First Name" 
							/>
						</div>
						
						<div class ="form-group">
							<label> Student Last Name </label>
							<input
							type = "text"
							name = "lastName"
							th:field = "*{lastName}"
							class = "form-control"
							placeholder="Enter Student Last Name" 
							/>
						</div>
						
						<div class ="form-group">
							<label> Student Email </label>
							<input
							type = "text"
							name = "email"
							th:field = "*{email}"
							class = "form-control"
							placeholder="Enter Student Email" 
							/>
						</div>
						
						<div class = "box-footer">
							<button type="submit" class = "btn btn-primary">
								Submit
							</button>
						</div>
					</form>
				
				</div>
			</div>
		</div>
	</div>
</body>
</html>


**resources/templates/update_student.html**

Let's create an update_student.html file and add the following content to it:

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Student Management System</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="#">Student Management System</a>

  <!-- Toggler/collapsibe Button -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" th:href="@{/students}">Student Management</a>
      </li>
    </ul>
  </div>
</nav>
<br>
<br>
	<div class = "container">
		<div class = "row">
			<div class ="col-lg-6 col-md-6 col-sm-6 container justify-content-center card">
				<h1 class = "text-center"> Update Student </h1>
				<div class = "card-body">
					<form th:action="@{/students/{id} (id=${student.id})}" th:object = "${student}" method="POST">
						<div class ="form-group">
							<label> Student First Name </label>
							<input
							type = "text"
							name = "firstName"
							th:field = "*{firstName}"
							class = "form-control"
							placeholder="Enter Student First Name" 
							/>
						</div>
						
						<div class ="form-group">
							<label> Student Last Name </label>
							<input
							type = "text"
							name = "lastName"
							th:field = "*{lastName}"
							class = "form-control"
							placeholder="Enter Student Last Name" 
							/>
						</div>
						
						<div class ="form-group">
							<label> Student Email </label>
							<input
							type = "text"
							name = "email"
							th:field = "*{email}"
							class = "form-control"
							placeholder="Enter Student Email" 
							/>
						</div>
						
						<div class = "box-footer">
							<button type="submit" class = "btn btn-primary">
								Submit
							</button>
						</div>
					</form>
				
				</div>
			</div>
		</div>
	</div>
</body>
</html>


**9. Run Spring Boot Application**

Run the Spring boot application with the main class:

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
		System.out.println("Student Management System Running Succuessfully");
	}

}


**10. Demo**

Once the Spring boot application is up and running then use the below URL to access this application:

**URL: http://localhost:8080/students**

**List Students**

![List Student] (![Screenshot (3)](https://github.com/KavyakGowd/Student_Management_System/assets/156078895/23930833-a756-4cbb-8959-ad807646ed21)
)

**Add Student**

![Add Student](![Screenshot (4)](https://github.com/KavyakGowd/Student_Management_System/assets/156078895/78feba73-18bd-4e91-8fd2-b1f18b9927cb)
)

**Update Student**
![update student] (![Screenshot (5)](https://github.com/KavyakGowd/Student_Management_System/assets/156078895/c46ba50f-947f-45d5-af9e-4ff21fd9b13c)
)
