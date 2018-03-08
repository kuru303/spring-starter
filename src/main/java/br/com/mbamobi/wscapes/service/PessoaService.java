package br.com.mbamobi.wscapes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mbamobi.wscapes.dao.PessoaRepository;
import br.com.mbamobi.wscapes.entity.Pessoa;
import br.com.mbamobi.wscapes.singleton.Util;

@Service
@Transactional
public class PessoaService {
	
	@Autowired
	private PessoaRepository rep;
	
	public Pessoa getPessoa( final Long id ) {
        return rep.findOne(id);
	}
	
	public List<Pessoa> listaPessoas() {
		return rep.findAll();
	}
	
	public Pessoa getByCpf( Long cpf ) {
		return rep.findByCPF(cpf);
	}
	
	public Pessoa merge(final Pessoa pessoa) {
		Pessoa pessDB = getPessoa( pessoa.getId() );
		return rep.save( Util.getInstance().mergeObjects( pessDB, pessoa ) );
	}

}
