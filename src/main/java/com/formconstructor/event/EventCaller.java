package com.formconstructor.event;

import cn.nukkit.Server;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;

/**
 * Represents a class for calling an event and processing its result.
 */
public interface EventCaller {

    /**
     * Calls an event and returns the result.
     *
     * @return True if event is cancelled, otherwise false
     * @throws UnsupportedOperationException If EventCaller is not applied to cn.nukkit.event.Event
     */
    default boolean callEvent() {
        if (this instanceof Event event) {
            Server.getInstance().getPluginManager().callEvent(event);
            return event instanceof Cancellable && !event.isCancelled();
        } else {
            throw new UnsupportedOperationException("EventCaller applied not on cn.nukkit.event.Event class");
        }
    }
}
