package ru.practicum.ewm.requests.service;

public final class RequestConstants {

    private RequestConstants() {
    }

    public static final String REQUEST_SAME_USER_ID_EXCEPTION_MESSAGE =
            "Cannot create request for own event.";
    public static final String REQUEST_STATE_EXCEPTION_MESSAGE =
            "Cannot create request for unpublished event.";
    public static final String REQUEST_LIMIT_EXCEPTION_MESSAGE =
            "Cannot create request because of participant limitation.";
    public static final String REQUEST_NOT_FOUND_EXCEPTION_MESSAGE =
            "Request with id=%s was not found";
}
