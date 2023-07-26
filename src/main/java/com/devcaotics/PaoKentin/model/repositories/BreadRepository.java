package com.devcaotics.PaoKentin.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devcaotics.PaoKentin.model.entities.Bread;

public class BreadRepository
{
	private static BreadRepository myself = new BreadRepository();
	
	private BreadRepository() {}
	
	public static BreadRepository getCurrentInstance()
	{ return myself; }
	
	public void create(Bread b) throws SQLException
	{
		String sql = "INSERT INTO Bread(name, description, preparationTime) VALUES(?, ?, ?)";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setString(1, b.getName());
			pstm.setString(2, b.getDescription());
			pstm.setLong(3, b.getPreparationTime());
			
			pstm.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update(Bread b)
	{
		String sql = "UPDATE Bread SET name=?, description=?, preparationTime=? WHERE id=?";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setString(1, b.getName());
			pstm.setString(2, b.getDescription());
			pstm.setLong(3, b.getPreparationTime());
			pstm.setLong(4, b.getId());
			
			pstm.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Bread read(int id)
	{
		String sql = "SELECT * FROM Bread WHERE id=?";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			ResultSet result = pstm.executeQuery();
			Bread bread = null;
			
			if (result.next())
			{
				bread = new Bread();
				bread.setId(id);
				bread.setName(result.getString("name"));
				bread.setDescription(result.getString("description"));
				bread.setPreparationTime(result.getLong("preparationTime"));
			}
			
			return bread;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void delete(int id)
	{
		String sql = "DELETE FROM Bread WHERE id=?";
		
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
	
	public List<Bread> readAll()
	{
		String sql = "SELECT * FROM Bread";
		List<Bread> breads = new ArrayList<>();
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			Bread bread = null;
			
			while (result.next())
			{
				bread = new Bread();
				bread.setId(result.getInt("id"));
				bread.setName(result.getString("name"));
				bread.setDescription(result.getString("description"));
				bread.setPreparationTime(result.getLong("preparationTime"));
				
				breads.add(bread);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return breads;
	}
}