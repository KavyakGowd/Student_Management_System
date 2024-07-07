This Project will build a simple **Student Management System** web application using Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, and MySQL database.

We will build a CRUD operation for the Student entity in our **Student Management System**** web application.



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
  
- Spring Boot DevtoolsCreate Spring Boot Project in Spring Tool Suite [STS]

Selected below dependencies while creating spring boot project using spring initializr:

- Spring Web
  
- Thymeleaf
  
- Spring Data JPA
  
- MySQL Driver
  
- Spring Boot Devtools

2. Create Spring Boot Project Structure
   
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


 3. Create Student JPA Entity
    
Let's create a Student JPA entity under the entity package and add the following content to it:

It consists of a User class which consists of Entity's used to model our data application.

For each of our data , constructors , getters and setters are created.


import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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


4. Create JPA StudentRepository

 It consists of UserDao which is used to manage the datas.
  
Let's create a StudentRepository interface under the repository package and add the following content:


import org.springframework.data.jpa.repository.JpaRepository;

import com.sbproject.sms.entity.Student;



public interface StudentRepository extends JpaRepository<Student, Long>{

}

**5. Configure MySQL Database**

Before configuring the MySQL database configuration in our Spring boot project, first, create a database named SMS in MySQL workbench:

**create database sms**
Let's open the application.properties file and add following content to it:

  Let's open the application.properties file and add following content to it:Let's open the application.properties file and add following content to it:Let's open the application.properties file and add following content to it:
  
