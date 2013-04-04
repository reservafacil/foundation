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

import java.util.ArrayList;

import com.brazoft.foundation.gwt.client.ui.ProgressBar.ProgressBarOptions;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.ui.api.GridColumn;
import com.brazoft.foundation.gwt.client.ui.api.GridFilter;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row.Cell;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.brazoft.foundation.gwt.client.jso.JSObject;

@SuppressWarnings("unchecked")
public class DataGrid<J extends JSObject> extends AbstractTable<DataGrid<J>>
{
	private java.util.List<GridFilter<J>> filters = new ArrayList<GridFilter<J>>();
	
	private java.util.List<GridColumn<?, J>> columns = new ArrayList<GridColumn<?, J>>();
	
	private Header header = this.header();
	
	private Body body = this.body();
	
	private GridCaption caption = new GridCaption(this.header.row());
	
	private ProgressIndicator progress = new ProgressIndicator(this.header.row());
	
	private Row headerColumns = this.header.row();
	
	private DataGridOptions options;
	
	private GridFooter footer = new GridFooter(this.footer().row());
	
	private JsArray<J> rows;
	
	private int totalRows;
	
	public DataGrid()
	{
		this.className("table table-bordered datagrid");
		this.footer.pager.whenPaginate(new EventHandler()
		{
			@Override
			public void onEvent(Event e)
			{
				int page = (Integer) e.data();
				DataGrid.this.drawPage(page);
			}
		});
		
		this.footer.rowsPerPage.onChange(new ChangeHandler()
		{
			@Override
			public void onChange(ChangeEvent event)
			{
				DataGrid.this.footer.pager.reset();
				DataGrid.this.drawPage(1);
			}
		});
		
		this.add(this.progress);
	}
	
	public DataGrid<J> onDraw(EventHandler handler)
	{
		return this.addEvent("onDraw", handler);
	}
	
