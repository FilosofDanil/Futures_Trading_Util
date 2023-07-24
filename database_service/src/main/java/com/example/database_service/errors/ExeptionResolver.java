package com.example.database_service.errors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Component
public class ExeptionResolver extends AbstractHandlerExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        final ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        logger.error(ex.getMessage(), ex);
        if (ex instanceof IllegalArgumentException) {
            ex.printStackTrace();
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            modelAndView.addObject("message", "Invalid data, or these resource is already exist!");
            return modelAndView;
        }
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("message", "Something went wrong on the server!");
        return modelAndView;
    }
}
