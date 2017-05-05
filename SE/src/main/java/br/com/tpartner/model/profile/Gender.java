package br.com.tpartner.model.profile;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tp_profile_gender")
public class Gender implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	public Gender(){}
	public Gender(String name){
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
