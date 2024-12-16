package modelo.dao;

import java.util.List;

import modelo.entidades.Vendedor;

public interface VendedorDao {

	void insert(Vendedor obj);
	void update(Vendedor obj);
	void deleteByID(Integer id);
	Vendedor findById (Integer id);
	List<Vendedor> findAll();
}
