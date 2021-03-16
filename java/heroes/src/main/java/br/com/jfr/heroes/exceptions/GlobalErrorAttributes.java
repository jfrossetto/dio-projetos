package br.com.jfr.heroes.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private final MessageSource messageSource;

    @Autowired
    public GlobalErrorAttributes(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Map<String, Object> getErrorAttributes(final ServerRequest request, final ErrorAttributeOptions options) {

        final Map<String, Object> errorAttributesMap = super.getErrorAttributes(request, options);
        final Throwable throwable = getError(request);

        if (throwable instanceof WebExchangeBindException) {
            final WebExchangeBindException bindException = (WebExchangeBindException) throwable;
            errorAttributesMap.put("erros", criarListaDeErros(bindException.getBindingResult()));
            return errorAttributesMap;
        }

        if (throwable instanceof ResponseStatusException) {
            final ResponseStatusException ex = (ResponseStatusException) throwable;
            errorAttributesMap.put("mensagem", ex.getReason());
            errorAttributesMap.put("causa", ex.getCause());
            return errorAttributesMap;
        }

        errorAttributesMap.put("mensagem", throwable.getMessage());
        errorAttributesMap.put("mensagemDetalhada", throwable.getCause());

        return errorAttributesMap;
    }

    private List<Map<String, Object>> criarListaDeErros(final BindingResult bindingResult) {
        final List<Map<String, Object>> erros = new ArrayList<>();

        for (final FieldError fieldError : bindingResult.getFieldErrors()) {
            final Map<String, Object> erro = new HashMap<>();
            erro.put("mensagem", messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
            erro.put("mensagemDetalhada", fieldError.toString());
            erros.add(erro);
        }
        return erros;
    }

}
