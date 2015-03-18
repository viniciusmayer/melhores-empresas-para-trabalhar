package melhores;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private Integer melhorColocacao;
	private List<Lista> listas;
	private String nome;
	private String site;
	private String empregados;
	private String industria;
	private String detalhe;
	private String propriedade;
	private Integer pontos;
	private List<String> paises;

	public Empresa() {
		this.melhorColocacao = null;
		this.listas = new ArrayList<>();
		this.nome = null;
		this.site = null;
		this.empregados = null;
		this.industria = null;
		this.detalhe = null;
		this.propriedade = null;
		this.pontos = 0;
		this.paises = new ArrayList<String>();
	}
	
	
	/*
	 * GET
	 */
	public List<String> getPaises() {
		return paises;
	}

	public Integer getMelhorColocacao() {
		return melhorColocacao;
	}

	public String getIndustria() {
		return industria;
	}

	public List<Lista> getListas() {
		return listas;
	}

	public String getSite() {
		return site;
	}

	/*
	 * EXTRACT
	 */
	public Integer getColocacao(String readLine) {
		Integer colocacao = null;
		String[] split = readLine.split("\\.");
		if (split.length > 1){
			colocacao = new Integer(split[0].trim());
		}
		return colocacao;
	}

	/*
	 * SET
	 */
	public void setNome(String readLine) {
		String[] split = readLine.split("\\.");
		if (split.length > 1){
			this.nome = split[1].trim();
		} else {
			this.nome = split[0].trim();
		}
	}

	public void setSite(String site) {
		String[] split = site.split("\\//");
		if (split.length > 1){
			site = split[1];
		}
		split = site.split("\\/");
		if (split.length > 1){
			site = split[0];
		}
		this.site = site.trim();
	}

	public void setEmpregados(String empregados) {
		this.empregados = empregados.replace("empregados", "").trim();
	}

	public void setIndustria(String industria, Boolean padrao) {
		if (padrao){
			industria = industria.replace("Indústrias:", "");
			String[] split = industria.split("\\//");
			this.industria = split[0].trim();
			if (split.length > 1){
				this.detalhe = split[1].trim();
			}
		} else {
			industria = industria.replace("Setor:", "");
			String[] split = industria.split("\\|");
			this.industria = split[0].trim();
			if (split.length > 1){
				this.detalhe = split[1].trim();
			}
		}
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade.replace("propriedade:", "").trim();
	}

	public void setMelhorColocacao(Integer melhorColocacao) {
		this.melhorColocacao = melhorColocacao;
	}

	/*
	 * ADD
	 */
	public void addListas(List<Lista> listas) {
		for (Lista lista : listas){
			this.addLista(lista.getLista(), lista.getColocacao());
		}
	}
	
	public void addLista(ListaEnum lista, Integer colocacao){
		if (!this.listas.contains(lista)){
			this.listas.add(new Lista(lista, colocacao));
		}
	}
	
	public void addPonto(Integer i) {
		this.pontos += i;
	}

	public void addPaises(String readLine) {
		readLine = readLine.replace("Listado em:", "").trim();
		String[] split = readLine.split(",");
		for (int i = 0; i < split.length; i+=1){
			this.paises.add(split[i].trim());
		}
	}
	
	public void addPaises(List<String> paises){
		for (String pais : paises){
			if (!this.paises.contains(pais)){
				this.paises.add(pais);
			}
		}
	}

	/*
	 * EXTRA
	 */
	public String imprimir() {
		StringBuilder stringBuilder = new StringBuilder();
		boolean b = false;
		stringBuilder.append((this.melhorColocacao != null)?this.melhorColocacao:"");
		stringBuilder.append(";");
		b = false;
		for (Lista lista : this.listas) {
			if (b) {
				stringBuilder.append(", ");
			}
			b = true;
			stringBuilder.append(lista.getLista().getNome());
			if (lista.getColocacao() != null){
				stringBuilder.append(" [");
				stringBuilder.append(lista.getColocacao());
				stringBuilder.append("]");
			}
		}
		stringBuilder.append(";");
		stringBuilder.append(this.pontos);
		stringBuilder.append(";");
		stringBuilder.append((this.melhorColocacao != null && this.melhorColocacao < 11)?"Sim":"Nao");
		stringBuilder.append(";");
		stringBuilder.append((this.melhorColocacao != null && this.melhorColocacao < 21)?"Sim":"Nao");
		stringBuilder.append(";");
		stringBuilder.append((this.melhorColocacao != null && this.melhorColocacao < 31)?"Sim":"Nao");
		stringBuilder.append(";");
		stringBuilder.append(this.nome);
		stringBuilder.append(";");
		stringBuilder.append(this.site);
		stringBuilder.append(";");
		stringBuilder.append((this.empregados != null)?this.empregados:"");
		stringBuilder.append(";");
		stringBuilder.append(this.industria);
		stringBuilder.append(";");
		stringBuilder.append((this.detalhe != null)?this.detalhe:"");
		stringBuilder.append(";");
		stringBuilder.append((this.propriedade != null)?this.propriedade:"");
		stringBuilder.append(";");
		b = false;
		for (String pais : this.paises) {
			if (b) {
				stringBuilder.append(", ");
			}
			b = true;
			stringBuilder.append(pais);
		}
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
}