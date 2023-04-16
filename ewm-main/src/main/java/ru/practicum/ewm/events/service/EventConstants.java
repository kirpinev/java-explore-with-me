package ru.practicum.ewm.events.service;

public final class EventConstants {

    private EventConstants() {
    }

    public static final String EVENT_NOT_FOUND_MESSAGE = "Event with id=%s was not found";
    public static final String OPERATION_EXCEPTION_MESSAGE = "Field: eventDate. Error: должно содержать дату, " +
            "которая еще не наступила. Value: %s";
    public static final String EVENT_UPDATE_PUBLISHED_MESSAGE = "Cannot update the event because " +
            "it's not in the right state: %s";
    public static final String EVENT_STATE_EXCEPTION_MESSAGE = "Cannot publish the event because " +
            "it's not in the right state: %s";
    public static final String EVENT_CANCEL_EXCEPTION_MESSAGE = "Cannot cancel the event because " +
            "it's not in the right state: %s";
}
