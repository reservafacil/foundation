package com.brazoft.foundation.showcase.client;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.*;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.ui.InputText;
import com.brazoft.foundation.gwt.client.ui.api.FloatPanel;
import com.brazoft.foundation.gwt.client.util.Entry;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.jso.JSIterable;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class Selection<S extends Selection<S, V>, V>
    extends Component<S>
    implements UIInput<S, V>, HasFocusHandlers<S> {

	private DropDown         menu      = new DropDown();

	private HTML<DivElement> mask      = HTML.asDiv().className("select2-drop-mask");

	private Container        container = new Container();

	private Entry            selection;

	private boolean          readonly;

	private boolean          required;

	public Selection() {
		super(ElementResolver.div());
		this.init();
	}

	private void init() {
		this.menu.target(this.container);
		this.add(this.container).add(this.mask).add(this.menu);
	}

	@Override
	public S onFocus(FocusHandler handler) {
		this.container.onFocus(handler);
		return (S)this;
	}

	@Override
	public S onBlur(BlurHandler handler) {
		this.container.onBlur(handler);
		return (S)this;
	}

	public S onSelection(EventHandler<Entry> handler) {
		this.menu.onSelection(handler);
		return (S)this;
	}

	public S open() {
		this.menu.open();
		
		return (S)this;
	}

	public S close() {
		this.menu.close();
		
		return (S)this;
	}

	public S resetable() {
		this.container.clear.visible();
		return (S)this;
	}

	public S minimumInputLength(int length) {
		// this.options.put("minimumInputLength", length);
		return (S)this;
	}

	public S maximumInputLength(int length) {
		this.menu.search.input.maxLength(length);
		return (S)this;
	}

	public S searchable(Matcher matcher) {
		this.menu.search.visible();
		return (S)this;
	}
	
    public Entry getSelection() {
	    return selection;
    }

	public S select(String value) {
		this.menu.results.child(value);
		return (S)this;
	}

	void select(Entry value) {
		this.selection = value;
		this.container.placeholder.text(value.getValue());
	}
	
	public S select(int index) {
		this.selection = this.menu.results.child(index).getValue();

		return (S)this;
	}

	public S deselect(int index) {
		// this.input.deselect(index);
		return (S)this;
	}

	public S deselect(String value) {
		return (S)this;
	}

	public S item(String value) {
		return this.item(value, value);
	}

	public S item(String text, String value) {
		this.menu.results.item(value, text);
		return (S)this;
	}

	public S items(JsArray<Entry> entries) {
		for (Entry entry : JSIterable.from(entries)) {
			this.menu.results.item(entry);
		}

		return (S)this;
	}

	@Override
	public S placeholder(String placeholder) {
		this.container.placeholder.attribute("data-placeholder", placeholder);
		this.container.text(placeholder);

		return (S)this;
	}

	@Override
	public boolean isReadOnly() {
		return this.readonly;
	}

	@Override
	public S readonly() {
		this.readonly = true;
		return (S)this;
	}

	@Override
	public boolean isEditable() {
		return !this.readonly;
	}

	@Override
	public S editable() {
		this.readonly = false;
		return (S)this;
	}

	@Override
	public boolean isNullable() {
		return !this.required;
	}

	@Override
	public S nullable() {
		this.required = false;
		return (S)this;
	}

	@Override
	public boolean isRequired() {
		return this.required;
	}

	@Override
	public S required() {
		this.required = true;
		return (S)this;
	}

	@Override
	public S clear() {
		return (S)this;
	}

	class Container
	    extends Component<Container>
	    implements HasFocusHandlers<Container>, HasText<Container> {

		private HTML<AnchorElement> choice      = HTML.asAnchor("javascript:void(0)").className("select2-choice");

		private HTML<SpanElement>   placeholder = HTML.asSpan().className("select2-chosen");

		private HTML<Element>       clear       = HTML.as(ElementResolver.abbr()).className("select2-search-choice-close").hidden();

		private HTML<DivElement>    arrow       = HTML.asDiv().add(HTML.as(ElementResolver.create("b")));

		public Container() {
			super(ElementResolver.div());
			this.setup();
		}

		private void setup() {
			this.className("select2-container").style().width(100, Unit.PCT);
			this.choice.onFocus(new FocusHandler() {

				@Override
				public void onFocus(FocusEvent event) {
					Container.this.activate();
				}
			}).onBlur(new BlurHandler() {

				@Override
				public void onBlur(BlurEvent event) {
					if(!Selection.this.menu.isHover()) {
						Container.this.deactivate();
						Selection.this.close();
					}
				}
			}).onClick(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Selection.this.menu.toggle();
				}
			}).onKeyPress(new KeyPressHandler() {

				@Override
				public void onKeyPress(KeyPressEvent event) {
					if (KeyCodes.KEY_ESCAPE == event.getNativeEvent().getKeyCode()) {
						Selection.this.close();
					}
				}
			});

			this.choice.add(this.placeholder).add(this.clear).add(this.arrow);
			this.add(this.choice);
		}

		@Override
		public Container onBlur(BlurHandler handler) {
			this.choice.onBlur(handler);
			return this;
		}

		@Override
		public Container onFocus(FocusHandler handler) {
			this.choice.onFocus(handler);
			return this;
		}

		@Override
		public Container blur() {
			this.deactivate();
			return super.blur();
		}

		@Override
		public Container focus() {
			this.activate();
			return super.focus();
		}

		@Override
		public Container text(String text) {
			this.placeholder.text(text);
			return this;
		}

		@Override
		public String getText() {
			return this.placeholder.getText();
		}

		private void activate() {
			this.className("select2-container-active");
		}

		private void deactivate() {
			this.removeClassName("select2-container-active");
		}
	}

	class DropDown
	    extends FloatPanel<DropDown> {

		private Search search  = new Search().hidden();

		private Items  results = new Items();

		public DropDown() {
			super(ElementResolver.div());
			this.init();
		}

		private void init() {
			this.className("select2-drop").add(this.search).add(this.results).hidden();
		}

		public DropDown onSelection(EventHandler<Entry> handler) {
			return this.addHandler(FireableEvent.SELECT, handler);
		}

		public DropDown activate() {
			return this.className("select2-drop-active");
		}

		public DropDown deactivate() {
			return this.removeClassName("select2-drop-active");
		}
		
		public DropDown open() {
			this.style().width(Selection.this.container.outerWidth(), Unit.PX);
			Selection.this.container.className("select2-dropdown-open").deactivate();
			
			return super.open();
		}

		public DropDown close() {
			Selection.this.container.removeClassName("select2-dropdown-open");
			
			return super.close();
		}
		
		void select(Entry value){
			this.fireEvent(new Event<Entry>(FireableEvent.SELECT, value));
			Selection.this.select(value);
			Selection.this.menu.close();
		}

		class Search
		    extends Component<Search> {

			private InputText input = new InputText().className("select2-input");

			public Search() {
				super(ElementResolver.div());
				this.className("select2-search").add(this.input);
			}
		}

		class Items
		    extends Component<Items> {

			public Items() {
				super(ElementResolver.ul());
				this.init();
			}

			private void init() {
				this.className("select2-results");
			}

			public Items item(String value, String text) {
				return this.item(Entry.create().key(value).value(text));
			}

			public Items item(Entry entry) {
				return this.add(new Item().value(entry));
			}

			Item child(int index) {
				return (Item)this.getChild(index);
			}
			
			Item child(String key){
				for(Widget child : this.getChildren()) {
					Item item = (Item) child;
					if(item.value.getKey().equals(key)){
						return item;
					}
				}
				
				return null;
			}

			class Item
			    extends Component<Item>
			    implements HasClickHandlers<Item>, HasMouseHandlers<Item>, HasValue<Item, Entry> {

				private HTML<DivElement> label = HTML.asDiv().className("select2-result-label");

				private Entry            value;

				public Item() {
					super(ElementResolver.li());
					this.init();
				}

				private void init() {
					this.className("select2-results-dept-0 select2-result select2-result-selectable");
					this.add(this.label);

					this.onClick(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							DropDown.this.select(Item.this.value);
						}
					}).onMouseOver(new MouseOverHandler() {

						@Override
						public void onMouseOver(MouseOverEvent event) {
							Item.this.className("select2-highlighted");
						}
					}).onMouseOut(new MouseOutHandler() {

						@Override
						public void onMouseOut(MouseOutEvent event) {
							Item.this.removeClassName("select2-highlighted");
						}
					});
				}

				@Override
				public Item onClick(ClickHandler handler) {
					return Events.on(this, handler);
				}

				@Override
				public Item onDoubleClick(DoubleClickHandler handler) {
					return Events.on(this, handler);
				}

				@Override
				public Item onMouseDown(MouseDownHandler handler) {
					return Events.on(this, handler);
				}

				@Override
				public Item onMouseMove(MouseMoveHandler handler) {
					return Events.on(this, handler);
				}

				@Override
				public Item onMouseOut(MouseOutHandler handler) {
					return Events.on(this, handler);
				}

				@Override
				public Item onMouseOver(MouseOverHandler handler) {
					return Events.on(this, handler);
				}

				@Override
				public Item onMouseUp(MouseUpHandler handler) {
					return Events.on(this, handler);
				}

				@Override
				public Item onMouseWheel(MouseWheelHandler handler) {
					return Events.on(this, handler);
				}

				@Override
				public Item value(Entry value) {
					this.value = value;

					if (value != null) {
						this.label.text(value.getValue());
						this.label.attribute("data-value", value.getKey());
					}

					return this;
				}

				@Override
				public Entry getValue() {
					return this.value;
				}
			}
		}
	}

	public interface Matcher {

	}

	enum FireableEvent
	    implements EventType {
		SELECT;
	}

	/*
	 * <div class="select2-container select2-dropdown-open select2-container-active"
	 * id="s2id_gwt-uid-2" style="width: 100%;">
	 * <a tabindex="-1" class="select2-choice" onclick="return false;" href="javascript:void(0)">
	 * <span>PlaceHolder</span>
	 * <abbr style="display:none;" class="select2-search-choice-close"></abbr>
	 * </a>
	 * <input type="text" class="select2-focusser select2-offscreen" disabled="disabled">
	 * </div>
	 * /*Options HTML
	 * <div style="top: 30px; left: 0px; width: 1920px; display: block;"
	 * class="select2-drop select2-drop-active" id="select2-drop">
	 * <div class="select2-search select2-search-hidden">
	 * <input type="text" class="select2-input" autocomplete="off">
	 * </div>
	 * <ul class="select2-results">
	 * <li class="select2-results-dept-0 select2-result select2-result-selectable">
	 * <div class="select2-result-label"><span class="select2-match"></span>Value 1</div>
	 * </li>
	 * <li class="select2-results-dept-0 select2-result select2-result-selectable">
	 * <div class="select2-result-label"><span class="select2-match"></span>Value 2</div>
	 * </li>
	 * <li
	 * class="select2-results-dept-0 select2-result select2-result-selectable select2-highlighted">
	 * <div class="select2-result-label"><span class="select2-match"></span>Value 3</div>
	 * </li>
	 * </ul>
	 * </div>
	 */
}