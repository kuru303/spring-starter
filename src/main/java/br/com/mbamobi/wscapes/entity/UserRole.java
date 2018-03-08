package br.com.mbamobi.wscapes.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USUARIO_ROLE")  
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CO_SEQ_ROLE", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "DS_ROLE", nullable=false)
    private String descricao;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof UserRole)) {
            return false;
        }

        UserRole userRole = (UserRole) o;

        return (userRole.id == id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "br.com.mbamobi.wscapes.entity.UserRole[ descricao=" + descricao + " ]";
    }

}
