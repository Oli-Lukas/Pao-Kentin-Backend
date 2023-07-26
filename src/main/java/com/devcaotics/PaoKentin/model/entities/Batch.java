package com.devcaotics.PaoKentin.model.entities;

import java.util.Date;

public class Batch
{
	private int   id;
	private Bread bread;
	private Date  startTime;
	private Date  endTime;
	
	public int getId()
	{ return id; }
	
	public void setId(int id)
	{ this.id = id; }

	public Bread getBread()
	{ return bread;	}

	public void setBread(Bread bread)
	{ this.bread = bread; }

	public Date getStartTime()
	{ return startTime;	}

	public void setStartTime(Date startTime)
	{ this.startTime = startTime; }

	public Date getEndTime()
	{ return endTime; }

	public void setEndTime(Date endTime)
	{ this.endTime = endTime; }
}
