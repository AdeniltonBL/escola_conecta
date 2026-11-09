package br.com.techsolutions.escolaconecta.service;

import br.com.techsolutions.escolaconecta.dto.usuario.UsuarioResponseDTO;
import br.com.techsolutions.escolaconecta.exception.RecursoNaoEncontradoException;
import br.com.techsolutions.escolaconecta.model.Chamado;
import br.com.techsolutions.escolaconecta.model.Usuario;
import br.com.techsolutions.escolaconecta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    private UsuarioResponseDTO converterParaDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.isAtivo()
        );
    }

    public Usuario cadastrar(Usuario usuario){

        if (usuario.getNome() == null || usuario.getNome().isBlank()){
            return null;
        }

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(
                "Usuario não encontrado"));
        return converterParaDTO(usuario);
    }

    public boolean excluir(Long id){
        if(!usuarioRepository.existsById(id)){}
            return false;
    }


}
