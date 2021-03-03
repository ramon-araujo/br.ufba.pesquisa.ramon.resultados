package br.ufba.pesquisa.ramon.resultados.model;

import java.util.List;
import java.util.stream.Collectors;

public class Resultado {

	private TipoFerramentaEnum ferramenta;
	private String projeto;
	private List<CodeSmellInstance> listaDeSmells;
	
	public TipoFerramentaEnum getFerramenta() {
		return ferramenta;
	}
	
	public void setFerramenta(TipoFerramentaEnum ferramenta) {
		this.ferramenta = ferramenta;
	}
	
	public String getProjeto() {
		return projeto;
	}
	
	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}
	
	public List<CodeSmellInstance> getListaDeSmells() {
		return listaDeSmells;
	}
	
	public void setListaDeSmells(List<CodeSmellInstance> listaDeSmells) {
		this.listaDeSmells = listaDeSmells;
	}
	
	private Integer contarQuantidadeDeSmells(TipoCodeSmellEnum tipo) {
		return listaDeSmells.stream().filter(smell -> tipo.equals(smell.getTipo())).collect(Collectors.toList()).size();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ferramenta.getNome());
		sb.append(" - " + projeto);
		for (CodeSmellInstance codeSmellInstance : listaDeSmells) {
			sb.append(codeSmellInstance);
		}
		return sb.toString();
	}

	public void sumarizarResultado() {
		System.out.println(ferramenta.getNome() + " - " + projeto);
		System.out.println("	Total de smells: " + listaDeSmells.size());
		for (TipoCodeSmellEnum tipo : TipoCodeSmellEnum.values()) {
			System.out.println("		" + tipo.getNome() + ": " + contarQuantidadeDeSmells(tipo));
		}
		
		for (CodeSmellInstance codeSmellInstance : listaDeSmells) {
			System.out.println(codeSmellInstance.getItem() + " - " + codeSmellInstance.getTipo());
		}
	}
}
