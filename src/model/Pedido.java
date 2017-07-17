package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Minicurso Persistindo com Hibernate
 * @author francisco 
 *
 */
@Entity
public class Pedido {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	private String nomeCliente;
	@Column(length=11)
	private String cpf;
	@Temporal(TemporalType.DATE)
	private Date dataCompra;
	private Situacao situacao;
	@ManyToMany
	private List<Produto> produtos;		
	@OneToOne
	@JoinColumn(name="ntfs_id")
	private NotaFiscal notaFiscal;

	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", nomeCliente=" + nomeCliente
				+ ", cpf=" + cpf + ", dataCompra=" + dataCompra + "]";
	}
	
}
