package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Minicurso Persistindo com Hibernate
 * @author francisco 
 *
 */
@Entity
@Table(name="TBFORNECEDOR")
public class Fornecedor {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@Column(length=14)
	private String cnpj;
	private String razaoSocial;

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	@Override
	public String toString() {
		return "Fornecedor [codigo=" + codigo + ", cnpj=" + cnpj
				+ ", razaoSocial=" + razaoSocial + "]";
	}
	
	
}
