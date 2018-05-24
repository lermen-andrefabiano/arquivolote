package br.com.arquivo.lote.motor.comparator;

import java.util.Comparator;

import br.com.arquivo.lote.model.Venda;

/**
 * 
 * Classe para encontrar a maior venda
 * 
 * @author andre.lermen
 *
 */
public class ComparatorMaiorVenda implements Comparator<Venda> {

	@Override
	public int compare(Venda venda, Venda vendaProx) {
		return venda.getValorTotal().compareTo(vendaProx.getValorTotal());
	}

}
