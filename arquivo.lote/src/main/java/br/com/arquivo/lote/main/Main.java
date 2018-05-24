package br.com.arquivo.lote.main;

import java.io.IOException;

import br.com.arquivo.lote.motor.MotorArquivoLote;

/**
 * 
 * Classe main para executar o sistema
 * 
 * @author andre.lermen
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {

		// executa o sistema
		new MotorArquivoLote().init();

	}

}
