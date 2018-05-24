package br.com.arquivo.lote.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * Classe modelo para representar as vendas
 * 
 * @author andre.lermen
 *
 */
public class Venda implements Serializable {

	private static final long serialVersionUID = 5862716229111497751L;

	private String tipo;

	private Long id;

	private Long itemId;

	private BigDecimal quantidade;

	private BigDecimal preco;

	private String vendedor;

	public Venda() {
	}

	public Venda(String tipo, Long id, Long itemId, BigDecimal quantidade, BigDecimal preco, String vendedor) {
		this.tipo = tipo;
		this.id = id;
		this.itemId = itemId;
		this.quantidade = quantidade;
		this.preco = preco;
		this.vendedor = vendedor;
	}

	/**
	 * Método que calcula o valor total da venda multiplicando a quantidade *
	 * preço
	 * 
	 * @return BigDecimal valor total da venda
	 */
	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = this.quantidade.multiply(this.preco);
		return valorTotal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	@Override
	public String toString() {
		return "id venda: " + id + " item id: " + itemId + " quantidade: " + quantidade + " preço: " + preco
				+ " vendedor: " + vendedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
