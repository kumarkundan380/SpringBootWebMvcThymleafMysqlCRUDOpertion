package com.kundan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue
	@Column(name = "std_id")
	private Integer stdId;
	
	@Column(name = "std_name")
	private String stdName;
	
	@Column(name = "std_gender")
	private String stdGen;
	
	@Column(name = "std_fee")
	private Double stdFee;
	
	@Column(name = "std_course")
	private String stdCourse;
	
	@ElementCollection
	private List<String> stdSlot;
	
	@Column(name = "std_address")
	private String stdAddr;

}
