package com.cpincerato.vraptor4.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Query;

import com.cpincerato.vraptor4.model.Produtos;

@RequestScoped
public class ProdutosRepository {

	@Inject
	private Session session;
	
	private Criteria createCriteria() {
		return session.createCriteria(Produtos.class);
	}

	@SuppressWarnings("unchecked")
	public List<Produtos> list() {
		return createCriteria().list();
	}
	
	public void save(Produtos produto) {
		session.save(produto);
	}
	
	public void update(Produtos produto) {
		session.update(produto);
	}
	
	public void deleteById(Long id) {
		Query query = session.createQuery("delete from Produtos where id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public Produtos findById(Long id) {
		return (Produtos) session.get(Produtos.class, id);
	}

}
