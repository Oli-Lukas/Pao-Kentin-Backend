package com.devcaotics.PaoKentin.model.entities;

import java.util.Date;

public class Fornada
{
	private int  id;
	private Pao  tipoDePao;
	private Date inicioDaFornada;
	private Date fimDaFornada;
	
	public int getId()
	{ return id; }
	
	public void setId(int id)
	{ this.id = id; }
	
	public Pao getTipoDePao()
	{ return tipoDePao;	}
	
	public void setTipoDePao(Pao tipoDePao)
	{ this.tipoDePao = tipoDePao; }
	
	public Date getInicioDaFornada()
	{ return inicioDaFornada; }
	
	public void setInicioDaFornada(Date inicioDaFornada)
	{ this.inicioDaFornada = inicioDaFornada; }
	
	public Date getFimDaFornada()
	{ return fimDaFornada; }
	
	public void setFimDaFornada(Date fimDaFornada)
	{ this.fimDaFornada = fimDaFornada; }
}
