package org.quark.events;


import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class FileCheckBean {

    private static final Logger LOGGER = LoggerFactory.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("File check is starting : ");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("File check is finished as  : ");
    }
}
