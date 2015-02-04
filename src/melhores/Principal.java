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

public class Principal {

	private static final String MELHORES_CONSOLIDADO_CSV = "arquivos/consolidado.csv";

	public static void main(String[] args) {
		List<Empresa> BRGrandes = processar(ListaEnum.BR_GRANDES);
		List<Empresa> BRMediasMultinational = processar(ListaEnum.BR_MEDIAS_MULTINACIONAL);
		//List<Empresa> BRMediasNacional = processar(ListaEnum.BR_MEDIAS_NACIONAL);
		List<Empresa> RSGrandes = processar(ListaEnum.RS_GRANDES);
		//List<Empresa> RSMedias = processar(ListaEnum.RS_MEDIAS);
		List<Empresa> BRTI = processar(ListaEnum.BR_TI);

		Map<String, Empresa> empresas = new HashMap<String, Empresa>();
		consolidar(empresas, BRGrandes);
		consolidar(empresas, BRMediasMultinational);
		//consolidar(empresas, BRMediasNacional);
		consolidar(empresas, RSGrandes);
		//consolidar(empresas, RSMedias);
		consolidar(empresas, BRTI);
		
		escrever(empresas, MELHORES_CONSOLIDADO_CSV);
	}

	private static void consolidar(Map<String, Empresa> empresas, List<Empresa> melhores) {
		for (Empresa empresa : melhores) {
			Empresa empresaExistente = empresas.get(empresa.getSite());
			if (empresaExistente == null) { // ou seja, nao existe
				empresas.put(empresa.getSite(), empresa);
			} else {
				empresaExistente.addVoto();
				empresaExistente.addListas(empresa.getListas());
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
		//int contador = 0;
		Empresa empresa = new Empresa();
		try {
			String readLine = null;
			while ((readLine = bufferedReader.readLine()) != null) {
				if (linha == 6) {
					empresa.addLista(lista);
					empresas.add(empresa);
					empresa = new Empresa();
					linha = 0;
				}
				switch (linha) {
				case 0:					
					String[] split = readLine.split("\\.");
					if (split.length > 1){
						String colocacao = split[0].trim();
						empresa.addColocacao(colocacao);
						
						String nome = split[1].trim();
						empresa.setNome(nome);
					} else {
						String nome = split[0].trim();
						empresa.setNome(nome);
					}
					break;
				case 1:
					String empregados = empregados(readLine);
					empresa.setEmpregados(empregados);
					break;
				case 2:
					empresa.setSite(readLine);
					break;
				case 3:
					String industria = industria(readLine);
					empresa.setIndustria(industria);
					break;
				case 4:
					String propriedade = propriedade(readLine);
					empresa.setPropriedade(propriedade);
					break;
				}
				linha += 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return empresas;
	}

	private static void escrever(Map<String, Empresa> empresas, String arquivoDestino) {
		StringBuilder stringBuilder = new StringBuilder();
		String cabecalho = "colocacoes;listas;voto;10 melhores;20 melhores;30 melhores;empresa;site;numero funcionarios;industria;propriedade\n";
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

	private static String propriedade(String readLine) {
		return readLine.replace("propriedade: ", "");
	}

	private static String industria(String readLine) {
		return readLine.replace("Indústrias: ", "");
	}

	private static String empregados(String readLine) {
		return readLine.replace(" empregados", "");
	}
}