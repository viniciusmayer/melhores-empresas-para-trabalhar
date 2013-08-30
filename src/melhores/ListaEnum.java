package melhores;

/*
	private static final int MELHORES = 0;
	private static final int TI_TELECOM = 1;
	private static final int PEMES_MULTINACIONAIS = 2;
	private static final int PEMES_NACIONAL = 2;
 */
public enum ListaEnum {

	MELHORES("Melhores"),
	TI_TELECOM("TI e Telecom"),
	PEMES_MULTINACIONAL("PMs Multinacional"),
	PEMES_NACIONAL("PMs Nacional");

	private String nome;

	private ListaEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
