package com.devcaotics.PaoKentin.model.entities;

public class Pao
{
	private int    id;
	private String nome;
	private String descricao;
	private long   tempoDePreparoEmSegundos;
	
	public int getId()
	{ return id; }
	
	public void setId(int id)
	{ this.id = id; }
	
	public String getNome()
	{ return nome; }
	
	public void setNome(String nome)
	{ this.nome = nome;	}
	
	public String getDescricao()
	{ return descricao;	}
	
	public void setDescricao(String descricao)
	{ this.descricao = descricao; }

	public long getTempoDePreparoEmSegundos()
	{ return tempoDePreparoEmSegundos; }
	
	public void setTempoDePreparoEmSegundos(long tempoDePreparoEmSegundos)
	{ this.tempoDePreparoEmSegundos = tempoDePreparoEmSegundos;	}
}