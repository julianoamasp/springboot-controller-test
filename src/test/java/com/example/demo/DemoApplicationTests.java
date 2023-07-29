package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.Controller.UsuarioController;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	
	@Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Limpar a lista de usuários antes de cada teste
        try {
			UsuarioController.usuarios.clear();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testObterUsuariosRetorna200() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/usuario"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void testAdicionarUsuarioRetorna200() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Você pode adicionar asserções adicionais aqui, como verificar se o usuário foi realmente adicionado à lista
    }

    @Test
    public void testAtualizarUsuarioRetorna200() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Maria");

        // Adicionar o usuário à lista antes de atualizar
        UsuarioController.usuarios.add(usuario);

        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/{id}", 1)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verificar se o usuário foi atualizado corretamente (por exemplo, verificar o nome atualizado)
        assertThat(UsuarioController.usuarios.get(0).getNome()).isEqualTo("Maria");
    }

    @Test
    public void testExcluirUsuarioRetorna200() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Fulano");

        // Adicionar o usuário à lista antes de excluir
        UsuarioController.usuarios.add(usuario);

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verificar se o usuário foi excluído corretamente (por exemplo, verificar se a lista está vazia após a exclusão)
        assertThat(UsuarioController.usuarios).isEmpty();
    }
	
	
	
	
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@Test
	void contextLoads() {
		boolean dados = true;
		Assertions.assertTrue(dados);
	}


	@Test
	public void testarSalvar() {
		Usuario usuario = new Usuario();
		int total = usuarioRepository.findAll().size();
		usuario.setNome("teste");
		usuarioRepository.save(usuario);
		int novoTotal = usuarioRepository.findAll().size();
		Assertions.assertEquals(total, novoTotal);
	}
}
