package uib.teamdank.common.util;

import java.util.Objects;

/**
 * A timed event notifies a listener every set number of seconds. It must be
 * updated with the time by calling {@link #update(float)}.
 */
public class TimedEvent {

	private final float frequencySeconds;
	private final Runnable action;

	private boolean loop;
	private float currentTime;

	public TimedEvent(float frequencyInSeconds, boolean loop, Runnable action) {
		if (frequencyInSeconds <= 0) {
			throw new IllegalArgumentException("frequency must be over zero");
		}
		Objects.requireNonNull(action, "action cannot be null");
		this.frequencySeconds = frequencyInSeconds;
		this.action = action;
		setLoop(loop);
	}

	public TimedEvent(float frequencyInSeconds, Runnable action) {
		this(frequencyInSeconds, false, action);
	}

	public boolean isDone() {
		return (!loop && currentTime > frequencySeconds);
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public void update(float delta) {
		if (!isDone()) {
			this.currentTime += delta;
			if (currentTime >= frequencySeconds) {
				action.run();
				currentTime -= frequencySeconds;
			}
		}
	}

}
