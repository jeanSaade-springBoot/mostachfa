package com.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient 
{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	public String fName;
	public String lName;
	public String userCode;
	public String fatherName;
	public String emailAddress;
	public String fileNbr;
	public String telNumber;
	public String password;
	public String nationalNbr;
	public String age;
}
