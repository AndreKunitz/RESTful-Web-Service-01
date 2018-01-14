package com.andrekunitz.modelagem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrekunitz.modelagem.domain.Cliente;
import com.andrekunitz.modelagem.repositories.ClienteRepository;
import com.andrekunitz.modelagem.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()); 
		}
		return obj; 
	}
}
