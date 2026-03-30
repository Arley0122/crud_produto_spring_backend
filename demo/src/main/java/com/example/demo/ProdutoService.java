package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceções.ProdutoNaoEncontradoException;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public ProdutoDTO criarProduto(ProdutoDTO dto){
        Produto produto = copiaParaProduto(dto);
        Produto salvo = repository.save(produto);
        ProdutoDTO resposta = copiarParaDTO(salvo);
        return resposta;
    }

    public List<ProdutoDTO> buscarProdutos(){
        List<Produto> produtos = repository.findAll();

        List<ProdutoDTO> dtos = new ArrayList<>();
        for(Produto produto: produtos){
            ProdutoDTO dto = copiarParaDTO(produto);
            dtos.add(dto);
        }   
        return dtos;
    }

    public ProdutoDTO buscarProdutoPorId(int id){
        Produto produto = repository.findById(id).orElseThrow(()-> new ProdutoNaoEncontradoException(id));
        ProdutoDTO resposta = copiarParaDTO(produto);
        return resposta;
        
    }

    public ProdutoDTO atualizarProdutoById(int id, ProdutoDTO dtoAtualizado){
        Produto produto = repository.findById(id).orElse(null);
        if(produto == null) return null;
        Produto produtoAtualizado = copiaParaProduto(dtoAtualizado);
        produtoAtualizado.setId(produto.getId());
        repository.save(produtoAtualizado);
        ProdutoDTO resposta = copiarParaDTO(produtoAtualizado);
        return resposta;
    }

    public boolean deletarProdutoPorId(int id){
        Produto produto = repository.findById(id).orElse(null);
        if(produto == null) return false;
        repository.deleteById(id);
        return true;
    }

    public void deletarTodosProdutos(){
        repository.deleteAll();
    }

    //FUNÇÕES AUXILIARES
    private Produto copiaParaProduto(ProdutoDTO dto){
       Produto produto = new Produto();
        if(dto.getId() != 0) produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setEstoque(dto.getEstoque());
        return produto;
    }

    private ProdutoDTO copiarParaDTO(Produto produto){
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setEstoque(produto.getEstoque());
        return dto;
    }

}
