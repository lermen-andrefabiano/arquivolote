package br.com.arquivo.lote.motor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import br.com.arquivo.lote.model.Cliente;
import br.com.arquivo.lote.model.Venda;
import br.com.arquivo.lote.model.Vendedor;
import br.com.arquivo.lote.motor.comparator.ComparatorMaiorVenda;
import br.com.arquivo.lote.motor.comparator.ComparatorPiorVendedor;
import br.com.arquivo.lote.motor.filter.FiltroArquivoDat;

/**
 * 
 * Classe motor para ler e tratar os dados oriundos dos arquivos .dat
 * 
 * @author andre.lermen
 *
 */
public class MotorArquivoLote implements Serializable {

	private static final long serialVersionUID = 4570383712647989948L;

	private static final String _003 = "003";
	private static final String _002 = "002";
	private static final String _001 = "001";
	private static final String DADOS_IN = "\\src\\main\\resources\\dados\\in";
	private static final String DADOS_OUT = "\\src\\main\\resources\\dados\\out\\sumarizacao.dat.proc";
	private static final String USER_DIR = "user.dir";

	// armazena lista dos clientes
	private Set<Cliente> clientes;

	// armazena lista das vendas
	private Set<Venda> vendas;

	// armazena lista de vendedores
	private Set<Vendedor> vendedores;

	public MotorArquivoLote() {
		this.clientes = new HashSet<>();
		this.vendas = new HashSet<>();
		this.vendedores = new HashSet<>();
	}

	/**
	 * Inicia o programa de leitura de arquivos .dat
	 * 
	 * @throws FileNotFoundException
	 */
	public void init() throws FileNotFoundException {
		this.executaMotorLote();
		this.saidaInformacoes();
	}

