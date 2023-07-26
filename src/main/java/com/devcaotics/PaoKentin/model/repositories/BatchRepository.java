package com.devcaotics.PaoKentin.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devcaotics.PaoKentin.model.entities.Batch;
import com.devcaotics.PaoKentin.model.entities.Bread;

public class BatchRepository
{
	private static BatchRepository myself = new BatchRepository();
	private BatchRepository() {}
	
	public static BatchRepository getCurrentInstance()
	{ return myself; }
	
	public void create(Batch b) throws SQLException
	{
		String sql = "INSERT INTO Batch(breadId, startTime, endTime) VALUES(?, ?, ?)";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			Timestamp startTime = new Timestamp(b.getStartTime().getTime());
			Timestamp endTime   = new Timestamp(b.getEndTime().getTime());
			
			pstm.setInt(1, b.getBread().getId());
			pstm.setTimestamp(2, startTime);
			pstm.setTimestamp(3, endTime);
			
			pstm.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Batch> read(int id)
	{
		String sql = "SELECT * FROM Batch as Batch JOIN Bread as Bread ON (Batch.breadId = Bread.id) WHERE Batch.breadId = ?";
		List<Batch> fornadas = new ArrayList<>();
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			Batch batch = null;
			
			while (result.next())
			{
				batch = new Batch();
				batch.setId(result.getInt("id"));
				batch.setStartTime(new Date(result.getTimestamp("startTime").getTime()));
				batch.setEndTime(new Date(result.getTimestamp("endTime").getTime()));
				
				Bread bread = new Bread();
				bread.setId(result.getInt("breadId"));
				bread.setName(result.getString("name"));
				bread.setDescription(result.getString("description"));
				bread.setPreparationTime(result.getLong("preparationTime"));
				
				batch.setBread(bread);
								
				fornadas.add(batch);
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
	
	public Batch readLast(int id)
	{
		String sql = "SELECT * FROM Batch as Batch JOIN Bread as Bread ON (Batch.breadId = Bread.id) WHERE Batch.breadId = ? ORDER BY Batch.endTime DESC";
		List<Batch> batches = new ArrayList<>();
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			Batch batch = null;
			
			while (result.next())
			{
				batch = new Batch();
				batch.setId(result.getInt("id"));
				batch.setStartTime(new Date(result.getTimestamp("startTime").getTime()));
				batch.setEndTime(new Date(result.getTimestamp("endTime").getTime()));
				
				Bread bread = new Bread();
				bread.setId(result.getInt("breadId"));
				bread.setName(result.getString("name"));
				bread.setDescription(result.getString("description"));
				bread.setPreparationTime(result.getLong("preparationTime"));
				
				batch.setBread(bread);
				
				batches.add(batch);
			}
			
			return batches.get(0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
