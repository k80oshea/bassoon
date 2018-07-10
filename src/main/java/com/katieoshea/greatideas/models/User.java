package com.katieoshea.greatideas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    @Size(min=1, max=64)
    private String name;
    @Email
    private String email;
    @Size(min=8)
    private String password;
    @Transient
    @Size(min=8)
    private String confirm;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
//ideas
    @OneToMany(mappedBy="thinker", fetch = FetchType.LAZY)
    private List<Idea> thoughts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "liked_ideas", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "idea_id")
        )
    private List<Idea> likedIdeas;
    
//constructors
    public User() {
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
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirm() {
        return confirm;
    }
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }  
	public List<Idea> getThoughts() {
		return thoughts;
	}
	public void setThoughts(List<Idea> thoughts) {
		this.thoughts = thoughts;
	}
	public List<Idea> getLikedIdeas() {
		return likedIdeas;
	}
	public void setLikedIdeas(List<Idea> likedIdeas) {
		this.likedIdeas = likedIdeas;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
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
