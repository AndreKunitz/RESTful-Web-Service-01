package com.andrekunitz.modelagem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrekunitz.modelagem.domain.Categoria;
import com.andrekunitz.modelagem.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id); 
		return obj; 
	}
}
