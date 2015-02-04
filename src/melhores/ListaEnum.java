package melhores;

public enum ListaEnum {

	BR_GRANDES("Brasil-Grande", "arquivos/2014GPTW-Brasil-Grandes.txt", 20),
	BR_MEDIAS_MULTINACIONAL("Brasil-Media-Multinacional", "arquivos/2014GPTW-Brasil-MediasMultinacionais.txt", 10),
	BR_MEDIAS_NACIONAL("Brasil-Media-Nacional", "arquivos/2014GPTW-Brasil-MediasNacionais.txt", 10),
	RS_GRANDES("RS-Grande", "arquivos/2014GPTW-RS-Grandes.txt", 9),
	RS_MEDIAS("RS-Media", "arquivos/2014GPTW-RS-Medias.txt", 31),
	BR_TI("Brasil-TI", "arquivos/2014GPTW-Brasil-TI.txt", 100);

	private String nome;
	private String arquivo;
	private Integer colocacoes;

	private ListaEnum(String nome, String arquivo, Integer colocacoes) {
		this.nome = nome;
		this.arquivo = arquivo;
		this.colocacoes = colocacoes;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getColocacoes() {
		return colocacoes;
	}

	public void setColocacoes(Integer colocacoes) {
		this.colocacoes = colocacoes;
	}
}