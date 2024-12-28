package Aplicacao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import modelo.dao.DaoFabrica;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class ExecutaODBC {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		VendedorDao vendedorDao = DaoFabrica.criaVendedorDao();
		System.out.println("=== Teste 1: Encontrando vendedor por departamento =====");
		Vendedor vendedor = vendedorDao.findById(3);
		System.out.println(vendedor);

		System.out.println("\n=== Teste 2: Encontrando vendedor por departamento =====");
		Departamento departamento = new Departamento(2,null);
		List<Vendedor> lista = vendedorDao.findByDepartment(departamento);
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== Teste 3: Encontrando todos os vendedores =====");
	    lista = vendedorDao.findAll();
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}	
		
		/*System.out.println("\n=== Teste 4: Vendedor Insert =====");
		Vendedor novoVendedor = new Vendedor((Integer) null, "Cris", "cris@gmail.com", new Date(), 4000.0, departamento);
		vendedorDao.insert(novoVendedor);
		System.out.println("Inserido! novo ID = " + novoVendedor.getId());*/
		
		System.out.println("\n=== Teste 5: Atualizando vendedor =====");
		vendedor = vendedorDao.findById(1);
		vendedor.setName("Marta Maria");
		vendedorDao.update(vendedor);
		System.out.println("Vendedor atualizado!");
		
		System.out.println("\n=== Teste 6: Vendedor deletadoDeletando vendedor");
		System.out.print("Forneça o Id do vendedor: ");
		int numVendedor = sc.nextInt();
		vendedorDao.deleteByID(numVendedor);
		System.out.println("Vendedor deletado!");
		
		sc.close();
	}

}
