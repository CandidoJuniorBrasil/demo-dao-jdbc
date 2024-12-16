package modelo.dao;

import java.util.List;

import modelo.entidades.Departamento;

public interface DepartamentoDao {
	
	void insert(Departamento obj);
	void update(Departamento obj);
	void deleteByID(Integer id);
	Departamento findById (Integer id);
	List<Departamento> findAll();

}
