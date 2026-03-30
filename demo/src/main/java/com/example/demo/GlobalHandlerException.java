package com.example.demo;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exceções.ProdutoNaoEncontradoException;

@ControllerAdvice
public class GlobalHandlerException {
    
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> tratarProdutoNaoEncontraod(ProdutoNaoEncontradoException ex){

        ErrorResponse erro = new ErrorResponse();
        erro.setStatus(404);
        erro.setTimeAmp(Instant.now());
        erro.setMessage(ex.getMessage());

        return ResponseEntity.status(404).body(erro);
    }
}
 
