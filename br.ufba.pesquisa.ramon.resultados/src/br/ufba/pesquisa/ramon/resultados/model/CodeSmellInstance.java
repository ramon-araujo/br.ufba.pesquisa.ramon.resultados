package br.ufba.pesquisa.ramon.resultados.model;

public class CodeSmellInstance {
	
	private TipoCodeSmellEnum tipo;
	private String item;
	
	public CodeSmellInstance(TipoCodeSmellEnum tipo, String item) {
		this.tipo = tipo;
		this.item = item;
	}
	
	public String getItem() {
		return item;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public TipoCodeSmellEnum getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoCodeSmellEnum tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "(" + tipo + ", " + item + ")";
	}

}
