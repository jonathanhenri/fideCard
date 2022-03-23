package com.fidecard.application.config;

import com.fidecard.application.ErroDto;
import com.fidecard.application.utils.exceptions.ConflictException;
import com.fidecard.application.utils.exceptions.CreateException;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.application.utils.exceptions.UnprocessableException;
import org.joda.time.IllegalFieldValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class ErrorControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorControllerAdvice.class);

//    private final MessageBuilder messageBuilder;

//    @Autowired
//    public ErrorControllerAdvice(MessageBuilder messageBuilder) {
//        this.messageBuilder = messageBuilder;
//    }
    
    @Autowired
    public ErrorControllerAdvice() {
    }
    
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto serviceException(HttpServletRequest req, RuntimeException ex) {
        long timestamp = System.currentTimeMillis();
        String mensagem = String.format("Falha no processamento da requisição, entre em contato com a " +
                "equipe do suporte (timestamp: %d). %s", timestamp, ex.getMessage());
        LOG.error(mensagem, ex);
        return new ErroDto(HttpStatus.BAD_REQUEST.value(), mensagem);
    }


    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErroDto disabledException(HttpServletRequest req, DisabledException ex) {
        String mensagem = "Aplicativo bloqueado. Entre em contato com a equipe do suporte.";
        LOG.warn(mensagem);
        return new ErroDto(HttpStatus.UNAUTHORIZED.value(), mensagem);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErroDto badCredentialsException(HttpServletRequest req, BadCredentialsException ex) {
        String mensagem = "Credenciais invalidas";
        LOG.warn(mensagem);
        return new ErroDto(HttpStatus.UNAUTHORIZED.value(), mensagem);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroDto entidadeNaoEncontrada(HttpServletRequest req, EntityNotFoundException ex) {
        return new ErroDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto methodArgumentTypeMismatch(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {
        Throwable throwable = ex.getMostSpecificCause();
        String message = throwable.getMessage();
        if (throwable instanceof IllegalFieldValueException) {
            message = message.replace("Cannot parse", "Falha ao parsear").replace("Value", "O valor")
                    .replace("for", "para").replace("monthOfYear", "o mês do ano")
                    .replace("dayOfMonth", "o dia do mês").replace("must be in the range", "deve estar no intervalo");
            return new ErroDto(HttpStatus.BAD_REQUEST.value(), message);
        } else if (throwable instanceof IllegalArgumentException) {
            message = message.replace("Invalid format", "Formato inválido")
                    .replace("is malformed at", "está mal-formado em");
            return new ErroDto(HttpStatus.BAD_REQUEST.value(), message);
        } else if (throwable instanceof DateTimeException) {
            message = message.replace("Invalid value", "Valor invalido").replace("for", "para")
                    .replace("MonthOfYear", "o mês do ano").replace("DayOfMonth", "o dia do mês")
                    .replace("valid values", "valores validos").replace("Text", "Texto")
                    .replace("could not be", "nao pode ser").replace("parsed at", "convertido no");
            return new ErroDto(HttpStatus.BAD_REQUEST.value(), message);
        }

        return falhaInesperada(req, ex);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErroDto falhaInesperada(HttpServletRequest req, RuntimeException ex) {
        long timestamp = System.currentTimeMillis();
        String mensagem = String.format("Falha inesperada no processamento da requisição, entre em contato com a " +
                "equipe do suporte (timestamp: %d).", timestamp);
        LOG.error(mensagem, ex);
        return new ErroDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), mensagem);
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto multiPartBadRequest(HttpServletRequest req, MultipartException ex) {
        long timestamp = System.currentTimeMillis();
        String mensagem = String.format("A requisicao recebida nao e uma 'Multipart Request' verifique o valor do " +
                "header Content-Type (timestamp: %d).", timestamp);
        LOG.error(mensagem, ex);
        return new ErroDto(HttpStatus.BAD_REQUEST.value(), mensagem);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto parametrosInvalidosParaRequisicao(HttpServletRequest req, IllegalArgumentException ex) {
        return new ErroDto(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto badRequest(HttpServletRequest req, ServletRequestBindingException ex) {
        return new ErroDto(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(CreateException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto badRequest(HttpServletRequest req, CreateException ex) {
        return new ErroDto(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto badRequest(HttpServletRequest req, IOException ex) {
        return new ErroDto(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto campoObrigatorioNaoPreenchidoCorretamente(HttpServletRequest request,
                                                             MethodArgumentNotValidException ex) {
        return new ErroDto(HttpStatus.BAD_REQUEST.value(), tratarMensagem(ex));
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDto campoObrigatorioNaoPreenchidoCorretamente(HttpServletRequest request,
                                                             ValidationException ex) {
        return new ErroDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ErroDto erroConflitoAoProcessarModificacao(HttpServletRequest request, ConflictException ex) {
        return new ErroDto(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(UnprocessableException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErroDto erroSintaxeAoProcessarEntidade(HttpServletRequest request, UnprocessableException ex) {
        return new ErroDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
//    @ResponseBody
//    public ErroDto naoPossuiPermissao(HttpServletRequest req, HttpServletResponse resp) {
//        String message = messageBuilder.getMessage(SEGURANCA_ACESSO_NEGADO);
//        return new ErroDto(HttpStatus.UNAUTHORIZED.value(), message);
//    }

    private String tratarMensagem(MethodArgumentNotValidException ex) {
        final String target = ex.getBindingResult().getFieldError().getField();
        return "Falha ao validar campo ".concat(target).concat(" - Causa: 	").concat(getCause(ex));
    }

    private String getCause(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining((", ")));
    }
}
