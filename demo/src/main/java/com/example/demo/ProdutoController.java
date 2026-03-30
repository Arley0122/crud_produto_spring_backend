package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class ProdutoController {

    @Autowired 
    private ProdutoService service;

    @GetMapping("/produto")
    public ResponseEntity<?> buscarProdutos(){
        
        List<ProdutoDTO> produtos = service.buscarProdutos();

        if(!produtos.isEmpty()) return ResponseEntity.status(200).body(produtos);
        else return ResponseEntity.status(404).build();
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<?> buscarProdutoPorId(
        @PathVariable int id
    ){
        ProdutoDTO resposta = service.buscarProdutoPorId(id);

         return ResponseEntity.status(200).body(resposta);
    }

    @PostMapping("/produto")
    public ResponseEntity<?> criarProduto(
        @RequestBody ProdutoDTO dto
    ){
        if(dto == null) return ResponseEntity.status(400).build();

        else{
            ProdutoDTO salvo = service.criarProduto(dto);
            return ResponseEntity.status(200).body(salvo);
        }
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<?> atualizarProdutoById(
        @RequestBody ProdutoDTO dto,
        @PathVariable int id

        ){
        ProdutoDTO dtoAtualizado = service.atualizarProdutoById(id, dto);
        if(dtoAtualizado == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(dtoAtualizado);
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<?> deletarProdutoPorId(
        @PathVariable int id
    ){
        boolean concluiu = service.deletarProdutoPorId(id);
        if(!concluiu) return ResponseEntity.status(404).build();
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/produto")
    public ResponseEntity<?> deletarTodosProdutos(){
        service.deletarTodosProdutos();
        return ResponseEntity.status(204).build();
    }

}