package testes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Fornecedor;
import model.Pedido;
import model.Produto;
import model.Situacao;

/**
 * Minicurso Persistindo com Hibernate
 * @author francisco 
 *
 */
public class Teste {

	public static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("minicurso.vendas");
	public static EntityManager em;

	public static void main(String[] args) {

//		salvarFornecedor();
//		consultarFornecedor();
//		alterarFornecedor();
//		removerFornecedor();

//		salvarProduto();
		salvarPedido();
	}

	public static void salvarProduto(){
		try {
			// Instanciar objeto
			Produto p = new Produto();
			p.setNome("Livro Core Java - Fundamentos I");
			Fornecedor f = new Fornecedor();
			f.setCodigo(1);
			p.setFornecedor(f);
			// Salvar objeto
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}		
	}
	
	public static void salvarPedido(){
		try {
			// Instanciar objeto
			Pedido ped = new Pedido();
			ped.setCpf("10900239212");
			ped.setNomeCliente("Maria Elizabeth");
			ped.setSituacao(Situacao.PENDENTE_PAGAMENTO);
			ped.setDataCompra(new Date());
			
			em = emf.createEntityManager();

			Produto p1 = em.find(Produto.class, 1);
			Produto p2 = em.find(Produto.class, 2);
			List<Produto> lista = new ArrayList<Produto>();
			lista.add(p1);
			lista.add(p2);
			
			ped.setProdutos(lista);

			// Salvar objeto
			em.getTransaction().begin();
			em.persist(ped);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}		
	}
	
	public static void consultarFornecedor() {
		try {
			// Consultar objeto
			em = emf.createEntityManager();
			Fornecedor f = em.find(Fornecedor.class, 2);
			if (f != null){
				System.out.println(f.getRazaoSocial());	
			} else {
				System.out.println("Fornecedor nao encontrado");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	public static void alterarFornecedor() {
		try {
			// Consultar objeto
			em = emf.createEntityManager();
			Fornecedor f = em.find(Fornecedor.class, 2);              
			System.out.println(f.getRazaoSocial());
			
			f.setRazaoSocial("Livraria Sol e Mar");
			em.getTransaction().begin();
			f = em.merge(f);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	public static void removerFornecedor() {
		try {
			// Consultar objeto
			em = emf.createEntityManager();
			Fornecedor f = em.find(Fornecedor.class, 2);              
			System.out.println(f.getRazaoSocial());
			// Remover objeto
			em.getTransaction().begin();
			em.remove(f);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	public static void salvarFornecedor() {
		try {
			// Instanciar objeto
			Fornecedor f = new Fornecedor();
			f.setCnpj("123123000123");
			f.setRazaoSocial("Livraria Solemar");
			// Salvar objeto
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(f);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