	public DataGrid<J> add(final GridColumn<?, J> column)
	{
		column.cell(this.headerColumns.cell());
		column.onClick(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				DataGrid.this.sort(column);
			}
		});
		
		this.columns.add(column);
		
		int colspan = this.columns.size();
		this.caption.colspan(colspan);
		this.footer.colspan(colspan);
		this.progress.colspan(colspan);
		
		return this;
	}
	
	public void add(GridFilter<J>... filters)
	{
		for(GridFilter<J> filter : filters)
		{
			this.filters.add(filter);
		}
	}
	
	public DataGridOptions options()
	{
		if(this.options == null)
		{
			this.options = new DataGridOptions();
		}
		
		return this.options;
	}
	
	public DataGrid<J> sort(GridColumn<?, J> column)
	{
		for(GridColumn<?, J> gc : this.columns)
		{
			gc.unsort();
		}
		
		this.footer.pager.reset();
		column.sort(this.rows);
		this.drawPage(1);
		
		return this;
	}
	
	public DataGrid<J> filter()
	{
		boolean apply;
		int totalRowsFiltered = 0;
		
		this.footer.pager.reset();
		
		for(int i = 0; i < this.rows.length(); i++)
		{
			J row = this.rows.get(i);
			
			for(GridFilter<J> filter : this.filters)
			{
				apply = filter.filter(row);
				row.set("_skip_", !apply);
				
				if(apply)
				{
					totalRowsFiltered++;
					break;
				}
			}
		}
		
		this.totalRows = totalRowsFiltered;		
		this.drawPage(1);
		
		return this;
	}
	
	public DataGrid<J> draw(JsArray<J> rows)
	{
		this.fireEvent("onDraw");
		
		this.rows = rows;
		this.totalRows = rows.length();
		
		this.drawPage(1);
		
		return this;
	}
	
	void drawPage(int page)
	{
		this.progress.working();
		
		int rowsPerPage = this.footer.rowsPerPage.getValue();
		
		int pages = this.totalRows / rowsPerPage;
		if(this.totalRows % rowsPerPage > 0)
		{
			pages++;
		}
		
		this.footer.pager.pages(pages);
		
		int numberOfRows = this.footer.rowsPerPage.getValue();
		int rowIndex = (page -1) * numberOfRows;
		numberOfRows = rowIndex + numberOfRows;
		
		this.body.detachChildren();
		
		int index = 0;
		int objectIndex = -1;
		
		for(; rowIndex < numberOfRows;)
		{
			if(rowIndex == this.totalRows)
			{
				break;
			}
			
			J object = this.rows.get(index++);
			
			if(object.hasProperty("_skip_") && object.getBoolean("_skip_"))
			{
				continue;
			}
			
			objectIndex++;
			if(objectIndex < rowIndex)
			{
				continue;
			}
			
			rowIndex++;
			
			Row row = this.body.row();
			
 			for (GridColumn<?, J> column : this.columns)
			{
				row.cell().text(column.toString(object));
			}
		}
		
		this.progress.done();
	}
	
	public class DataGridOptions
	{
		public DataGridOptions title(String title)
		{
			DataGrid.this.caption.title.text(title);
			return this;
		}
		
		public DataGridOptions searchPlaceholder(String placeholder)
		{
			DataGrid.this.caption.search.input.placeholder(placeholder);
			return this;
		}
		
		public DataGridOptions searchMode(GridSearchMode mode)
		{
			DataGrid.this.caption.search.mode(mode);
			return this;
		}
		
		public DataGridOptions disableFilter()
		{
			DataGrid.this.caption.search.disable();
			return this;
		}
		
		public DataGridOptions enableFilter()
		{
			DataGrid.this.caption.search.enable();
			return this;
		}
		
		public Spinner rowsPerPage()
		{
			return DataGrid.this.footer.rowsPerPage;
		}
	}
	
	public enum GridSearchMode
	{
		CONTAINS
		{
			@Override
			public boolean eval(String search, String text)
			{
				return text.contains(search);
			}
		},
		EQUALS
		{
			@Override
			public boolean eval(String search, String text)
			{
				return search.equals(text);
			}
		},
		EQUALS_IGNORE_CASE
		{
			@Override
			public boolean eval(String search, String text)
			{
				return search.equalsIgnoreCase(text);
			}
		},
		STARTS_WITH
		{
			@Override
			public boolean eval(String search, String text)
			{
				return text.startsWith(search);
			}
		},
		ENDS_WITH
		{
			@Override
			public boolean eval(String search, String text)
			{
				return text.endsWith(search);
			}
		};
		
		public abstract boolean eval(String search, String text);
	}
	
	class GridCaption
	{
		private Cell cell;
		
		private HTML<SpanElement> title = HTML.asSpan().className("datagrid-header-title");
		
		private HTML<DivElement> left = HTML.asDiv().className("datagrid-header-left");
		
		private GridSearch search = new GridSearch();
		
		public GridCaption(Row row)
		{
			super();
			this.init(row.cell());
		}
		
		private void init(Cell cell)
		{
			this.cell = cell;
			this.cell.add(this.title).add(this.left).add(this.search);
		}
		
		GridCaption colspan(int colspan)
		{
			this.cell.colspan(colspan);
			return this;
		}
		
		class GridSearch extends Bootstrap<GridSearch>
		{
			private ExtendedTextBox input = new ExtendedTextBox().className("datagrid-search").size(Size.DEFAULT);
			
			private Button searchButton = new Button().icon(Icon.FILTER);
			
			private Button clearButton = new Button().icon(Icon.REMOVE).hidden();
			
			private GridFilter<J> filter = new DefaultGridFilter();
			
			private GridSearchMode mode = GridSearchMode.CONTAINS;
			
			public GridSearch()
			{
				super(ElementResolver.div());
				this.init();
			}
			
			private void init()
			{
				this.className("datagrid-header-right").add(input);
				this.input.append(searchButton).append(clearButton);
				this.input.onChange(new ChangeHandler()
				{
					@Override
					public void onChange(ChangeEvent event)
					{
						GridSearch.this.search();
					}
				});
				
				this.searchButton.onClick(new ClickHandler()
				{
					@Override
					public void onClick(ClickEvent event)
					{
						GridSearch.this.search();
					}
				});
				
				this.clearButton.onClick(new ClickHandler()
				{
					@Override
					public void onClick(ClickEvent event)
					{
						GridSearch.this.input.value("");
						GridSearch.this.searchButton.visible();
						GridSearch.this.clearButton.hidden();
						GridSearch.this.filter.deactivate();
						DataGrid.this.filter();
					}
				});
				
				DataGrid.this.add(this.filter);
			}
			
			GridSearch mode(GridSearchMode mode)
			{
				this.mode = mode;
				return this;
			}
			
			GridSearch disable()
			{
				this.input.hidden();
				this.searchButton.hidden();
				this.clearButton.hidden();
				
				return this;
			}
			
			GridSearch enable()
			{
				this.input.visible();
				this.searchButton.visible();
				this.clearButton.hidden();
				
				return this;
			}
			
			GridSearch search()
			{
				this.searchButton.hidden();
				this.clearButton.visible();
				this.filter.activate();
				DataGrid.this.filter();

				return this;
			}
		}
	}
	
	class GridFooter
	{
		private Cell cell;
		
		private Spinner rowsPerPage = new Spinner().range(0, 100).value(5).step(5);
		
		private Pager pager = new Pager().className("grid-pager");
		
		public GridFooter(Row row)
		{
			super();
			this.init(row.cell());
		}
		
		private void init(Cell cell)
		{
			this.cell = cell;
			
			HTML<DivElement> left = HTML.asDiv().className("datagrid-footer-left");
			HTML<DivElement> controls = HTML.asDiv().className("grid-controls");
			left.add(controls.add(this.rowsPerPage));
			
			HTML<DivElement> right = HTML.asDiv().className("datagrid-footer-right");
			right.add(this.pager);
			
			this.cell.add(left).add(right);
		}
		
		GridFooter colspan(int colspan)
		{
			this.cell.colspan(colspan);
			return this;
		}
	}
	
	class ProgressIndicator extends Bootstrap<ProgressIndicator>
	{
		private ProgressBar bar = new ProgressBar(ProgressBarOptions.ANIMATED).worked(100);
		
		private Row row;
		
		private Cell cell;
		
		public ProgressIndicator(Row row)
		{
			super(ElementResolver.div());
			this.init(row);
		}
		
		private void init(Row row)
		{
			this.bar.style().marginBottom(0, Unit.PX);
			this.row = row;
			this.cell = row.cell();
			this.cell.add(this.bar);
			this.done();
		}
		
		ProgressIndicator colspan(int colspan)
		{
			this.cell.colspan(colspan);
			return this;
		}
		
		public ProgressIndicator working()
		{
			this.row.style().clearDisplay();
			return this;
		}
		
		public ProgressIndicator done()
		{
			this.row.style().display(Display.NONE);
			
			return this;
		}
	}
	
	class DefaultGridFilter extends GridFilter<J>
	{
		@Override
		protected boolean doFilter(J row)
		{
			String search = DataGrid.this.caption.search.input.getValue();
			
			if("".equals(search.trim()))
			{
				return true;
			}
			
			for(GridColumn<?, J> column : DataGrid.this.columns)
			{
				if(DataGrid.this.caption.search.mode.eval(search, column.toString(row)))
				{
					return true;
				}
			}
			
			return false;
		}
	}
}