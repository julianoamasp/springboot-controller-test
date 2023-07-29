package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Usuario;
import com.example.demo.Service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	public static List<Usuario> usuarios = new ArrayList<>();

	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok().body(usuarioService.getAll());
	}
}
