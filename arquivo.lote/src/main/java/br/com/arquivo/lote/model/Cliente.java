package br.com.arquivo.lote.model;

import java.io.Serializable;

/**
 * 
 * Classe modelo para representar os clientes
 * 
 * @author andre.lermen
 *
 */
public class Cliente implements Serializable {

	private static final long serialVersionUID = 8024218438904613381L;

	private String tipo;

	private Long cnpj;

	private String nome;

	private String ramoAtividade;

	public Cliente() {
	}

	public Cliente(String tipo, Long cnpj, String nome, String ramoAtividade) {
		this.tipo = tipo;
		this.cnpj = cnpj;
		this.nome = nome;
		this.ramoAtividade = ramoAtividade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	@Override
	public String toString() {
		return "cnpj: " + cnpj + " nome: ".concat(nome).concat(" ramo de atividade: ").concat(ramoAtividade);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

}
