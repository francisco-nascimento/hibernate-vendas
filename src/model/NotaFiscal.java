package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Minicurso Persistindo com Hibernate
 * @author francisco 
 *
 */
@Entity
public class NotaFiscal {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataGeracao;
	private boolean eletronica;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Date getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public boolean isEletronica() {
		return eletronica;
	}
	public void setEletronica(boolean eletronica) {
		this.eletronica = eletronica;
	}
	@Override
	public String toString() {
		return "NotaFiscal [codigo=" + codigo + ", dataGeracao=" + dataGeracao
				+ ", eletronica=" + eletronica + "]";
	}
}
