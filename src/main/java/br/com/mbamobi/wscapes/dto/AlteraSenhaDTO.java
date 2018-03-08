package br.com.mbamobi.wscapes.dto;

public class AlteraSenhaDTO {
	
	private String senhaAtual;
	
	private String senhaNova;
	
	private String senhaConfirmacao;

	/**
	 * @return the senhaAtual
	 */
	public String getSenhaAtual() {
		return senhaAtual;
	}

	/**
	 * @param senhaAtual the senhaAtual to set
	 */
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	/**
	 * @return the senhaNova
	 */
	public String getSenhaNova() {
		return senhaNova;
	}

	/**
	 * @param senhaNova the senhaNova to set
	 */
	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	/**
	 * @return the senhaConfirmacao
	 */
	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	/**
	 * @param senhaConfirmacao the senhaConfirmacao to set
	 */
	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}
	
	

}
