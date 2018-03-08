package br.com.mbamobi.wscapes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mbamobi.wscapes.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query(value = "SELECT * "
		     + " FROM TB_PESSOA P "
		     + " WHERE P.NU_CPF = :cpf ", nativeQuery = true)
	Pessoa findByCPF( 
		  @Param("cpf") Long cpf);
	
	@Query(value = "SELECT P.* "
		     + " FROM TB_AGENTE A JOIN TB_PESSOA P ON P.CO_SEQ_PESSOA = A.CO_PESSOA "
		     + " WHERE A.CO_AGENTE = :coAgente ", nativeQuery = true)
	Pessoa findByAgente( @Param("coAgente") Long coAgente );
	
}