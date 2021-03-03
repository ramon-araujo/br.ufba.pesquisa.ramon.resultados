package br.ufba.pesquisa.ramon.resultados;

import java.util.Objects;

public class Main {

	public static void main(String[] args) {

		if (Objects.nonNull(args) && args.length >0) {
			String diretorio = args[0];
			
			if (Objects.nonNull(diretorio) && !diretorio.isEmpty()) {
				
				ColetorJSpirit coletorJSpirit = new ColetorJSpirit(diretorio);
				coletorJSpirit.processarColetorDeResultados();
				
				ColetorPMD coletorPMD = new ColetorPMD(diretorio);
				coletorPMD.processarColetorDeResultados();
			}
		}
	}

}
