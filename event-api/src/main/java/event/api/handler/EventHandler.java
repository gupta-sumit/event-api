package event.api.handler;

import event.api.Event;

public interface EventHandler {

	public void handleEvent(Event<?> event);
}
