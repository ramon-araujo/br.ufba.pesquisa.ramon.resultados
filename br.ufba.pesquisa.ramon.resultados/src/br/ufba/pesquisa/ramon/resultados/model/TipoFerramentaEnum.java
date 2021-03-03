package br.ufba.pesquisa.ramon.resultados.model;

public enum TipoFerramentaEnum {
	
	JSPIRIT("JSpirit", "jspirit"),
	PMD("PMD", "pmd");
	
	private String nome;
	private String extensaoArquivos;
	
	private TipoFerramentaEnum(String nomeFerramenta, String extensaoArquivos) {
		this.nome = nomeFerramenta;
		this.extensaoArquivos = extensaoArquivos;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getExtensaoArquivos() {
		return extensaoArquivos;
	}
}
