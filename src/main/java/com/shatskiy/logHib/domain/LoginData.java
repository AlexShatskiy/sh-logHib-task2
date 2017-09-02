package com.shatskiy.logHib.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


//import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "logindata")
public class LoginData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "login")
	@NotNull(message="add login")
	@Size(min=3, message="login size min 3 char")
	@Size(max=30, message="login size max 10 char")
	private String login;
	
	@Column(name = "password")
	@NotNull(message="add password")
	@Size(min=5, message="password size min 5 char")
	private String password;
	
	@Column(name = "name")
	@Pattern(regexp = "^[a-zA-Z]*", message = "only character")
	private String name;
	
	@Column(name = "surname")
	@Pattern(regexp = "^[a-zA-Z]*", message = "only character")
	private String surname;
	
	@Column(name = "age")
	@Min(value=10, message="must be > 10")
	@Max(value=100, message="must be < 100")
	private Integer age;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "country")
	private String country;
	
	public LoginData() {
		super();
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String age) {
		this.gender = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
