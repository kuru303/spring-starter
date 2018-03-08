package br.com.mbamobi.wscapes.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.mbamobi.wscapes.converter.BooleanToSimNaoConverter;

@Entity
@Table(name = "TB_USUARIO")  
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CO_SEQ_USUARIO", unique = true, nullable = false)
    private long id;
    
    @Column(name = "DS_USERNAME", nullable=false)
    private String username;

    @Column(name = "DS_SENHA", nullable=false)
    private String senha;
    
    @OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="CO_PESSOA")
    private Pessoa pessoa;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="CO_ROLE")
    private UserRole usuarioRole;
    
    @Column(name = "ST_ATIVO")
    @Convert(converter=BooleanToSimNaoConverter.class)
    private Boolean ativo;

    @Column(name = "ST_SENHA_TEMPORARIA")
    @Convert(converter=BooleanToSimNaoConverter.class)
    private Boolean senhaTemporaria;
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    
	/**
	 * @return the cpf
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param cpf the cpf to set
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
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the usuarioRole
	 */
	public UserRole getUsuarioRole() {
		return usuarioRole;
	}

	/**
	 * @param usuarioRole the usuarioRole to set
	 */
	public void setUsuarioRole(UserRole usuarioRole) {
		this.usuarioRole = usuarioRole;
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
	
	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return (user.id == id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "br.com.mbamobi.wscapes.entity.Usuario[ id=" + id + " ]";
    }
    
}
