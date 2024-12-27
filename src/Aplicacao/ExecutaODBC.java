package Aplicacao;

import java.util.Date;
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
		
		/*System.out.println("\n=== Teste 4: Vendedor Insert =====");
		Vendedor novoVendedor = new Vendedor((Integer) null, "Cris", "cris@gmail.com", new Date(), 4000.0, departamento);
		vendedorDao.insert(novoVendedor);
		System.out.println("Inserido! novo ID = " + novoVendedor.getId());*/
		
		System.out.println("\n=== Teste 5: Vendedor atualizado =====");
		vendedor = vendedorDao.findById(1);
		vendedor.setName("Marta Maria");
		vendedorDao.update(vendedor);
		System.out.println("Vendedor atualizado!");
	}

}
