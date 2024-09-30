package com.smart.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;  //user id
	
	
	@NotBlank(message="Name cannot be empty")
	@Size(min=4, max=25, message="Name must be between 4 to 25")
	private String name;  //name of the user
	
	@Email()
	@NotBlank(message="Email cannot be empty")
	@Column(unique=true)
	private String email; //email of user, which is unique
	
	@NotBlank(message="Password cannot be empty")
	@Size(min=5, max=15, message="password size between 5 to 15")
	private String password; //password of the user account
	private String imageUrl; //image of user
	private String role; //role of user
	private boolean enabled;  //active status of user
	private String about;  //information about user
	
	//one user have multiple Contacts
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Contact> contacts=new ArrayList<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", imageUrl="
				+ imageUrl + ", role=" + role + ", enabled=" + enabled + ", about=" + about + ", contacts=" + contacts
				+ "]";
	}


	//generating getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
