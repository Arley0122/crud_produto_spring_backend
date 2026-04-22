package com.example.demo.usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService( UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return usuarioRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("O usuário não foi encontrado: "+ username));
    }
}
