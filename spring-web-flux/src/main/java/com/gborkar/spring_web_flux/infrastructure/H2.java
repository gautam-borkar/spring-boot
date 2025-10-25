package com.gborkar.spring_web_flux.infrastructure;

import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class H2 {

    private Server webServer;
    
    private Server tcpServer;

    @EventListener(org.springframework.boot.context.event.ApplicationStartedEvent.class)
    public void start() throws java.sql.SQLException {
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8082", "-tcpAllowOthers", 
                "-ifNotExists").start();
        this.tcpServer = org.h2.tools.Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", 
                "-ifNotExists").start();
    }

    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void stop() {
        this.tcpServer.stop();
        this.webServer.stop();
    }

}