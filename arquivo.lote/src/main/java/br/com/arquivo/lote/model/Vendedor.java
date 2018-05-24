package br.com.arquivo.lote.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * Classe modelo para representar os vendedores
 * 
 * @author andre.lermen
 *
 */
public class Vendedor implements Serializable {

	private static final long serialVersionUID = 8681526558752142032L;

	private String tipo;

	private Long cpf;

	private String nome;

	private BigDecimal salario;

	public Vendedor() {
	}

	public Vendedor(String tipo, Long cpf, String nome, BigDecimal salario) {
		this.tipo = tipo;
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "cpf: " + cpf + " nome: ".concat(nome).concat(" salário: ") + salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Vendedor other = (Vendedor) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}
