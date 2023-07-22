package com.devcaotics.PaoKentin.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devcaotics.PaoKentin.model.entities.Fornada;
import com.devcaotics.PaoKentin.model.entities.Pao;

public class RepositorioFornada
{
	private static RepositorioFornada myself = new RepositorioFornada();
	private RepositorioFornada() {}
	
	public static RepositorioFornada getCurrentInstance()
	{ return myself; }
	
	public void create(Fornada f) throws SQLException
	{
		String sql = "INSERT INTO Fornada(idPao, inicioDaFornada, fimDaFornada) VALUES(?, ?, ?)";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			Timestamp inicioDaFornada = new Timestamp(f.getInicioDaFornada().getTime());
			Timestamp fimDaFornada    = new Timestamp(f.getFimDaFornada().getTime());
			
			pstm.setInt(1, f.getTipoDePao().getId());
			pstm.setTimestamp(2, inicioDaFornada);
			pstm.setTimestamp(3, fimDaFornada);
			
			pstm.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Fornada> read(int id)
	{
		String sql = "SELECT * FROM Fornada as F JOIN Pao as P ON (F.idPao = P.id) WHERE F.idPao = ?";
		List<Fornada> fornadas = new ArrayList<>();
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			Fornada fornada = null;
			
			while (result.next())
			{
				fornada = new Fornada();
				fornada.setId(result.getInt("id"));
				fornada.setInicioDaFornada(new Date(result.getTimestamp("inicioDaFornada").getTime()));
				fornada.setFimDaFornada(new Date(result.getTimestamp("fimDaFornada").getTime()));
				
				Pao pao = new Pao();
				pao.setId(result.getInt("idPao"));
				pao.setNome(result.getString("nome"));
				pao.setDescricao(result.getString("descricao"));
				pao.setTempoDePreparoEmSegundos(result.getLong("tempoPreparo"));
				
				fornada.setTipoDePao(pao);
								
				fornadas.add(fornada);
			}
			System.out.println(fornadas.size());
			
			return fornadas;
		}
		catch(SQLException e)
		{
			e.printStackTrace();			
		}
		
		return null;
	}
	
	public Fornada readLast(int id)
	{
		String sql = "SELECT * FROM Fornada as F JOIN Pao as P ON (F.idPao = P.id) WHERE F.idPao = ? ORDER BY F.fimDaFornada DESC";
		List<Fornada> fornadas = new ArrayList<>();
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			Fornada fornada = null;
			
			while (result.next())
			{
				fornada = new Fornada();
				fornada.setId(result.getInt("id"));
				fornada.setInicioDaFornada(new Date(result.getTimestamp("inicioDaFornada").getTime()));
				fornada.setFimDaFornada(new Date(result.getTimestamp("fimDaFornada").getTime()));
				
				Pao pao = new Pao();
				pao.setId(result.getInt("idPao"));
				pao.setNome(result.getString("nome"));
				pao.setDescricao(result.getString("descricao"));
				pao.setTempoDePreparoEmSegundos(result.getLong("tempoPreparo"));
				
				fornada.setTipoDePao(pao);
				
				fornadas.add(fornada);
			}
			
			return fornadas.get(0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
