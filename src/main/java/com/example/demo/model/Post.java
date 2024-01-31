package com.example.demo.model;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="post")
public class Post {
	@Id
	private int pid;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	private String ptitle;
	private String pcontent;
	private LocalDate date;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="username")
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
