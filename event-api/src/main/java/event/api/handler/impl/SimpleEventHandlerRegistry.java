package event.api.handler.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import event.api.handler.EventHandler;
import event.api.handler.EventHandlerRegistry;

public class SimpleEventHandlerRegistry implements EventHandlerRegistry {

	private final Map<String, Set<EventHandler>> eventHandlerMap;
	
	public SimpleEventHandlerRegistry() {
		this.eventHandlerMap = new ConcurrentHashMap<>();
	}
	
	@Override
	public void registerEventHandler(String eventType, EventHandler eventHandler) {
		Objects.requireNonNull(eventType);
		Objects.requireNonNull(eventHandler);
		if(eventHandlerMap.containsKey(eventType)) {
			eventHandlerMap.get(eventType).add(eventHandler);
		} else {
			Set<EventHandler> eventHandlers = new HashSet<>();
			eventHandlers.add(eventHandler);
			eventHandlerMap.put(eventType, eventHandlers);
		}
	}

	@Override
	public boolean removeEventHandler(String eventType, EventHandler eventHandler) {
		Objects.requireNonNull(eventType);
		Objects.requireNonNull(eventHandler);
		Optional<Set<EventHandler>> eventHandlerSetOpt = Optional.ofNullable(eventHandlerMap.get(eventType));
		if(eventHandlerSetOpt.isPresent()) {
			return eventHandlerSetOpt.get().remove(eventHandler);
		}
		return false;
	}

	@Override
	public Set<EventHandler> getEventHandlers(String eventType) {
		return Optional.ofNullable(eventHandlerMap.get(eventType)).map(Collections::unmodifiableSet).orElse(Collections.emptySet());
	}

}
