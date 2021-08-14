package com.example.demo.entity.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.common.Erole;
import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_role")
public class Role extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	private Erole name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();
	
	public Role() {}

	public Role(Erole name) {
		super();
		this.name = name;
	}



	public Erole getName() {
		return name;
	}

	public void setName(Erole name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	

}
