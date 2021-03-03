package br.ufba.pesquisa.ramon.resultados;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.ufba.pesquisa.ramon.resultados.model.CodeSmellInstance;
import br.ufba.pesquisa.ramon.resultados.model.Resultado;
import br.ufba.pesquisa.ramon.resultados.model.TipoFerramentaEnum;

public abstract class AbstractColetorResultados {

	private String pathPastaArquivos;
	private TipoFerramentaEnum tipoFerramenta;
	private List<Resultado> resultados = new ArrayList<Resultado>();
	
	public AbstractColetorResultados(String pathPastaArquivos, TipoFerramentaEnum tipoFerramenta) {
		this.pathPastaArquivos = pathPastaArquivos;
		this.tipoFerramenta = tipoFerramenta;
	}
	
	public void processarColetorDeResultados() {
		
		File folder = new File(pathPastaArquivos);
		
		File[] listaDeArquivos = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String fileName) {
				return fileName.endsWith("." + tipoFerramenta.getExtensaoArquivos());
			}
		});
		
		for (File file : listaDeArquivos) {
			adicionarResultado(getNomeProjeto(file), extrairCodeSmells(file));
		}
		
		for (Resultado result : resultados) {
			result.sumarizarResultado();
		}
		
	}
	
	protected abstract List<CodeSmellInstance> extrairCodeSmells(File file);
	
	protected String getNomeProjeto(File file) {
		return file.getName().replaceFirst("[.][^.]+$", "");
	}
	
	private void adicionarResultado(String nomeProjeto, List<CodeSmellInstance> codeSmells) {
		Resultado resultadoEncontrado = null;
		for (Resultado resultado : this.resultados) {
			if (resultado.getProjeto().equals(nomeProjeto)) {
				resultadoEncontrado = resultado;
				break;
			}
		}
		
		if (Objects.nonNull(resultadoEncontrado)) {
			resultadoEncontrado.getListaDeSmells().addAll(codeSmells);
		} else {
			Resultado resultado = new Resultado();
			resultado.setFerramenta(this.tipoFerramenta);
			resultado.setProjeto(nomeProjeto);
			resultado.setListaDeSmells(codeSmells);
			
			resultados.add(resultado);
		}
	}
}
