package com.andrekunitz.modelagem;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andrekunitz.modelagem.domain.Categoria;
import com.andrekunitz.modelagem.domain.Cidade;
import com.andrekunitz.modelagem.domain.Cliente;
import com.andrekunitz.modelagem.domain.Endereco;
import com.andrekunitz.modelagem.domain.Estado;
import com.andrekunitz.modelagem.domain.Pagamento;
import com.andrekunitz.modelagem.domain.PagamentoComBoleto;
import com.andrekunitz.modelagem.domain.PagamentoComCartao;
import com.andrekunitz.modelagem.domain.Pedido;
import com.andrekunitz.modelagem.domain.Produto;
import com.andrekunitz.modelagem.domain.enums.EstadoPagamento;
import com.andrekunitz.modelagem.domain.enums.TipoCliente;
import com.andrekunitz.modelagem.repositories.CategoriaRepository;
import com.andrekunitz.modelagem.repositories.CidadeRepository;
import com.andrekunitz.modelagem.repositories.ClienteRepository;
import com.andrekunitz.modelagem.repositories.EnderecoRepository;
import com.andrekunitz.modelagem.repositories.EstadoRepository;
import com.andrekunitz.modelagem.repositories.PagamentoRepository;
import com.andrekunitz.modelagem.repositories.PedidoRepository;
import com.andrekunitz.modelagem.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagemApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository; 
	@Autowired
	private ProdutoRepository produtoRepository; 
	@Autowired
	private CidadeRepository cidadeRepository; 
	@Autowired
	private EstadoRepository estadoRepository; 
	@Autowired
	private ClienteRepository clienteRepository; 
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório"); 
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00); 
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3)); 
		cat2.getProdutos().addAll(Arrays.asList(p2)); 
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2)); 
		p3.getCategorias().addAll(Arrays.asList(cat1)); 
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3)); 
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo"); 
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2); 
		Cidade c3 = new Cidade(null, "Campinas", est2); 
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2)); 
		cidadeRepository.save(Arrays.asList(c1, c2, c3)); 
		
		Cliente cli1 = new Cliente(null, "maria Silva", "maria@gmail.com", "11122233344", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11112222", "33334444"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "APTO 303", "Jardim", "99888111", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "774444111", cli1, c2); 
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2)); 
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2)); 
		
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy hh:mm"); 
		
		Pedido ped1 = new Pedido(null, date.parse("16/01/2018 20:00"), cli1, e1); 
		Pedido ped2 = new Pedido(null, date.parse("16/01/2018 20:30"), cli1, e2); 
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6); 
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, date.parse("17/01/2018 00:00"), null); 
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2)); 
		
		pedidoRepository.save(Arrays.asList(ped1, ped2)); 
		pagamentoRepository.save(Arrays.asList(pgto1, pgto2)); 
	}
}
