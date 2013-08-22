package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.*;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.brazoft.foundation.gwt.client.util.Entry;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class TypeAhead<T extends TypeAhead<T, V>, V>
    extends DecoratedInput<T, V> {

	private Menu            menu             = new Menu();

	private JsArray<Entry>  entries;

	private Entry           selection;

	private int             numberOfItems    = 10;

	private int             minLength        = 3;

	private ContentProvider provider;

	private SelectionTimer  selectionCommand = new SelectionTimer();

	public TypeAhead() {
		super(new InputText());
		this.init();
	}

	private void init() {
		this.style().position(Position.RELATIVE);
		this.add(this.menu.hidden());

		this.input().onKeyUp(new Handler() {

			@Override
			void onKeyUp(int keyCode, char charCode) {
				String value = TypeAhead.this.input().getValue();
				if (value.length() >= TypeAhead.this.minLength) {
					TypeAhead.this.load(value);
				}

				if (value.length() < TypeAhead.this.minLength  ||  TypeAhead.this.entries == null  ||  TypeAhead.this.entries.length() == 0) {
					TypeAhead.this.menu.close();
					TypeAhead.this.entries = null;
				}
			}
		});

		this.input().onBlur(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				if (TypeAhead.this.menu.isOpened() && !TypeAhead.this.menu.isHover()) {
					TypeAhead.this.menu.close();
				}
			}
		});
	}

	private void load(String value) {
		this.entries = this.provider.provideContent(value, this.numberOfItems);
		this.menu.detachChildren();

		for (int idx = 0; idx < entries.length(); idx++) {
			Entry entry = this.entries.get(idx);
			this.menu.item(entry);
		}

		this.menu.open();
		this.selectionCommand.schedule(10);
	}

	public T onShow(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.SHOW, handler);
	}

	public T onMoveUp(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.UP, handler);
	}

	public T onMoveDown(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.DOWN, handler);
	}

	public T onSelect(EventHandler<Entry> handler) {
		this.addHandler(FireableEvent.SELECTION, handler);
		return (T)this;
	}

	public T provider(ContentProvider provider) {
		this.provider = provider;
		return (T)this;
	}

	@Override
	public InputText input() {
		return (InputText)super.input();
	}

	public T block() {
		this.input().block();
		return (T)this;
	}

	public T visibleItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
		return (T)this;
	}

	public T triggerAutoComplete(int minLength) {
		this.minLength = minLength;
		return (T)this;
	}

	public T activate(int index) {
		this.menu.item(index).activate();
		return (T)this;
	}

	public T deactivate(int index) {
		this.menu.item(index).deactivate();
		return (T)this;
	}

	public int getActiveIndex() {
		return this.menu.activeItem();
	}
	
	public T select(int index) {
		return this.select(index, true);
	}

	public T select(int index, boolean fireEvent) {
		this.input().blur();
		return this.select(this.entries.get(index), fireEvent);
	}
	
	public T select(Entry entry) {
		return this.select(entry, true);
	}

	public T select(Entry entry, boolean fireEvent) {
		this.selection = entry;
		this.input().value(entry.getValue());
		
		if(fireEvent) {
			this.fireEvent(new Event<Entry>(FireableEvent.SELECTION, entry));
		}
		this.menu.close();

		return (T)this;
	}
	
	public boolean hasSuggestions() {
		return this.menu.isOpened();
	}

	public Entry getSelection() {
		return this.selection;
	}

	public int size() {
		int size = 0;
		if (this.entries != null) {
			size = this.entries.length();
		}

		return size;
	}

	public static interface ContentProvider {

		JsArray<Entry> provideContent(String value, int numberOfItems);
	}

	enum FireableEvent
	    implements EventType {
		COMPLETION, DOWN, SELECTION, SHOW, UP;
	}

	class Menu
	    extends FloatPanel<Menu>
	    implements HasMouseHandlers<Menu>, HasKeyHandlers<Menu> {

		public Menu() {
			super(ElementResolver.ul());
			this.init();
		}

		private void init() {
			this.className("typeahead dropdown-menu").target(TypeAhead.this.input());
		}

		void up() {
			this.moveTo(-1);
			TypeAhead.this.fireEvent(FireableEvent.UP);
		}

		void down() {
			this.moveTo(1);
			TypeAhead.this.fireEvent(FireableEvent.DOWN);
		}

		void moveTo(int direction) {
			if (!this.hasChildren() || this.isHover()) {
				return;
			}

			int idx = direction > 0 ? 0 : this.childrenCount() - 1;
			MenuItem target = this.item(idx);
			boolean bound = false;

			do {
				MenuItem item = this.item(idx);
				idx += direction;
				bound = idx >= 0 && idx < this.childrenCount();

				if (item.isActive()) {
					item.blur();
					if (bound) {
						this.item(idx).activate();
						return;
					}
					break;
				}
			} while (bound);

			target.activate();
		}

		MenuItem item(int index) {
			return (MenuItem)this.getChild(index);
		}

		int activeItem() {
			int idx = 0;
			for (Widget child : this.getChildren()) {
				MenuItem item = (MenuItem)child;
				if (item.isActive()) {
					return idx;
				}
				idx++;
			}

			return -1;
		}

		@Override
		public Menu onKeyPress(KeyPressHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Menu onKeyDown(KeyDownHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Menu onKeyUp(KeyUpHandler handler) {
			return Events.on(this, handler);
		}

		public Menu reset() {
			int index = this.activeItem();
			if(index > -1) {
				this.item(index).deactivate();
			}
			
			return this;
		}

		public MenuItem item(final Entry entry) {
			final MenuItem item = new MenuItem().text(entry.getValue()).value(entry.getKey());

			item.onClick(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					TypeAhead.this.select(entry);
				}
			}).onBlur(new BlurHandler() {

				@Override
				public void onBlur(BlurEvent event) {
					item.link.removeAttribute("focused");
				}
			}).onKeyUp(new Handler());

			this.add(item);
			return item;
		}

		class MenuItem
		    extends Component<MenuItem>
		    implements HasText<MenuItem>, HasValue<MenuItem, String>, HasClickHandlers<MenuItem>, HasMouseHandlers<MenuItem>,
		    HasKeyHandlers<MenuItem>, HasFocusHandlers<MenuItem>, HasTouchHandlers<MenuItem> {

			private HTML<AnchorElement> link = HTML.asAnchor();

			public MenuItem() {
				super(ElementResolver.li());
				this.init();
			}

			private void init() {
				this.add(this.link);

				this.link.onMouseOut(new MouseOutHandler() {

					@Override
					public void onMouseOut(MouseOutEvent event) {
						MenuItem.this.deactivate();
					}
				});

				this.link.onMouseOver(new MouseOverHandler() {

					@Override
					public void onMouseOver(MouseOverEvent event) {
						Menu.this.reset();
						MenuItem.this.activate();
					}
				});
			}

			public MenuItem activate() {
				this.className("active");
				return this;
			}

			public MenuItem deactivate() {
				this.removeClassName("active");
				return this;
			}

			public MenuItem focus() {
				this.activate();
				this.link.getElement().focus();
				return this;
			}

			public MenuItem blur() {
				this.deactivate();
				this.link.getElement().blur();
				return this;
			}

			public boolean isActive() {
				return "true".equals(this.link.getAttribute("focused")) || this.getStyleName().contains("active");
			}

			@Override
			public MenuItem onTouchCancel(TouchCancelHandler handler) {
				this.link.onTouchCancel(handler);
				return this;
			}

			@Override
			public MenuItem onTouchEnd(TouchEndHandler handler) {
				this.link.onTouchEnd(handler);
				return this;
			}

			@Override
			public MenuItem onTouchMove(TouchMoveHandler handler) {
				this.link.onTouchMove(handler);
				return this;
			}

			@Override
			public MenuItem onTouchStart(TouchStartHandler handler) {
				this.link.onTouchStart(handler);
				return this;
			}

			@Override
			public MenuItem onFocus(FocusHandler handler) {
				this.link.onFocus(handler);
				return this;
			}

			@Override
			public MenuItem onBlur(BlurHandler handler) {
				this.link.onBlur(handler);
				return this;
			}

			@Override
			public MenuItem onKeyPress(KeyPressHandler handler) {
				this.link.onKeyPress(handler);
				return this;
			}

			@Override
			public MenuItem onKeyDown(KeyDownHandler handler) {
				this.link.onKeyDown(handler);
				return this;
			}

			@Override
			public MenuItem onKeyUp(KeyUpHandler handler) {
				this.link.onKeyUp(handler);
				return this;
			}

			@Override
			public MenuItem onMouseDown(MouseDownHandler handler) {
				this.link.onMouseDown(handler);
				return this;
			}

			@Override
			public MenuItem onMouseMove(MouseMoveHandler handler) {
				this.link.onMouseMove(handler);
				return this;
			}

			@Override
			public MenuItem onMouseOut(MouseOutHandler handler) {
				this.link.onMouseOut(handler);
				return this;
			}

			@Override
			public MenuItem onMouseOver(MouseOverHandler handler) {
				this.link.onMouseOver(handler);
				return this;
			}

			@Override
			public MenuItem onMouseUp(MouseUpHandler handler) {
				this.link.onMouseUp(handler);
				return this;
			}

			@Override
			public MenuItem onMouseWheel(MouseWheelHandler handler) {
				this.link.onMouseWheel(handler);
				return this;
			}

			@Override
			public MenuItem onClick(ClickHandler handler) {
				this.link.onClick(handler);
				return this;
			}

			@Override
			public MenuItem onDoubleClick(DoubleClickHandler handler) {
				this.link.onDoubleClick(handler);
				return this;
			}

			@Override
			public String getValue() {
				return this.getAttribute("data-value");
			}

			@Override
			public MenuItem value(String value) {
				return this.attribute("data-value", value);
			}

			@Override
			public MenuItem text(String text) {
				this.link.text(text);
				return this;
			}

			@Override
			public String getText() {
				return this.link.getText();
			}
		}
	}

	class SelectionTimer
	    extends Timer {

		@Override
		public void run() {
			TypeAhead.this.fireEvent(FireableEvent.SHOW);
		}
	}

	class Handler
	    implements KeyUpHandler {

		@Override
		public final void onKeyUp(KeyUpEvent event) {
			int keyCode = event.getNativeEvent().getKeyCode();

			if (KeyCodes.KEY_ESCAPE == keyCode) {
				TypeAhead.this.menu.close();
				return;
			}

			if (KeyCodes.KEY_UP == keyCode) {
				TypeAhead.this.menu.up();
				return;
			}

			if (KeyCodes.KEY_DOWN == keyCode) {
				TypeAhead.this.menu.down();
				return;
			}

			if (Widgets.controlKeys().contains(keyCode) && keyCode != KeyCodes.KEY_BACKSPACE) {
				return;
			}

			this.onKeyUp(keyCode, (char)event.getNativeEvent().getCharCode());
		}

		void onKeyUp(int keyCode, char charCode) {}
	}
}
