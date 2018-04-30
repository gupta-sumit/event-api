package event.api.dispatcher.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import event.api.dispatcher.EventDispatcher;
import event.api.handler.EventHandlerRegistry;

public class EventDispatchers {
	
	
	public static EventDispatcher syncDispatcher(EventHandlerRegistry eventHandlerRegistry) {
		return (e) -> Optional.ofNullable(eventHandlerRegistry.getEventHandlers(e.getType()))
						.ifPresent(c -> c.forEach(eh -> eh.handleEvent(e)));
	}
	
	public static EventDispatcher asyncDispatcher(EventHandlerRegistry eventHandlerRegistry, Consumer<Throwable> exceptionConsumer) {
		return (e) -> Optional.ofNullable(eventHandlerRegistry.getEventHandlers(e.getType()))
						.ifPresent(c -> c.forEach(eh -> {
							CompletableFuture.runAsync(() -> eh.handleEvent(e)).exceptionally(t-> {
								exceptionConsumer.accept(t);
								return null;
							});
						}));
	}
	
	
	
	
}
