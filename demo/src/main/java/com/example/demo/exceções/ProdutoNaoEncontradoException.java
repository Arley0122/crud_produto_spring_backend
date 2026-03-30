package com.example.demo.exceções;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(int id){
        super("O id "+id+" não existe");
    }
    
}
