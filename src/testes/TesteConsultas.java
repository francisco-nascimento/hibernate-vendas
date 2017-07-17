package testes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Fornecedor;
import model.NotaFiscal;
import model.Pedido;
import model.Produto;

/**
 * Minicurso Persistindo com Hibernate
 * @author francisco 
 *
 */
public class TesteConsultas {

	public static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("minicurso.vendas");
	public static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {

		consulta0();
		consulta1();
		consulta2();

		Fornecedor forn1 = new Fornecedor();
		forn1.setCodigo(1);
		List<Produto> res = buscarProdutosPorFornecedor(forn1);
		printList(res);

		List<Fornecedor> forns = buscarFornecedoresPorRazao("Livraria");
		printList(forns);

		Calendar cal = Calendar.getInstance();
		
		cal.set(2017, 6, 10);		
		Date data1 = cal.getTime();
		cal.set(2017, 6, 15);		
		Date data2 = cal.getTime();
		System.out.println(data1);
		System.out.println(data2);
		List<Produto> prods = buscarProdutos("10900239212", data1, data2);
		printList(prods);
		
		List<NotaFiscal> notas = buscarNotaFiscal("");
		printList(notas);
		
		List<Pedido> peds = buscarPedidos();
		printList(peds);
		
		em.close();
		emf.close();
	}

	public static void consulta0() {
		TypedQuery<Fornecedor> q1 = em.createQuery("from Fornecedor f",
				Fornecedor.class);
		List<Fornecedor> resultados = q1.getResultList();
		for (Fornecedor f : resultados) {
			System.out.println(f.getRazaoSocial());
		}
	}

	public static void consulta1() {
		TypedQuery<Produto> q1 = em.createQuery(
				"select p from Produto p where p.nome like :n", Produto.class);
		q1.setParameter("n", "%" + "Java" + "%");
		List<Produto> resultados = q1.getResultList();
		for (Produto p : resultados) {
			System.out.println(p.getCodigo() + "-" + p.getNome());
		}
	}

	public static void consulta2() {
		TypedQuery<Produto> q1 = em.createQuery(
				"select p from Produto p where p.nome like ?1"
						+ " and p.fornecedor.codigo = ?2", Produto.class);
		q1.setParameter(1, "%" + "Java" + "%");
		q1.setParameter(2, 1);
		List<Produto> resultados = q1.getResultList();
		for (Produto p : resultados) {
			System.out.println(p.getCodigo() + "-" + p.getNome());
		}
	}

	public static List<Fornecedor> buscarFornecedoresPorRazao(String razao) {
		TypedQuery<Fornecedor> q1 = em.createQuery(
				"Select f from Fornecedor f where f.razaoSocial like :rz",
				Fornecedor.class);
		q1.setParameter("rz", "%" + razao + "%");
		return q1.getResultList();
	}

	public static List<Produto> buscarProdutosPorFornecedor(
			Fornecedor fornecedor) {
		TypedQuery<Produto> q1 = em.createQuery(
				"Select p from Produto p where p.fornecedor = :forn",
				Produto.class);
		q1.setParameter("forn", fornecedor);
		return q1.getResultList();
	}

	public static List<Produto> buscarProdutos(String cpf, Date dataInicio, Date dataFim) {
		List<Produto> acumulado = new ArrayList<Produto>();
		Query q = 
			em.createQuery("Select ped.produtos from Pedido ped "
					+ "where ped.cpf = :cpf and ped.dataCompra between :dt1 and :dt2");
		q.setParameter("cpf", cpf);
		q.setParameter("dt1", dataInicio);
		q.setParameter("dt2", dataFim);
		List<Object> res = q.getResultList(); 
		for(Object obj : res){
			if (obj instanceof Produto){
				Produto p = (Produto) obj;
				acumulado.add(p);
			}
			
		}
		return acumulado;
	}

	public static List<NotaFiscal> buscarNotaFiscal(String cnpj) {
		TypedQuery<NotaFiscal> q = em
				.createQuery(
						"Select p.notaFiscal from Pedido p join p.produtos pr "
								+ "where p.notaFiscal.eletronica = :elet and pr.fornecedor.cnpj = :cnpj",
						NotaFiscal.class);
		q.setParameter("elet", true);
		q.setParameter("cnpj", cnpj);
		return q.getResultList();
	}

	public static List<Pedido> buscarPedidos() {
		TypedQuery<Pedido> q = em.createQuery(
				"Select p from Pedido p where size(p.produtos) > 5 "
						+ "and month(p.dataCompra) = 6", Pedido.class);
		return q.getResultList();
	}

	public static void printList(List objs) {
		for (Object o : objs) {
			System.out.println(o);
		}
	}
}
