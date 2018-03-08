package br.com.mbamobi.wscapes.dto;

public class LoginResponseDTO {
	
    private String username;
    private String senha;
    private Boolean senhaTemporaria;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * @return the senhaTemporaria
	 */
	public Boolean getSenhaTemporaria() {
		return senhaTemporaria;
	}
	/**
	 * @param senhaTemporaria the senhaTemporaria to set
	 */
	public void setSenhaTemporaria(Boolean senhaTemporaria) {
		this.senhaTemporaria = senhaTemporaria;
	}
	
}
