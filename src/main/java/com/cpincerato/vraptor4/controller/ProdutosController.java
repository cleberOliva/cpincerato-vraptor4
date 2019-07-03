package com.cpincerato.vraptor4.controller;

import javax.inject.Inject;

import com.cpincerato.vraptor4.model.Produtos;
import com.cpincerato.vraptor4.repository.ProdutosRepository;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
@Path("/produtos")
public class ProdutosController {

	@Inject
	private Result result;
	
	@Inject
	private ProdutosRepository produtosRepository;
	
	@Get
	@Path("/list")
	public void listAll() {
		result.use(Results.json())
		.withoutRoot()
		.from(produtosRepository.list())
		.serialize();
	}
	
	@Post
	@Path("/salvar")
	@Consumes("application/json")
	public void salvar(Produtos produto) {
		this.produtosRepository.save(produto);
		this.result.use(Results.status()).ok();
	}
	
	@Put
	@Path("/atualizar")
	@Consumes("application/json")
	public void atualizar(Produtos produto) {
		this.produtosRepository.update(produto);
		this.result.use(Results.status()).ok();
	}
	
	@Delete
	@Path("/delete/{id}")
	public void remove(Long id) {
		this.produtosRepository.deleteById(id);
		this.result.use(Results.status()).ok();
	}
	
	@Get
	@Path("/list/{id}")
	public void list(Long id) {
		result.use(Results.json())
		.withoutRoot()
		.from(produtosRepository.findById(id))
		.serialize();
	}
}
