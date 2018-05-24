package br.com.arquivo.lote;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.arquivo.lote.model.Cliente;
import br.com.arquivo.lote.model.Venda;
import br.com.arquivo.lote.model.Vendedor;
import br.com.arquivo.lote.motor.MotorArquivoLote;

public class TestMotorArquivoLote {

	private MotorArquivoLote motor;

	@Before
	public void init() throws FileNotFoundException {
		this.motor = new MotorArquivoLote();

		// clientes
		this.motor.getClientes().add(new Cliente("001", 2345675434544345l, "Jose da Silva", "Rural"));

		// vendedores
		this.motor.getVendedores().add(new Vendedor("002", 1234567891234l, "Diego", new BigDecimal(5000)));

		// vendas
		this.motor.getVendas()
				.add(new Venda("003", 10l, 11010l, new BigDecimal(300), new BigDecimal(3403), "Diego"));
		this.motor.getVendas()
				.add(new Venda("003", 8l, 13410l, new BigDecimal(540), new BigDecimal(2400), "Renato"));

	}

	@Test
	public void obterQuantidadeClientes() {
		int qtd = this.motor.obterQuantidadeClientes();

		Assert.assertEquals(1, qtd);
	}

	@Test
	public void obterQuantidadeVendedores() {
		int qtd = this.motor.obterQuantidadeVendedores();

		Assert.assertEquals(1, qtd);
	}

	@Test
	public void obterMaiorVenda() {
		Long maiorVenda = this.motor.obterMaiorVenda();

		Assert.assertEquals(8, maiorVenda.longValue());
	}

	@Test
	public void obterPiorVendedor() {
		String piorVendedor = this.motor.obterPiorVendedor();

		Assert.assertEquals("Diego", piorVendedor);
	}

}
