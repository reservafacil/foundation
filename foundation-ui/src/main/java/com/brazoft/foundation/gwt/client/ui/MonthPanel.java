/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brazoft.foundation.gwt.client.ui;

import java.util.Date;

import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Body;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Header;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row.Cell;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.brazoft.foundation.gwt.client.i18n.DateFormat;
import com.brazoft.foundation.gwt.client.json.JSONCollection;
import com.brazoft.foundation.gwt.client.util.Calendar;
import com.brazoft.foundation.gwt.client.util.Calendar.Month;
import com.brazoft.foundation.gwt.client.util.Calendar.WeekDay;
import com.google.gwt.user.client.ui.Widget;

public class MonthPanel extends Bootstrap<MonthPanel>
{
	private Table		table	= new Table().className("table-condensed");

	private Selector	selector;

	private Days		days;

	private Calendar	currentDate = Calendar.now();
	
	private boolean selected;
	
	public MonthPanel()
	{
		super(ElementResolver.div());
		this.init();
	}
	
	MonthPanel onPrevious(ClickHandler handler)
	{
		this.selector.onPrevious(handler);
		return this;
	}
	
	MonthPanel onNext(ClickHandler handler)
	{
		this.selector.onNext(handler);
		return this;
	}
	
	Calendar current()
	{
		return this.currentDate;
	}

