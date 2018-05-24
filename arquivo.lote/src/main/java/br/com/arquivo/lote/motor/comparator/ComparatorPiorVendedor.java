package br.com.arquivo.lote.motor.comparator;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map.Entry;

/**
 * 
 * Classe para encontrar o pior vendedor
 * 
 * @author andre.lermen
 *
 */
public class ComparatorPiorVendedor implements Comparator<Entry<String, BigDecimal>> {

	@Override
	public int compare(Entry<String, BigDecimal> entry1, Entry<String, BigDecimal> entry2) {
		return entry1.getValue().compareTo(entry2.getValue());
	}

}
