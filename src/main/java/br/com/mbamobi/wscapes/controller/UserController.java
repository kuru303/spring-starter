package br.com.mbamobi.wscapes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mbamobi.wscapes.dto.AlteraSenhaDTO;
import br.com.mbamobi.wscapes.dto.LoginResponseDTO;
import br.com.mbamobi.wscapes.entity.Pessoa;
import br.com.mbamobi.wscapes.entity.User;
import br.com.mbamobi.wscapes.service.PessoaService;
import br.com.mbamobi.wscapes.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PessoaService pessoaService;
    
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public User getUser() {
    	return this.userService.getUser();
    }
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public LoginResponseDTO login() {
    	return this.userService.login();
    }
    
    @RequestMapping(value = "/recovery", method = {RequestMethod.GET, RequestMethod.POST})
    public User recoveryPassword(@RequestBody Pessoa pessoa) {
    	return this.userService.resetPassword(pessoa);
    }
    
    @RequestMapping(value = "/trocasenha", method = {RequestMethod.GET, RequestMethod.POST})
    public User alterarSenha(@RequestBody AlteraSenhaDTO alteraSenhaDTO) {
    	return this.userService.alterarSenha(alteraSenhaDTO);
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
    	return this.userService.saveUser(user);
    }
    
    @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
    public Pessoa changePessoa(@RequestBody Pessoa pessoa) {
    	return this.pessoaService.merge(pessoa);
    }
    
}
