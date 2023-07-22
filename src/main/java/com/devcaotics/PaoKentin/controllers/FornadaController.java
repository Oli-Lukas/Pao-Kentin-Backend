package com.devcaotics.PaoKentin.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcaotics.PaoKentin.model.entities.CalculoFornadaResponse;
import com.devcaotics.PaoKentin.model.entities.Fornada;
import com.devcaotics.PaoKentin.model.entities.Pao;
import com.devcaotics.PaoKentin.model.repositories.RepositorioFornada;
import com.devcaotics.PaoKentin.model.repositories.RepositorioPao;

@CrossOrigin("*")
@RestController
@RequestMapping("/fornada")
public class FornadaController
{
	@PostMapping("/{idPao}")
	public String create(@RequestBody Fornada fornada, @PathVariable("idPao") int idPao)
	{
		Pao pao = RepositorioPao.getCurrentInstance().read(idPao);
		fornada.setTipoDePao(pao);
		
		try
		{
			RepositorioFornada.getCurrentInstance().create(fornada);
			return "Fornada cadastrada com sucesso!";
		}
		catch(SQLException e)
		{
			return "Não foi possível cadastrar a fornada.";
		}
	}

	@GetMapping("/{id}")
	public List<Fornada> read(@PathVariable("id") int id)
	{
		return RepositorioFornada.getCurrentInstance().read(id);
	}
	
	@GetMapping("/last/{idPao}")
	public Fornada readLastFornada(@PathVariable("idPao") int idPao)
	{
		return RepositorioFornada.getCurrentInstance().readLast(idPao);
	}
	
	/*
	@GetMapping("/diferenca/{id}")
	public CalculoFornadaResponse calculaDiferenca(@PathVariable("id") int id)
	{
		CalculoFornadaResponse response = null;
		Fornada fornada = this.read(id);
		
		if (fornada == null)
			return null;
		
		long currentUnixTimestamp      = new Date().getTime();
		long fimDaFornadaUnixTimestamp = fornada.getFimDaFornada().getTime();
		
		long diferencaEntreTimestamps = Math.abs(fimDaFornadaUnixTimestamp - currentUnixTimestamp);
		boolean fornadaPronta = (currentUnixTimestamp >= fimDaFornadaUnixTimestamp);
		
		response = new CalculoFornadaResponse();
		response.setDiferencaEmSegundos(diferencaEntreTimestamps);
		response.setFornadaPronta(fornadaPronta);
		
		return response;
	}
	*/
}