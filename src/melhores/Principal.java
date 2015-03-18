package melhores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Principal {

	private static final String MELHORES_CONSOLIDADO_CSV = "arquivos/consolidado.csv";

	public static void main(String[] args) {
		List<Empresa> BRGrandes = processar(ListaEnum.BR_GRANDES);
		List<Empresa> BRMediasMultinational = processar(ListaEnum.BR_MEDIAS_MULTINACIONAL);
		List<Empresa> BRMediasNacional = processar(ListaEnum.BR_MEDIAS_NACIONAL);
		List<Empresa> RSGrandes = processar(ListaEnum.RS_GRANDES);
		List<Empresa> RSMedias = processar(ListaEnum.RS_MEDIAS);
		List<Empresa> BRTI = processar(ListaEnum.BR_TI);
		List<Empresa> mundoMultinacional = processar(ListaEnum.MUNDO_MULTINACIONAL);

		Map<String, Empresa> empresas = new HashMap<String, Empresa>();
		consolidar(empresas, BRGrandes);
		consolidar(empresas, BRMediasMultinational);
		consolidar(empresas, BRMediasNacional);
		consolidar(empresas, RSGrandes);
		consolidar(empresas, RSMedias);
		consolidar(empresas, BRTI);
		consolidar(empresas, mundoMultinacional);
		
		calcularPontuacao(empresas);
		
		escrever(empresas, MELHORES_CONSOLIDADO_CSV);
	}

	private static void calcularPontuacao(Map<String, Empresa> empresas) {
		Set<String> keySet = empresas.keySet();
		for(String key : keySet){
			Empresa empresa = empresas.get(key);
			
			for(Lista lista : empresa.getListas()){
				empresa.addPonto(lista.getLista().getPontos());
				
				Integer colocacao = lista.getColocacao();
				if (colocacao != null){
					if (colocacao < 11){
						empresa.addPonto(3);
					} else if (colocacao < 21){
						empresa.addPonto(2);
					} else if (colocacao < 31){
						empresa.addPonto(1);
					}
					
					Integer melhorColocacao = empresa.getMelhorColocacao();
					if (melhorColocacao == null){
						empresa.setMelhorColocacao(colocacao);
					} else if (colocacao < melhorColocacao){
						empresa.setMelhorColocacao(colocacao);
					}					
				}
			}
		}
	}

	private static void consolidar(Map<String, Empresa> empresas, List<Empresa> melhores) {
		for (Empresa empresa : melhores) {
			Empresa empresaExistente = null;
			String site = empresa.getSite();
			
			Set<String> keySet = empresas.keySet();
			for (String key : keySet){
				if (key.contains(site) || site.contains(key)){
					empresaExistente = empresas.get(site);
				}
			}
			if (empresaExistente == null) { // ou seja, nao existe
				empresas.put(site, empresa);
			} else {
				empresaExistente.addListas(empresa.getListas());
				empresaExistente.addPaises(empresa.getPaises());
			}
		}
	}

	private static List<Empresa> processar(ListaEnum lista) {
		List<Empresa> empresas = new ArrayList<Empresa>();
		File file = new File(lista.getArquivo());
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		int linha = 0;
		Empresa empresa = new Empresa();
		try {
			String readLine = null;
			Integer colocacao = null;
			while ((readLine = bufferedReader.readLine()) != null) {
				if (linha == 5) {
					empresa.addLista(lista, colocacao);
					empresas.add(empresa);
					empresa = new Empresa();
					colocacao = null;
					linha = 0;
					continue;
				}
				if (lista.getLayoutDefault()){
					Integer i = processarLayoutPadrao(linha, empresa, readLine);
					if (colocacao == null){
						colocacao = i;
					}
				} else {
					Integer i = processarLayoutMundo(linha, empresa, readLine);
					if (colocacao == null){
						colocacao = i;
					}
				}
				linha += 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return empresas;
	}

	private static Integer processarLayoutPadrao(int linha, Empresa empresa, String readLine) {
		Integer colocacao = null;
		switch (linha) {
		case 0:
			colocacao = empresa.getColocacao(readLine);
			empresa.setNome(readLine);
			break;
		case 1:
			empresa.setEmpregados(readLine);
			break;
		case 2:
			empresa.setSite(readLine);
			break;
		case 3:
			empresa.setIndustria(readLine, true);
			break;
		case 4:
			empresa.setPropriedade(readLine);
			break;
		}
		return colocacao;
	}

	private static Integer processarLayoutMundo(int linha, Empresa empresa,
			String readLine) {
		Integer colocacao = null;
		switch (linha) {
		case 0:
			colocacao = empresa.getColocacao(readLine);
			break;
		case 1:
			empresa.setNome(readLine);
			break;
		case 2:
			empresa.setSite(readLine);
			break;
		case 3:
			empresa.setIndustria(readLine, false);
			break;
		case 4:
			empresa.addPaises(readLine);
			break;
		}
		return colocacao;
	}
	
	private static void escrever(Map<String, Empresa> empresas, String arquivoDestino) {
		StringBuilder stringBuilder = new StringBuilder();
		String cabecalho = "colocacao;listas;pontos;10+;20+;30+;empresa;site;funcionario;industria;detalhe;propriedade;paises\n";
		stringBuilder.append(cabecalho);
		for (Empresa empresa : empresas.values()) {
			stringBuilder.append(empresa.imprimir());
		}

		File file2 = new File(arquivoDestino);
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		try {
			bufferedWriter.write(stringBuilder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}