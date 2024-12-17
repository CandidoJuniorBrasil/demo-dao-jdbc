package Aplicacao;

import java.util.Date;

import modelo.dao.DaoFabrica;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class ExecutaODBC {

	public static void main(String[] args) {
	
		Departamento dpt = new Departamento(1,"Livros");
		
		Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.0, dpt);
		
		VendedorDao vendedorDao = DaoFabrica.criaVendedorDao();
		
		System.out.println(vendedor);

	}

}
