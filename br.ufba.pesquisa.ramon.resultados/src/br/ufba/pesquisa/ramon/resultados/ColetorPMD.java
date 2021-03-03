package br.ufba.pesquisa.ramon.resultados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import br.ufba.pesquisa.ramon.resultados.model.CodeSmellInstance;
import br.ufba.pesquisa.ramon.resultados.model.TipoCodeSmellEnum;
import br.ufba.pesquisa.ramon.resultados.model.TipoFerramentaEnum;

public class ColetorPMD extends AbstractColetorResultados {

	public ColetorPMD(String pathPastaArquivos) {
		super(pathPastaArquivos, TipoFerramentaEnum.PMD);
	}
	
	
	@Override
	protected List<CodeSmellInstance> extrairCodeSmells(File file) {
		List<CodeSmellInstance> codeSmells = new ArrayList<CodeSmellInstance>();
		
		try (FileReader reader = new FileReader(file)) {
			BufferedReader br = new BufferedReader(reader);
			
			String line;
            while ((line = br.readLine()) != null) {
            	StringTokenizer st = new StringTokenizer(line, "\t");

            	if (st.hasMoreElements()) {
            		
            		String item = st.nextToken();
            		int inicioNomeClasse = item.lastIndexOf("\\") + 1;
            		int fimNomeClasse = item.indexOf(".java");
            		String nomeClasse = item.substring(inicioNomeClasse, fimNomeClasse);
            		
            		TipoCodeSmellEnum tipoSmell = convertToType(st.nextToken());
            		if (Objects.nonNull(tipoSmell)) {
            			codeSmells.add(new CodeSmellInstance(tipoSmell, nomeClasse));
            		}
            	}
            }
		} catch (IOException e) {
			System.out.println("Erro ao ler aquivo.");
			e.printStackTrace();
		}
		return codeSmells;
	}
	
	private TipoCodeSmellEnum convertToType(String tipo) {
		
		if (tipo.contains("God Class")) {
			return TipoCodeSmellEnum.GOD_CLASS;
		}
		
		if (tipo.contains("Avoid really long classes")) {
			return TipoCodeSmellEnum.LARGE_CLASS;
		}
		
		if (tipo.contains("Data Class")) {
			return TipoCodeSmellEnum.DATA_CLASS;
		}
		
		if (tipo.contains("long parameter")) {
			return TipoCodeSmellEnum.LONG_PARAMETER_LIST;
		}
		
		return null;
	}

}
