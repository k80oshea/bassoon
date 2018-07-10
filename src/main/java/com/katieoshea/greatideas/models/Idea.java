package com.katieoshea.greatideas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ideas")
public class Idea {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min=5, max=255)
    private String content;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
//users
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User thinker;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "liked_ideas", 
        joinColumns = @JoinColumn(name = "idea_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
        )
    private List<User> ideaLikers;

//constructors
  	public Idea() {
  		this.createdAt = new Date();
		this.updatedAt = new Date();
  	}
  	
//getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getThinker() {
		return thinker;
	}
	public void setThinker(User thinker) {
		this.thinker = thinker;
	}
	public List<User> getIdeaLikers() {
		return ideaLikers;
	}
	public void setIdeaLikers(List<User> ideaLikers) {
		this.ideaLikers = ideaLikers;
	}

	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


}
