package br.com.smartmed.consultas.rest.controller;

import br.com.smartmed.consultas.model.UsuarioModel;
import br.com.smartmed.consultas.rest.dto.login.LoginInDTO;
import br.com.smartmed.consultas.rest.dto.login.LoginOutDTO;
import br.com.smartmed.consultas.rest.dto.login.UsuarioInDTO;
import br.com.smartmed.consultas.rest.dto.login.UsuarioOutDTO;
import br.com.smartmed.consultas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginOutDTO> login(@RequestBody LoginInDTO loginRequest) {
        LoginOutDTO response = usuarioService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioInDTO> buscarPorId(@PathVariable Integer id) {
        UsuarioInDTO usuarioExistente = usuarioService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioExistente);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioOutDTO>> listarTodos() {
        List<UsuarioOutDTO> usuarios = usuarioService.obterTodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioOutDTO> cadastrarUsuario(@Valid @RequestBody UsuarioModel usuario) {
        UsuarioOutDTO novoUsuario = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<UsuarioInDTO> atualizarUsuario(@Valid @RequestBody UsuarioModel usuario) {
        UsuarioInDTO usuarioExistente = usuarioService.atualizar(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioExistente);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletarUsuario(@Valid @RequestBody UsuarioModel usuario) {
        usuarioService.deletar(usuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}