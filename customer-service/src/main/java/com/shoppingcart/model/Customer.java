package com.shoppingcart.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.*;

@Entity
@Table(name = "customers", catalog = "shopping_cart_db")
public class Customer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -902152316901000084L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;
	@Column(name = "password", nullable = false, length = 256)
	private String password;
	// private List<Order> orderses = new ArrayList<Order>();
	// private List<Cart> cartses = new ArrayList<Cart>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
