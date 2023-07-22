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
import com.devcaotics.PaoKentin.model.entities.Pao;
import com.devcaotics.PaoKentin.model.repositories.RepositorioPao;

@CrossOrigin("*")
@RestController
@RequestMapping("/pao")
public class PaoController
{
	@PostMapping
	public String create(@RequestBody Pao pao)
	{
		try
		{			
			RepositorioPao.getCurrentInstance().create(pao);
			return "Pao criado com sucesso!";
		}
		catch(SQLException e)
		{
			return "Não foi possível criar o pao";
		}
	}
	
	@GetMapping("/{id}")
	public Pao read(@PathVariable("id") int id)
	{
		Pao pao = RepositorioPao.getCurrentInstance().read(id);
		return pao;
	}
	
	@GetMapping
	public List<Pao> readAll()
	{
		return RepositorioPao.getCurrentInstance().readAll();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id)
	{
		RepositorioPao.getCurrentInstance().delete(id);
	}
}