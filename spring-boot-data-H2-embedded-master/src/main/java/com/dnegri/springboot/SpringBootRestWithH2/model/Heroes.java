package com.dnegri.springboot.SpringBootRestWithH2.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HEROES")
public class Heroes {

    @Column(name = "id")
    @Id
    Integer id;

    @Column(name = "name")
    String name;

    public Heroes(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public Heroes() {
		// TODO Auto-generated constructor stub
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
