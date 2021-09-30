package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImpl implements ProductsRepository {
	private final Connection connection;

	ProductsReposutoryJdbcImpl(Connection c) {
		this.connection = c;
	}

	@Override
	public List<Product> findAll() {
		return null;
	}

	@Override
	public Optional<Product> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public void update(Product product) {

	}

	@Override
	public void save(Product product) {

	}

	@Override
	public void delete(Long id) {

	}
}
