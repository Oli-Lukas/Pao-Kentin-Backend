package com.devcaotics.PaoKentin.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devcaotics.PaoKentin.model.entities.Bread;
import com.devcaotics.PaoKentin.model.repositories.BreadRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/bread")
public class BreadController
{
	@PostMapping
	public String create(@RequestBody Bread pao)
	{
		try
		{
			BreadRepository.getCurrentInstance().create(pao);
			return "Pao criado com sucesso!";
		}
		catch(SQLException e)
		{
			return "Não foi possível criar o pao";
		}
	}
	
	@GetMapping("/{id}")
	public Bread read(@PathVariable("id") int id)
	{
		Bread pao = BreadRepository.getCurrentInstance().read(id);
		return pao;
	}
	
	@GetMapping
	public List<Bread> readAll()
	{
		return BreadRepository.getCurrentInstance().readAll();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id)
	{
		BreadRepository.getCurrentInstance().delete(id);
	}
}