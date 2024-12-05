package de.bund.bva.isyfact.task.exception;

import java.io.Serial;

import static de.bund.bva.isyfact.util.text.MessageProvider.createMessage;

public class TaskRuntimeException extends RuntimeException {

    /**
     * Serial version UID.
     */
    @Serial
    private static final long serialVersionUID = -3L;

    public TaskRuntimeException(String ausnahmeId, String... parameter) {
        super(createMessage(ausnahmeId, parameter));
    }
}
