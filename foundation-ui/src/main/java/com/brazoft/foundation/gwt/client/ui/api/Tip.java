package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.Direction;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

@SuppressWarnings("unchecked")
public abstract class Tip<T extends Tip<T>>
    extends Component<T>
    implements HasMouseHandlers<T> {

	private Selector<?>  holder;

	private Direction    direction;

	private DisplayState display = DisplayState.STATIC;

	public Tip() {
		super(ElementResolver.div());
	}

	public T animate() {
		this.display = DisplayState.ANIMATE;
		this.className("fade");
		return (T)this;
	}

	@Override
	public T onMouseDown(MouseDownHandler handler) {
		return Events.on((T)this, handler);
	}

	@Override
	public T onMouseMove(MouseMoveHandler handler) {
		return Events.on((T)this, handler);
	}

	@Override
	public T onMouseOut(MouseOutHandler handler) {
		return Events.on((T)this, handler);
	}

	@Override
	public T onMouseOver(MouseOverHandler handler) {
		return Events.on((T)this, handler);
	}

	@Override
	public T onMouseUp(MouseUpHandler handler) {
		return Events.on((T)this, handler);
	}

	@Override
	public T onMouseWheel(MouseWheelHandler handler) {
		return Events.on((T)this, handler);
	}

	public T apply(Selector<?> holder) {
		this.holder = holder;
		return (T)this;
	}

	public T show() {
		if (!this.isAttached()) {
			RootPanel.get().add(this);
		}
		this.positioning();
		this.display.show((T)this);

		return (T)this;
	}

	public T hide() {
		this.display.hide((T)this);
		return (T)this;
	}

	public T toggle() {
		if (this.display.isVisible((T)this)) {
			return this.hide();
		}

		return this.show();
	}

	public T placement(Direction direction) {
		this.direction = direction;
		this.className(direction.name().toLowerCase());

		return (T)this;
	}

	public T trigger(Trigger... triggers) {
		for (Trigger trigger : triggers) {
			trigger.register(this.holder, (T)this);
		}

		return (T)this;
	}

	protected void positioning() {
		int top = 0;
		int left = 0;
		int actualWidth = this.outerWidth();
		int actualHeight = this.outerHeight();

		Offset pos = this.holder.offset();
		int height = this.holder.getOffsetHeight();
		int width = this.holder.getOffsetWidth();

		switch (this.direction) {
			case BOTTOM:
				top = pos.top() + height;
				left = pos.left() + width / 2 - actualWidth / 2;
				break;
			case TOP:
				top = pos.top() - actualHeight;
				left = pos.left() + width / 2 - actualWidth / 2;
				break;
			case LEFT:
				top = pos.top() + height / 2 - actualHeight / 2;
				left = pos.left() - actualWidth;
				break;
			case RIGHT:
				top = pos.top() + height / 2 - actualHeight / 2;
				left = pos.left() + width;
				break;
		}
		this.style().top(top, Unit.PX).left(left, Unit.PX).display(Display.BLOCK);
	}

	enum DisplayState {
		ANIMATE {

			@Override
			public <T extends Tip<T>> void show(T tip) {
				tip.removeClassName("out").className("in");
			}

			public <T extends Tip<T>> void hide(T tip) {
				tip.removeClassName("in").className("out").hidden();
			}

			public <T extends Tip<T>> boolean isVisible(T tip) {
				return tip.getStyleName().contains("in");
			}
		},
		STATIC {

			@Override
			public <T extends Tip<T>> void show(T tip) {
				tip.visible();
			}

			public <T extends Tip<T>> void hide(T tip) {
				tip.hidden();
			}

			public <T extends Tip<T>> boolean isVisible(T tip) {
				return tip.isVisible();
			};
		};

		public abstract <T extends Tip<T>> void show(T tip);

		public abstract <T extends Tip<T>> void hide(T tip);

		public abstract <T extends Tip<T>> boolean isVisible(T tip);

	}

	public enum Trigger {
		CLICK {

			@Override
			<T extends Tip<T>> void register(Widget holder, final T tip) {
				Events.on(holder, new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						tip.toggle();
					}
				});
			}
		},
		HOVER {

			@Override
			<T extends Tip<T>> void register(Widget holder, final T tip) {
				Events.on(holder, new MouseOverHandler() {

					@Override
					public void onMouseOver(MouseOverEvent event) {
						tip.show();
					}
				});

				// Events.on(holder, new MouseMoveHandler() {
				//
				// @Override
				// public void onMouseMove(MouseMoveEvent event) {
				// tip.show();
				// }
				// });

				Events.on(holder, new MouseOutHandler() {

					@Override
					public void onMouseOut(MouseOutEvent event) {
						tip.hide();
					}
				});
			}
		},
		FOCUS {

			@Override
			<T extends Tip<T>> void register(Widget holder, final T tip) {
				Events.on(holder, new FocusHandler() {

					@Override
					public void onFocus(FocusEvent event) {
						tip.show();
					}
				});

				Events.on(holder, new BlurHandler() {

					@Override
					public void onBlur(BlurEvent event) {
						tip.hide();
					}
				});
			}
		},
		MANUAL {

			@Override
			<T extends Tip<T>> void register(Widget holder, final T tip) {
				return;
			}
		};

		abstract <T extends Tip<T>> void register(Widget holder, T tip);
	}
}