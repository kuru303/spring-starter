package br.com.mbamobi.wscapes.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mbamobi.wscapes.dao.UserRepository;
import br.com.mbamobi.wscapes.dto.AlteraSenhaDTO;
import br.com.mbamobi.wscapes.dto.LoginResponseDTO;
import br.com.mbamobi.wscapes.entity.Pessoa;
import br.com.mbamobi.wscapes.entity.User;
import br.com.mbamobi.wscapes.singleton.Util;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository rep;
	
	private final String ALFABETO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXZ";
	private final Integer WORKLOAD = 12;
	private final String SALT = BCrypt.gensalt(WORKLOAD);
	
	public User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return rep.findByUsername(authentication.getName());
	}
	
	public LoginResponseDTO login() {
		User loggedUser = getUser();
		LoginResponseDTO loginDTO = new LoginResponseDTO();
		loginDTO.setUsername(loggedUser.getUsername());
		loginDTO.setSenha(loggedUser.getSenha());
		loginDTO.setSenhaTemporaria( loggedUser.getSenhaTemporaria() );
        return loginDTO;
	}
	
	public User resetPassword(final Pessoa pessoa) {
        User loggedUser = rep.findByCPF(pessoa.getCpf());
        String tempPass = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
        	tempPass += ALFABETO.charAt(r.nextInt(ALFABETO.length()));
        }
        loggedUser.setSenha(BCrypt.hashpw(tempPass, SALT));
        loggedUser.setSenhaTemporaria(Boolean.TRUE);
        User user = rep.save(loggedUser);
        Util.getInstance().sendEmail(user.getPessoa().getEmail(), tempPass);
        return user;
	}
	
	public User alterarSenha(final AlteraSenhaDTO dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = rep.findByUsername(authentication.getName());
        loggedUser.setSenha(BCrypt.hashpw(dto.getSenhaNova(), SALT));
        loggedUser.setSenhaTemporaria(Boolean.FALSE);
        return rep.save(loggedUser);
	}
	
	public User saveUser(final User usuario) {
		usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), SALT));
		usuario.setSenhaTemporaria(Boolean.FALSE);
		return rep.save(usuario);
	}
	
}
