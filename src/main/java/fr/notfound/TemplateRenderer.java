package fr.notfound;

import java.io.*;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class TemplateRenderer {
    
    private final VelocityEngine delegate;
    
    public TemplateRenderer(VelocityEngine delegate) {
        this.delegate = delegate;
    }
    
    public String render(String template, Map<?, ?> context) {
        try (Writer writer = new StringWriter()) {
            delegate.evaluate(
                new VelocityContext(context),
                writer, 
                TemplateRenderer.class.getSimpleName(), 
                template);
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
