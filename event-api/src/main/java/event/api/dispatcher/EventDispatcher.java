package event.api.dispatcher;

import event.api.Event;

@FunctionalInterface
public interface EventDispatcher {

	public void dispatchEvent(Event<?> event);

}
