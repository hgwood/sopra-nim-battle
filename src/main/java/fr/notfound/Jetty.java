package fr.notfound;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.*;

public class Jetty {

    public static Jetty onPort(int port) {
        return new Jetty(new Server(port), new ContextHandlerCollection());
    }

    private final Server built;
    private final HandlerCollection handlers;

    public Jetty(Server built, HandlerCollection handlers) {
        this.built = built;
        this.handlers = handlers;
    }

    public Jetty handle(String path, final String responseContent) {
        ContextHandler newHandler = new ContextHandler(path);
        newHandler.setHandler(new AbstractHandler() {
            @Override public void handle(String target, Request baseRequest, HttpServletRequest request,
                HttpServletResponse response) throws IOException, ServletException {
                try (Writer writer = response.getWriter()) {
                    writer.write(responseContent);
                    writer.flush();
                }
            }
        });
        handlers.addHandler(newHandler);
        return this;
    }
    
    public Jetty start() {
        built.setHandler(handlers);
        try {
            built.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    
    public Jetty stop() {
        try {
            built.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

}
