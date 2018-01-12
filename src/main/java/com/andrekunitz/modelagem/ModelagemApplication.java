package com.andrekunitz.modelagem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andrekunitz.modelagem.domain.Categoria;
import com.andrekunitz.modelagem.repositories.CategoriaRepository;

@SpringBootApplication
public class ModelagemApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório"); 
		
		categoriaRepository.save(Arrays.asList(cat1,cat2)); 
	}
}
