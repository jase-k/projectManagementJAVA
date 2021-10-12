package com.jasekraft.projectmanager.mvc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 5, max = 200, message ="Must be at least 5 characters and less than 200 characters")
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lead_id")
    private User teamLead;
    
    @NotNull
    @Size(min = 10, max = 255, message="Message must be between 10 -255 characters")
    private String description;
    
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    private List<User> team;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "projects_users", 
        joinColumns = @JoinColumn(name = "project_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> team;
    
    public Project() {
    }
   

    public Project(
			@NotNull @Size(min = 5, max = 200, message = "Must be at least 5 characters and less than 200 characters") String name,
			User teamLead,
			@NotNull @Size(min = 10, max = 255, message = "Message must be between 10 -255 characters") String description,
			@NotNull Date dueDate, List<User> team) {
		super();
		this.name = name;
		this.teamLead = teamLead;
		this.description = description;
		this.dueDate = dueDate;
		this.team = team;
	}


	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    
    public void addTeamMember(User user) {
    	this.team.add(user);
    }
    
    public void removeTeamMember(User user) {
    	if(user != this.getTeamLead()) {
    		this.team.remove(user);
    	}
    }
    
    
	public List<User> getTeam() {
		return team;
	}


	public void setTeam(List<User> team) {
		this.team = team;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


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


	public User getTeamLead() {
		return teamLead;
	}


	public void setTeamLead(User teamLead) {
		this.teamLead = teamLead;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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