	/**
	 * Monta a saída das informações
	 */
	private void saidaInformacoes() {
		try {
			String caminho = System.getProperty(USER_DIR) + DADOS_OUT;
			FileWriter arquivo = new FileWriter(new File(caminho));
			arquivo.write(this.obterSumarizacao());
			arquivo.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Monta os valores que serão sumarizados na saída
	 * 
	 * @return String com a saída dos resultados
	 */
	private String obterSumarizacao() {
		StringBuilder saida = new StringBuilder("1. Quantidade de Clientes: ");
		saida.append(this.obterQuantidadeClientes());
		saida.append("\n");
		saida.append("2. Quantidade de Vendedores: ");
		saida.append(this.obterQuantidadeVendedores());
		saida.append("\n");
		saida.append("3. ID da Venda de valor mais alto: ");
		saida.append(this.obterMaiorVenda());
		saida.append("\n");
		saida.append("4. Nome do Vendedor que menos vendeu: ");
		saida.append(this.obterPiorVendedor());

		return saida.toString();
	}

	/**
	 * Recupera a quantidade de clientes
	 * 
	 * @return int com o valor total de clientes
	 */
	public int obterQuantidadeClientes() {
		return this.getClientes().size();
	}

	/**
	 * Recupera a quantidade de vendedores
	 * 
	 * @return int com o valor total de vendedores
	 */
	public int obterQuantidadeVendedores() {
		return this.getVendedores().size();
	}

	/**
	 * Recupera o pior vendedor
	 * 
	 * @return String nome do vendedor
	 */
	public String obterPiorVendedor() {
		Map<String, BigDecimal> vendasVendedor = new HashMap<>();

		for (Venda venda : this.vendas) {
			BigDecimal result = vendasVendedor.get(venda.getVendedor());

			if (result != null) {
				result = result.add(venda.getValorTotal());
			} else {
				result = venda.getValorTotal();
			}

			vendasVendedor.put(venda.getVendedor(), result);

		}

		Entry<String, BigDecimal> piorVendedor = null;
		if (!vendasVendedor.isEmpty()) {
			piorVendedor = Collections.min(vendasVendedor.entrySet(), new ComparatorPiorVendedor());
		}

		return piorVendedor != null ? piorVendedor.getKey() : "";
	}

	/**
	 * Recupera a maior venda
	 * 
	 * @return id da maio venda
	 */
	public Long obterMaiorVenda() {
		Venda maiorVenda = null;
		if (!this.vendas.isEmpty()) {
			maiorVenda = Collections.max(this.vendas, new ComparatorMaiorVenda());
		}

		return maiorVenda != null ? maiorVenda.getId() : 0l;
	}

	/**
	 * Executa a leitura e tratamento dos dados oriundos do arquivos .dat
	 * 
	 * @throws FileNotFoundException
	 */
	private void executaMotorLote() throws FileNotFoundException {
		File[] lotes = this.lerArquivoLote();

		for (int i = 0; i < lotes.length; i++) {
			String arquivo = lotes[i].getAbsolutePath();
			Scanner scanner = new Scanner(new FileReader(arquivo));

			while (scanner.hasNext()) {
				String linha = scanner.nextLine();

				this.obterLinhaScanner(linha);
			}

			scanner.close();
		}

	}

	/**
	 * Trata a linha de cada arquivo .dat
	 * 
	 * @param linha
	 */
	private void obterLinhaScanner(String linha) {
		Scanner lineScanner = new Scanner(linha);
		lineScanner.useDelimiter(";");
		lineScanner.useLocale(Locale.US);

		while (lineScanner.hasNext()) {
			if (linha.startsWith(_001)) {
				this.populaVendedor(lineScanner);

			} else if (linha.startsWith(_002)) {
				this.populaCliente(lineScanner);

			} else if (linha.startsWith(_003)) {
				this.populaVenda(lineScanner);

			}

		}

		lineScanner.close();
	}

	/**
	 * Popula as vendas na lista de Set<Venda>
	 * 
	 * @param lineScanner
	 *            - linha do arquivo
	 */
	private void populaVenda(Scanner lineScanner) {
		String tipo = lineScanner.next();
		Long vendaId = lineScanner.nextLong();
		Long itemId = lineScanner.nextLong();
		BigDecimal quantidade = lineScanner.nextBigDecimal();
		BigDecimal preco = lineScanner.nextBigDecimal();
		String vendedor = lineScanner.next();

		this.getVendas().add(new Venda(tipo, vendaId, itemId, quantidade, preco, vendedor));
	}

	/**
	 * Popula os clientes na lista de Set<Cliente>
	 * 
	 * @param lineScanner
	 *            - linha do arquivo
	 */
	private void populaCliente(Scanner lineScanner) {
		String tipo = lineScanner.next();
		Long cnpj = lineScanner.nextLong();
		String nome = lineScanner.next();
		String ramoAtividade = lineScanner.next();

		this.getClientes().add(new Cliente(tipo, cnpj, nome, ramoAtividade));
	}

	/**
	 * Popula os vendedores na lista de Set<Vendedor>
	 * 
	 * @param lineScanner
	 *            - linha do arquivo
	 */
	private void populaVendedor(Scanner lineScanner) {
		String tipo = lineScanner.next();
		Long cpf = lineScanner.nextLong();
		String nome = lineScanner.next();
		BigDecimal salario = lineScanner.nextBigDecimal();

		this.getVendedores().add(new Vendedor(tipo, cpf, nome, salario));

	}

	/**
	 * Faz a leitura os arquivos na pasta dados/in
	 * 
	 * @return File[] - array com os arquivos encontrados do tipo .dat
	 */
	public File[] lerArquivoLote() {
		String caminho = System.getProperty(USER_DIR) + DADOS_IN;

		File arquivos[];
		File diretorio = new File(caminho);
		arquivos = diretorio.listFiles(new FiltroArquivoDat());

		return arquivos;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public Set<Venda> getVendas() {
		return vendas;
	}

	public Set<Vendedor> getVendedores() {
		return vendedores;
	}

}
