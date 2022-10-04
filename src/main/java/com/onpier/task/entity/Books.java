package com.onpier.task.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Udayshree
 * 
 *         Entity for the books table
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor

public class Books implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6888663149414599832L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String author;

	private String genre;

	private String publisher;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "book", targetEntity = Borrowed.class)
	@JsonIgnore
	private List<Borrowed> borrowed;
}
