package com.onpier.task.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Udayshree
 * 
 *         Entity for the Users table
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor

public class Users implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2244655707765174324L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;

	private String name;

	private Date memberSince;

	private Date memberTill;

	private String gender;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Borrowed.class)
	@JsonIgnore
	private List<Borrowed> borrowed;

}
