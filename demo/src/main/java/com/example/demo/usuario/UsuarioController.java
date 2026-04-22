package com.example.demo.usuario;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.pedido.PedidoDTO;
import com.example.demo.pedido.PedidoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public UsuarioController(UsuarioService usuarioService, PedidoService pedidoService) {
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    // 1. Criar usuário
    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO criado = usuarioService.criarUsuario(dto);

        //monta URI do recurso criado (ex: /usuarios/1)
        URI location = URI.create("/usuarios/" + criado.getId());

        return ResponseEntity.created(location).body(criado);
    }

    // 2. Listar todos
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // 3. Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            // mais pra frente podemos trocar por uma exceção customizada UsuarioNaoEncontradoException
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    //Buscar todos pedidos de um usuário por id
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoDTO>> listarPedidosDoUsuario(@PathVariable Long id) {
        // UsuarioDTO usuario = usuarioService.buscarPorId(id);
        // if (usuario == null) return ResponseEntity.notFound().build();

        List<PedidoDTO> pedidos = pedidoService.listarPorUsuario(id);
        return ResponseEntity.ok(pedidos);
    }
}