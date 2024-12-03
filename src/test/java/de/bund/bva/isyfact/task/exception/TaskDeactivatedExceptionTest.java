package de.bund.bva.isyfact.task.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskDeactivatedExceptionTest {

    @Test
    void getMessage() {
        String taskId = "XYZ";
        TaskDeactivatedException exception = new TaskDeactivatedException(taskId);
        String message = exception.getMessage();
        assertTrue(message.contains(taskId));

        exception = new TaskDeactivatedException(taskId, new NullPointerException());
        message = exception.getMessage();
        assertTrue(message.contains(taskId));
    }

    @Test
    void getLocalizedMessage() {
        String taskId = "XYZ";
        TaskDeactivatedException exception = new TaskDeactivatedException(taskId);
        String message = exception.getLocalizedMessage();
        assertTrue(message.contains(taskId));
    }
}