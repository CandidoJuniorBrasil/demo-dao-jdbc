package Aplicacao;

import modelo.dao.DaoFabrica;
import modelo.dao.VendedorDao;
import modelo.entidades.Vendedor;

public class ExecutaODBC {

	public static void main(String[] args) {
	
		VendedorDao vendedorDao = DaoFabrica.criaVendedorDao();
		System.out.println("=== Teste 1: Vendedor findById =====");
		
		Vendedor vendedor = vendedorDao.findById(3);
		
		System.out.println(vendedor);

	}

}
