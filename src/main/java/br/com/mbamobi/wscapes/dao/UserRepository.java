package br.com.mbamobi.wscapes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mbamobi.wscapes.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * "
		     + " FROM TB_USUARIO "
		     + " WHERE DS_USERNAME = :username ", nativeQuery = true)
	User findByUsername( @Param("username") String username);
	
	@Query(value = "SELECT A.* "
		     + " FROM TB_USUARIO A JOIN TB_PESSOA P ON P.CO_SEQ_PESSOA = A.CO_PESSOA "
		     + " WHERE P.NU_CPF = :cpf ", nativeQuery = true)
	User findByCPF( @Param("cpf") Long cpf );
	
}