package fr.notfound.http.server;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.*;

public class Jetty {
    
    public static interface WriterHandler {
        void handle(Writer responseWriter) throws IOException;
    }
    
    public static interface StringHandler {
        String handle();
    }

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
            @Override public void handle(
                String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) 
                throws IOException, ServletException {
                try (Writer writer = response.getWriter()) {
                    writer.write(responseContent);
                    writer.flush();
                }
            }
        });
        handlers.addHandler(newHandler);
        return this;
    }

    public Jetty handle(URI path, final String responseContent) {
        return handle(path.toString(), responseContent);
    }

    public Jetty handle(String path, final Runnable sideEffect) {
        ContextHandler newHandler = new ContextHandler(path);
        newHandler.setHandler(new AbstractHandler() {
            @Override public void handle(
                String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) 
                throws IOException, ServletException {
                sideEffect.run();
            }
        });
        handlers.addHandler(newHandler);
        return this;
    }
    
    public Jetty handle(String path, final WriterHandler writerHandler) {
        ContextHandler newHandler = new ContextHandler(path);
        newHandler.setHandler(new AbstractHandler() {
            @Override public void handle(
                String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) 
                throws IOException, ServletException {
                try (Writer responseWriter = response.getWriter()) {
                    writerHandler.handle(responseWriter);
                    responseWriter.flush();
                }
            }
        });
        handlers.addHandler(newHandler);
        return this;
    }
    
    public Jetty handle(URI path, final WriterHandler writerHandler) {
        return handle(path.toString(), writerHandler);
    }
    
    public Jetty handle(URI path, final Iterable<String> responses) {
        ContextHandler newHandler = new ContextHandler(path.toString());
        newHandler.setHandler(new AbstractHandler() {
            private final Iterator<String> responseIterator = responses.iterator();
            @Override public void handle(
                String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) 
                throws IOException, ServletException {
                try (Writer writer = response.getWriter()) {
                    writer.write(responseIterator.next());
                    writer.flush();
                }
            }
        });
        handlers.addHandler(newHandler);
        return this;
    }
    
    public Jetty handle(URI path, final StringHandler handler) {
        ContextHandler newHandler = new ContextHandler(path.toString());
        newHandler.setHandler(new AbstractHandler() {
            @Override public void handle(
                String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) 
                throws IOException, ServletException {
                try (Writer writer = response.getWriter()) {
                    writer.write(handler.handle());
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
