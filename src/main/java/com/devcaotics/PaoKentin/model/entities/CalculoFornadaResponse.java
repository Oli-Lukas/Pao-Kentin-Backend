package com.devcaotics.PaoKentin.model.entities;

public class CalculoFornadaResponse
{
	private long diferencaEmSegundos;
	private boolean fornadaPronta;
	
	public long getDiferencaEmSegundos()
	{ return diferencaEmSegundos; }
	
	public void setDiferencaEmSegundos(long diferencaEmSegundos)
	{ this.diferencaEmSegundos = diferencaEmSegundos; }

	public boolean isFornadaPronta()
	{ return fornadaPronta;	}

	public void setFornadaPronta(boolean fornadaPronta)
	{ this.fornadaPronta = fornadaPronta; }
}
