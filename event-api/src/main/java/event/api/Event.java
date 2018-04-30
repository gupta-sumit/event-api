package event.api;

import java.time.LocalDate;
import java.util.UUID;

public final class Event<T> {

	private final String id;
	private final String type;
	private final LocalDate createDate;
	private final T payload;
	
	public Event(String type, T payload) {
		super();
		this.id = UUID.randomUUID().toString();
		this.type = type;
		this.payload = payload;
		this.createDate = LocalDate.now();
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public T getPayload() {
		return payload;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", createDate=" + createDate + ", payload=" + payload + "]";
	}
	
	
}
