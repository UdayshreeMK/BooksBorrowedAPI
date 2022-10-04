package com.onpier.task.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Udayshree
 * 
 *         Entity for the Borrowed table
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "port_gen", sequenceName = "port_gen",  initialValue = 1)
public class Borrowed implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5852067608727862266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity = Users.class)
	@JoinColumn(name ="user_id",referencedColumnName = "id")
	private Users user;

	private String borrower;
	
	private String bookTitle;

	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity = Books.class)
	private Books book;
	

	private Date borrowedFrom;

	private Date borrowedTo;
}
