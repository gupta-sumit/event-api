package event.api.handler;

import java.util.Set;

public interface EventHandlerRegistry {

	public Set<EventHandler> getEventHandlers(String eventType);
	
	public void registerEventHandler(String eventType, EventHandler eventHandler);
	
	public boolean removeEventHandler(String eventType, EventHandler eventHandler);
}

