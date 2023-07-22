package com.devcaotics.PaoKentin.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devcaotics.PaoKentin.model.entities.Pao;

public class RepositorioPao
{
	private static RepositorioPao myself = new RepositorioPao();
	
	private RepositorioPao() {}
	
	public static RepositorioPao getCurrentInstance()
	{ return myself; }
	
	public void create(Pao p) throws SQLException
	{
		String sql = "INSERT INTO Pao(nome, descricao, tempoPreparo) VALUES(?, ?, ?)";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setString(1, p.getNome());
			pstm.setString(2, p.getDescricao());
			pstm.setLong(3, p.getTempoDePreparoEmSegundos());
			
			pstm.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update(Pao p)
	{
		String sql = "UPDATE Pao SET nome=?, descricao=?, tempoPreparo=? WHERE id=?";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setString(1, p.getNome());
			pstm.setString(2, p.getDescricao());
			pstm.setLong(3, p.getTempoDePreparoEmSegundos());
			pstm.setLong(4, p.getId());
			
			pstm.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Pao read(int id)
	{
		String sql = "SELECT * FROM Pao WHERE id=?";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			ResultSet result = pstm.executeQuery();
			Pao pao = null;
			
			if (result.next())
			{
				pao = new Pao();
				pao.setId(id);
				pao.setNome(result.getString("nome"));
				pao.setDescricao(result.getString("descricao"));
				pao.setTempoDePreparoEmSegundos(result.getLong("tempoPreparo"));
			}
			
			return pao;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void delete(int id)
	{
		String sql = "DELETE FROM Pao WHERE id = ?";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Pao> readAll()
	{
		String sql = "SELECT * FROM Pao";
		List<Pao> paes = new ArrayList<>();
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			Pao pao = null;
			
			while (result.next())
			{
				pao = new Pao();
				pao.setId(result.getInt("id"));
				pao.setNome(result.getString("nome"));
				pao.setDescricao(result.getString("descricao"));
				pao.setTempoDePreparoEmSegundos(result.getLong("tempoPreparo"));
				
				paes.add(pao);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return paes;
	}
}