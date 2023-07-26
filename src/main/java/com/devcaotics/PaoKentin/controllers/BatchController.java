package com.devcaotics.PaoKentin.controllers;

import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devcaotics.PaoKentin.model.entities.Batch;
import com.devcaotics.PaoKentin.model.entities.Bread;
import com.devcaotics.PaoKentin.model.repositories.BatchRepository;
import com.devcaotics.PaoKentin.model.repositories.BreadRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/batch")
public class BatchController
{
	@PostMapping("/{breadId}")
	public String create(@RequestBody Batch batch, @PathVariable("breadId") int breadId)
	{
		Bread bread = BreadRepository.getCurrentInstance().read(breadId);
		batch.setBread(bread);
		
		try
		{
			BatchRepository.getCurrentInstance().create(batch);
			return "Fornada cadastrada com sucesso!";
		}
		catch(SQLException e)
		{
			return "Não foi possível cadastrar a fornada.";
		}
	}

	@GetMapping("/{id}")
	public List<Batch> read(@PathVariable("id") int id)
	{
		return BatchRepository.getCurrentInstance().read(id);
	}
	
	@GetMapping("/last/{breadId}")
	public Batch readLastFornada(@PathVariable("breadId") int breadId)
	{
		return BatchRepository.getCurrentInstance().readLast(breadId);
	}
}