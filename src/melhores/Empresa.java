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
	private Boolean entreAs10Melhores;
	private Boolean entreAs20Melhores;
	private Boolean entreAs30Melhores;

	public Empresa() {
		this.colocacoes = new ArrayList<>();
		this.votos = 1;
		this.listas = new ArrayList<>();
		this.entreAs10Melhores = false;
		this.entreAs20Melhores = false;
		this.entreAs30Melhores = false;
	}

	public Boolean getEntreAs20Melhores() {
		return entreAs20Melhores;
	}

	public void setEntreAs20Melhores(Boolean entreAs20Melhores) {
		this.entreAs20Melhores = entreAs20Melhores;
	}

	public Boolean getEntreAs30Melhores() {
		return entreAs30Melhores;
	}

	public void setEntreAs30Melhores(Boolean entreAs30Melhores) {
		this.entreAs30Melhores = entreAs30Melhores;
	}

	public Boolean getEntreAs10Melhores() {
		return entreAs10Melhores;
	}

	public void setEntreAs10Melhores(Boolean entreAs10Melhores) {
		this.entreAs10Melhores = entreAs10Melhores;
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
		if (new Integer(colocacao) < 11){
			this.entreAs10Melhores = true;
		} else if (new Integer(colocacao) < 21){
			this.entreAs20Melhores = true;
		} else if (new Integer(colocacao) < 31){
			this.entreAs30Melhores = true;
		}
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
		stringBuilder.append(this.votos);
		stringBuilder.append(";");
		stringBuilder.append((this.entreAs10Melhores)?"Sim":"Nao");
		stringBuilder.append(";");
		stringBuilder.append((this.entreAs20Melhores)?"Sim":"Nao");
		stringBuilder.append(";");
		stringBuilder.append((this.entreAs30Melhores)?"Sim":"Nao");
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
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
}