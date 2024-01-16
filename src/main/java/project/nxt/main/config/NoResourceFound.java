package project.nxt.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class NoResourceFound {
    @Autowired
    ResourceLoader resourceLoader;
    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<Object> NoResourceFoundException(Exception ex) {
        //System.out.println(ex.getMessage());
        Resource resource =resourceLoader.getResource("classpath:static/index.html");
        return ResponseEntity.ok().body(resource);
    }
}
