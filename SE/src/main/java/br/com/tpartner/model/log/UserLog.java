package br.com.tpartner.model.log;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.tpartner.model.infrastructure.User;
import java.io.Serializable;

@Entity
@Table (name = "userlog")

public class UserLog implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private User user;
	
	private String title;
	
	private String description;
	
	private String image;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	private String activity;
	
	public Integer getid() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
        
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
}
