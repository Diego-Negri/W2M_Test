package com.dnegri.springboot.SpringBootRestWithH2.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnegri.springboot.SpringBootRestWithH2.model.Heroes;

import java.util.List;

public interface HeroesService extends JpaRepository<Heroes, Integer>{
	
	List<Heroes> findByNameContaining(String name);
}
