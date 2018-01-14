package com.andrekunitz.modelagem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrekunitz.modelagem.domain.Categoria;
import com.andrekunitz.modelagem.repositories.CategoriaRepository;
import com.andrekunitz.modelagem.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()); 
		}
		return obj; 
	}
}
