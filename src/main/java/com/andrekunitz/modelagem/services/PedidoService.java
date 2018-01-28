package com.andrekunitz.modelagem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrekunitz.modelagem.domain.Pedido;
import com.andrekunitz.modelagem.repositories.PedidoRepository;
import com.andrekunitz.modelagem.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()); 
		}
		return obj; 
	}
}
