package br.com.zup.casadocodigo.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FormResponse handle(MethodArgumentNotValidException exception) {
        List<String> response = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            response.add(mensagem);
        });

        return new FormResponse(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public FormResponse handle(ResponseStatusException exception) {
        return new FormResponse(Arrays.asList(exception.getMessage()));
    }
}
