package de.bund.bva.isyfact.task.demo;

import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.KATEGORIE_SICHERHEIT;
import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.createKategorieMarker;

@Component
public class ScheduledTasks {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Timed(value = "task.scheduledTasks.scheduleTaskWithFixedRate")
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        LOG.info(createKategorieMarker(KATEGORIE_SICHERHEIT), "EISYTA99991", "Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    @Timed(value = "task.scheduledTasks.scheduleTaskWithFixedDelay")
    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        LOG.info(createKategorieMarker(KATEGORIE_SICHERHEIT), "EISYTA99992", "Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            LOG.error("ISYTA99999", "Ran into an error!", ex);
            throw new IllegalStateException(ex);
        }
    }

    @Timed(value = "task.scheduledTasks.scheduleTaskWithInitialDelay")
    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        LOG.info(createKategorieMarker(KATEGORIE_SICHERHEIT), "EISYTA99993", "Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        if (new Random().nextInt(10) == 0) {
            throw new RuntimeException();
        }
    }

    @Timed(value = "task.scheduledTasks.scheduleTaskWithCronExpression")
    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        LOG.info(createKategorieMarker(KATEGORIE_SICHERHEIT), "EISYTA99994", dateTimeFormatter.format(LocalDateTime.now()));
    }
}