	private void init()
	{
		this.className("calendar").add(this.table);
		
		this.selector = new Selector(this.table.header());
		
		this.days = new Days(this.table.body());
		
		this.selector.onPrevious(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				MonthPanel.this.previous();
				MonthPanel.this.selected = false;
			}
		});
		
		this.selector.onNext(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				MonthPanel.this.next();
				MonthPanel.this.selected = false;
			}
		});
	}
	
	public MonthPanel selected(boolean selected)
	{
		this.selected = selected;
		return this;
	}
	
	public boolean isSelected()
	{
		return this.selected;
	}
	
	public MonthPanel onSelection(EventHandler handler)
	{
		return this.addEvent("selection", handler);
	}
	
	public static Date getValue(Event event)
	{
		return (Date) event.data();
	}
	
	public MonthPanel range(Date start, Date end)
	{
		return this.range(Calendar.as(start), Calendar.as(end));
	}
	
	public MonthPanel range(Calendar start, Calendar end)
	{
		this.days.range.start(start);
		this.days.range.end(end);
		
		if(start.after(this.currentDate))
		{
			this.currentDate = Calendar.clone(start);
		}
		
		this.days.renderMonth(this.currentDate);
		this.selector.set(this.currentDate);
		
		return this;
	}
	
	MonthPanel next()
	{
		return this.runToMonth(1);
	}
	
	MonthPanel previous()
	{
		return this.runToMonth(-1);
	}
	
	private MonthPanel runToMonth(int months)
	{
		this.currentDate.addMonths(months);
		
		this.selector.set(this.currentDate);
		this.days.renderMonth(this.currentDate);
		this.days.range.currentMonth = this.currentDate.getMonth();
		
		return this;
	}
	
	public MonthPanel set(Calendar calendar)
	{
		this.currentDate = calendar;
		
		this.selector.set(calendar);
		this.days.set(calendar);
		
		return this;
	}
	
	public MonthPanel set(Date date)
	{
		return this.set(Calendar.as(date));
	}
	
	class Days
	{
		private Body	body;

		private Cell	currentDay;

		private Range	range;
		
		public Days(Body body)
		{
			this.init(body);
		}
		
		private void init(Body body)
		{
			this.range = new Range();
			this.body = body;
			
			//Init table
			for(int w = 0; w < 6; w++)
			{
				Row row = body.row();
				for(int d = 0; d < 7; d++)
				{
					row.cell().onClick(new ClickHandler()
					{
						@Override
						public void onClick(ClickEvent event)
						{
							Cell cell = (Cell) event.getSource();
							Calendar calendar = Days.this.toCalendar(cell);
							if(Days.this.range.eval(calendar) && !cell.getStyleName().contains("off"))
							{
								Days.this.selectDate(calendar, cell);
								MonthPanel.this.fireEvent("selection", new Event(MonthPanel.this, calendar.toDate()));
							}
						}
					}).onMouseDown(new MouseDownHandler()
					{
						@Override
						public void onMouseDown(MouseDownEvent event)
						{
							MonthPanel.this.selected = true;
						}
					});
				}
			}
			
			this.renderMonth(MonthPanel.this.currentDate);
		}
		
		Calendar toCalendar(Cell cell)
		{
			return Calendar.time(Long.valueOf(cell.getAttribute("date-time"))); 
		}
		
		public Days set(Calendar calendar)
		{
			if(!this.range.currentMonth.equals(calendar.getMonth()))
			{
				this.renderMonth(calendar);
				return this;
			}
			
			//find cell to select
			for(Widget widget : this.body.getChildren())
			{
				Row row = (Row) widget;
				Cell cell = row.cell(calendar.getDay().ordinal());
				Calendar cellCalendar = this.toCalendar(cell);
				
				if(cellCalendar.equalsIgnoreTime(calendar))
				{
					this.selectDate(calendar, cell);
					break;
				}
			}
			
			return this;
		}
		
		void selectDate(Calendar calendar, Cell cell)
		{
			//remove active from last active day
			if(this.currentDay != null)
			{
				this.decorate(this.currentDay, this.toCalendar(this.currentDay));
			}
			
			cell.setStyleName("active");
			this.currentDay = cell;
		}
		
		void renderMonth(Calendar base)
		{
			Calendar runner = Calendar.clone(base).moveToFirstDayOfMonth();
			
			this.range.currentMonth = runner.getMonth();
			
			//Adjust calendar to the first day of week (Sunday)
			while(!runner.getDay().equals(WeekDay.SUNDAY))
			{
				runner.addDays(-1);
			}
			
			for(int w = 0; w < 6; w++)
			{
				Row row = this.body.row(w);
				for(int d = 0; d < 7; d++)
				{
					Cell cell = row.cell(d).text(String.valueOf(runner.getDate())).attribute("date-time", String.valueOf(runner.getTime()));

					this.decorate(cell, runner);
					
					runner.addDays(1);
				}
			}
		}
		
		void decorate(Cell cell, Calendar runner)
		{
			if(!range.eval(runner))
			{
 				cell.setStyleName("off disabled");
				return;
			}
			
			cell.setStyleName("available in-range");
		}
		
		class Range
		{
			private Calendar start;
			
			private Calendar end;
			
			private Month currentMonth;
			
			void start(Calendar start)
			{
				if(start == null)
				{
					return;
				}
				this.start = start.clearTime();
			}
			
			void end(Calendar end)
			{
				if(end == null)
				{
					return;
				}
				this.end = end.clearTime();
			}
			
			public boolean eval(Calendar calendar)
			{
				boolean eval = true;
				
				if(!calendar.getMonth().equals(this.currentMonth))
				{
					return false;
				}
				
				if(this.start == null && this.end == null)
				{
					return true;
				}
				
				if(this.start != null && !(calendar.equalsIgnoreTime(this.start) || calendar.after(this.start)))
				{
					eval = false;
				}
				
				if(this.end != null && !(calendar.equalsIgnoreTime(this.end) || calendar.before(this.end)))
				{
					eval = eval && false;
				}
				
				return eval;
			}
		}
	}

	class Selector
	{
		private Cell previous;
		
		private Cell month;
		
		private Cell next;
		
		private DateFormat monthNameFormat = DateFormat.YEAR_MONTH_ABBR;

		public Selector(Header header)
		{
			this.init(header);
		}
		
		void onPrevious(ClickHandler handler)
		{
			this.previous.onClick(handler);
		}
		
		void onNext(ClickHandler handler)
		{
			this.next.onClick(handler);
		}

		private void init(Header header)
		{
			Calendar now = Calendar.now();
			Row controls = header.row();
				
			this.previous = controls.cell().onMouseDown(this.selection);
			Widgets.setIcon(this.previous, Icon.CHEVRON_LEFT);
			
			this.month = controls.cell().colspan(5);
			this.set(now);
			
			this.next = controls.cell().onMouseDown(this.selection);
			Widgets.setIcon(this.next, Icon.CHEVRON_RIGHT);
			
			Row monthDays = header.row();
			
			JSONCollection<String> monthDayNames = Calendar.getMonthDayNames(now, WeekDay.SUNDAY);
			for(int i = 0; i < monthDayNames.size(); i++)
			{
				monthDays.cell().text(monthDayNames.get(i));
			}
		}
		
		private MouseDownHandler selection = new MouseDownHandler()
		{
			@Override
			public void onMouseDown(MouseDownEvent event)
			{
				MonthPanel.this.selected = true;
			}
		};
		
		Selector set(Calendar calendar)
		{
			this.month.text(calendar.toString(this.monthNameFormat));
			return this;
		}
	}
}