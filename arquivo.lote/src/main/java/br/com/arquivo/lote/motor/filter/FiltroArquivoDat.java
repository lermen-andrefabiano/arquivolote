package br.com.arquivo.lote.motor.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 * Classe modelo para filtrar os arquivos com extenção .dat
 * 
 * @author andre.lermen
 *
 */
public class FiltroArquivoDat implements FilenameFilter {

	private static final String DAT = ".dat";

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(DAT);
	}

}
