package com.smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="CONTACT")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cid; //primary key for table CONTACT, auto generated
	
	@NotBlank(message="Please enter Full Name")
	@Size(min=4, max=25, message="Name must be between 4 to 25")
	private String name; //name of the user's contact
	
	@NotBlank(message="Please enter nick name")
	private String nickname; //Nickname of user's conact
	
	@NotBlank(message="Please enter email")
	@Email()
	private String email; //Email of user's conact
	
	
	private String image; //Image of user's conact
	
	@NotBlank(message="Please enter description")
	private String description; //Detail about user's conact
	
	@NotBlank(message="Please enter Work")
	private String work; //Type of work of user's conact
	
	@NotBlank(message="Please Phone Number")
	@Size(min=10,max=10, message="Phone number must be 10 digits")
	private String phone; //Phone number of user's contact
	
	
	
	
	
	@ManyToOne
	@JsonIgnore
	private User user; 
	
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//generating getters and setters 
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", name=" + name + ", nickname=" + nickname + ", email=" + email + ", image="
				+ image + ", description=" + description + ", work=" + work + ", phone=" + phone + ", user=" + user
				+ "]";
	}
	
	
	

}
