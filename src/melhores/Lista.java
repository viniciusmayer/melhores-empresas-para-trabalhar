package melhores;

public class Lista {

	private ListaEnum lista;
	private Integer colocacao;

	public Lista(ListaEnum lista, Integer colocacao) {
		this.lista = lista;
		this.colocacao = (colocacao != null)?new Integer(colocacao):null;
	}

	public ListaEnum getLista() {
		return lista;
	}

	public Integer getColocacao() {
		return colocacao;
	}
}