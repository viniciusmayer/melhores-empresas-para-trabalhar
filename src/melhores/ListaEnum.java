package melhores;

public enum ListaEnum {

	BR_GRANDES				(2, "Brasil-Grande",				"arquivos/2014GPTW-Brasil-Grandes.txt",					true),
	BR_MEDIAS_MULTINACIONAL	(3, "Brasil-Media-Multinacional",	"arquivos/2014GPTW-Brasil-MediasMultinacionais.txt",	true),
	BR_MEDIAS_NACIONAL		(1, "Brasil-Media-Nacional",		"arquivos/2014GPTW-Brasil-MediasNacionais.txt",			true),
	RS_GRANDES				(2, "RS-Grande",					"arquivos/2014GPTW-RS-Grandes.txt",						true),
	RS_MEDIAS				(1, "RS-Media",						"arquivos/2014GPTW-RS-Medias.txt",						true),
	BR_TI					(2, "Brasil-TI",					"arquivos/2014GPTW-Brasil-TI.txt",						true),
	MUNDO_MULTINACIONAL		(3, "Mundo-Multinacional",			"arquivos/2014GPTW-Mundo-Multinacionais.txt",			false);

	private Integer pontos;
	private String nome;
	private String arquivo;
	private Boolean layoutDefault;

	private ListaEnum(Integer pontos, String nome, String arquivo, Boolean layoutDefault) {
		this.pontos = pontos;
		this.nome = nome;
		this.arquivo = arquivo;
		this.layoutDefault = layoutDefault;
	}
	
	public Boolean getLayoutDefault() {
		return layoutDefault;
	}

	public String getArquivo() {
		return arquivo;
	}

	public String getNome() {
		return nome;
	}

	public Integer getPontos() {
		return this.pontos;
	}

}