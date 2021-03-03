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

public class ColetorJSpirit extends AbstractColetorResultados {

	public ColetorJSpirit(String pathPastaArquivos) {
		super(pathPastaArquivos, TipoFerramentaEnum.JSPIRIT);
	}

	@Override
	public List<CodeSmellInstance> extrairCodeSmells(File file) {
		
		List<CodeSmellInstance> codeSmells = new ArrayList<CodeSmellInstance>();
		
		try (FileReader reader = new FileReader(file)) {
			
			BufferedReader br = new BufferedReader(reader);
			
			
			String line;
            while ((line = br.readLine()) != null) {
            	StringTokenizer st = new StringTokenizer(line, "\t");

            	if (st.hasMoreElements()) {
            		TipoCodeSmellEnum tipoSmell = convertToType(st.nextToken());
            		if (Objects.nonNull(tipoSmell)) {
            			codeSmells.add(new CodeSmellInstance(tipoSmell, st.nextToken()));
            		}
            	}
            }
		
		}  catch (IOException e) {
			System.out.println("Erro ao ler aquivo.");
			e.printStackTrace();
		}
		
		return codeSmells;
	}

	@Override
	protected String getNomeProjeto(File file) {
		return file.getName().replaceFirst("[.][^.]+$", "");
	}

	private TipoCodeSmellEnum convertToType(String tipo) {
		switch (tipo) {

		case "God Class":
			return TipoCodeSmellEnum.GOD_CLASS;

		case "Shotgun Surgery":
			return TipoCodeSmellEnum.SHOTGUN_SURGERY;
			
		case "Data Class":
			return TipoCodeSmellEnum.DATA_CLASS;
		
		case "Tradition Breaker":
			return TipoCodeSmellEnum.TRADITION_BREAKER;	
		
		default:
			return null;
		}
			
	}
	
	
}
