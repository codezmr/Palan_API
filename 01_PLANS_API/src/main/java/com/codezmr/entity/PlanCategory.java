package com.codezmr.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "PLAN_CATEGORY")
public class PlanCategory {
	
	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Integer categoryId; //primary key in DB
	
	@Column(name="CATEGORY_NAME")
	private String categoryName; 
	
	@Column(name="ACTIVE_SW")
	private String activeSw; //soft delete (show or hide record on UI)
	
	@Column(name="CREATED_BY")
	private String createdBy; //who is insert recored that person name
	
	@Column(name="UPDATED_BY")
	private String updatedBy; //who is update old recored that person name
	
	@Column(name="CREATED_DATE", updatable = false ) //updatable = false, for any record the column should't update;
	@CreationTimestamp //this is in hibernate
	private LocalDate createDate; //when record insert
	
	@Column(name="UPDATED_DATE", insertable = false ) //insertable =false, for any record the column should't insert, only update is allowed
	@UpdateTimestamp
	private LocalDate updateDate; //when record update
}
