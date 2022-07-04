package com.dnegri.springboot.SpringBootRestWithH2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dnegri.springboot.SpringBootRestWithH2.model.Heroes;
import com.dnegri.springboot.SpringBootRestWithH2.service.HeroesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HeroesController {

    @Autowired
    private HeroesService heroesService;

        
    @GetMapping("/heroes")
    @Cacheable(value="id", key="#root.args[0].getId()")
	public ResponseEntity<List<Heroes>> getAllHeroes(@RequestParam(required = false) String name) {
		try {
			List<Heroes> heroes = new ArrayList<Heroes>();
			if (name == null)
				heroesService.findAll().forEach(heroes::add);
			else
				heroesService.findByNameContaining(name).forEach(heroes::add);
			if (heroes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(heroes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}  
    
    
    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.GET)
    ResponseEntity<Heroes> getHeroes(@PathVariable Integer id){
    	
    	Optional<Heroes> heroesData = heroesService.findById(id);
		if (heroesData.isPresent()) {
			return new ResponseEntity<>(heroesData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
    	
    }
   
    
    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.PUT)
    ResponseEntity<Heroes> updateHeroes(@PathVariable("id") Integer id, @RequestBody Heroes heroes) {
    	Optional<Heroes> heroesData = heroesService.findById(id);
		if (heroesData.isPresent()) {
			Heroes _heroes = heroesData.get();
			_heroes.setName(heroes.getName());
			
			return new ResponseEntity<>(heroesService.save(_heroes), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

     
    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.DELETE)
    ResponseEntity<HttpStatus> deleteHeroes(@RequestParam Integer id){
    	try {
    		heroesService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}
