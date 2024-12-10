package de.bund.bva.isyfact.task;

import de.bund.bva.isyfact.task.annotation.OnceTask;
import de.bund.bva.isyfact.task.demo.ScheduledTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.KATEGORIE_JOURNAL;
import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.createKategorieMarker;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class ProgrammaticallyScheduledTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    @OnceTask
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                MILLISECONDS.sleep(100);
                LOG.info(createKategorieMarker(KATEGORIE_JOURNAL), "EISYTA99994", "Manual Task {} :: Execution Time - {}", i, dateTimeFormatter.format(LocalDateTime.now()));
            } catch (InterruptedException e) {
                LOG.debug("Thread unterbrochen");
                return;
            }
        }
    }
}
