package Aplicacao;

import java.util.List;
import java.util.Scanner;

import modelo.dao.DaoFabrica;
import modelo.dao.DepartamentoDao;
import modelo.entidades.Departamento;


public class ExecutaJDBC2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartamentoDao departamentoDao = DaoFabrica.criaDepartamentoDao();
		System.out.println("=== Teste 1: Encontrando departamento por Id =====");
		Departamento departamento = departamentoDao.findById(3);
		System.out.println(departamento);
		
		System.out.println("\n=== Teste 2: Encontrando todos os departamentos =====");
	    List<Departamento>lista = departamentoDao.findAll();
		for (Departamento obj : lista) {
			System.out.println(obj);
		}
		
	    System.out.println("\n=== Teste 4: Inserindo novo departamento =====");
		Departamento novoDep = new Departamento(null, "Musica");
		departamentoDao.insert(novoDep);
		System.out.println("Inserido! novo ID = " + novoDep.getId()); 
		
		System.out.println("\n=== Teste 5: Atualizando um departamento =====");
		departamento = departamentoDao.findById(3);
		departamento.setDepartamento("Vestuario");
		departamentoDao.update(departamento);
		System.out.println("Departamento atualizado!");
		
		System.out.println("\n=== Teste 6: Deletando um departamento");
		System.out.print("Forneça o Id do departamento: ");
		int numDep = sc.nextInt();
		departamentoDao.deleteByID(numDep);
		System.out.println("Departamento deletado!");

	}
}
 