package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuRepo;
	
	public List<Usuario> getAll(){
		return usuRepo.findAll();
	}
}
