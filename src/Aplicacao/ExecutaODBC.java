package Aplicacao;

import java.util.List;

import modelo.dao.DaoFabrica;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class ExecutaODBC {

	public static void main(String[] args) {
	
		VendedorDao vendedorDao = DaoFabrica.criaVendedorDao();
		System.out.println("=== Teste 1: Vendedor findById =====");
		Vendedor vendedor = vendedorDao.findById(3);
		System.out.println(vendedor);

		System.out.println("\n=== Teste 2: Vendedor findByDepartment =====");
		Departamento departamento = new Departamento(2,null);
		List<Vendedor> lista = vendedorDao.findByDepartment(departamento);
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== Teste 3: Vendedor findByAll =====");
	    lista = vendedorDao.findAll();
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}	
	}

}
