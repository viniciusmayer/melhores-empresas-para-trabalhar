package melhores;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private List<String> colocacoes;
	private List<ListaEnum> listas;
	private String nome;
	private String site;
	private String empregados;
	private String industria;
	private String propriedade;
	private Integer votos;

	public Empresa() {
		this.colocacoes = new ArrayList<>();
		this.votos = 0;
		this.listas = new ArrayList<>();
	}

	public List<String> getColocacoes() {
		return colocacoes;
	}

	public void setColocacoes(List<String> colocacoes) {
		this.colocacoes = colocacoes;
	}
	
	public List<ListaEnum> getListas() {
		return listas;
	}

	public void setListas(List<ListaEnum> listas) {
		this.listas = listas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getEmpregados() {
		return empregados;
	}

	public void setEmpregados(String empregados) {
		this.empregados = empregados;
	}

	public String getIndustria() {
		return industria;
	}

	public void setIndustria(String industria) {
		this.industria = industria;
	}

	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	public Integer getVotos() {
		return votos;
	}

	public void setVotos(Integer votos) {
		this.votos = votos;
	}

	public void addColocacao(String colocacao) {
		this.colocacoes.add(colocacao);
	}
	
	public void addListas(List<ListaEnum> listas) {
		for (ListaEnum lista : listas) {
			if (!this.listas.contains(lista)){
				this.listas.add(lista);
			}
		}
	}
	
	public void addLista(ListaEnum lista) {
		if (!this.listas.contains(lista)){
			this.listas.add(lista);
		}
	}
	
	public void addVoto() {
		this.votos += 1;
	}

	public String imprimir() {
		StringBuilder stringBuilder = new StringBuilder();
		boolean b = false;
		for (String colocacao : this.colocacoes) {
			if (b) {
				stringBuilder.append(",");
			}
			b = true;
			stringBuilder.append(colocacao);
		}
		stringBuilder.append(";");
		b = false;
		for (ListaEnum lista : this.listas) {
			if (b) {
				stringBuilder.append(",");
			}
			b = true;
			stringBuilder.append(lista.getNome());
		}
		stringBuilder.append(";");
		stringBuilder.append(this.nome);
		stringBuilder.append(";");
		stringBuilder.append(this.site);
		stringBuilder.append(";");
		stringBuilder.append(this.empregados);
		stringBuilder.append(";");
		stringBuilder.append(this.industria);
		stringBuilder.append(";");
		stringBuilder.append(this.propriedade);
		stringBuilder.append(";");
		stringBuilder.append(this.votos);
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
}