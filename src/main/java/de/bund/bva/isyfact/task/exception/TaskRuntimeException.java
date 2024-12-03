package de.bund.bva.isyfact.task.exception;

import de.bund.bva.isyfact.util.text.MessageProvider;

import java.io.Serial;

public class TaskRuntimeException extends RuntimeException {

    /**
     * Serial version UID.
     */
    @Serial
    private static final long serialVersionUID = -3L;

    public TaskRuntimeException(String ausnahmeId, String... parameter) {
        super(MessageProvider.getMessage(ausnahmeId, parameter));
    }
}
