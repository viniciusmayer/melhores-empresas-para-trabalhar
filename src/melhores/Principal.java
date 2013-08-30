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

	private static final String MELHORES_TXT = "arquivos/melhores.txt";
	private static final String MELHORES_TI_TELECOM_TXT = "arquivos/melhores-tiTelecom.txt";
	private static final String MELHORES_PEQUENAS_MEDIAS_MULTINACIONAIS_TXT = "arquivos/melhores-pequenasMediasMultinacionais.txt";
	private static final String MELHORES_PEQUENAS_MEDIAS_NACIONAL_TXT = "arquivos/melhores-pequenasMediasNacional.txt";

	public static void main(String[] args) {
		List<Empresa> melhores = processar(MELHORES_TXT, ListaEnum.MELHORES);
		List<Empresa> melhoresTITelecom = processar(MELHORES_TI_TELECOM_TXT, ListaEnum.TI_TELECOM);
		List<Empresa> melhoresPequenasMediasMultinacionais = processar(MELHORES_PEQUENAS_MEDIAS_MULTINACIONAIS_TXT,
				ListaEnum.PEMES_MULTINACIONAL);
		List<Empresa> melhoresPequenasMediasNacional = processar(MELHORES_PEQUENAS_MEDIAS_NACIONAL_TXT,
				ListaEnum.PEMES_NACIONAL);

		Map<String, Empresa> empresas = new HashMap<String, Empresa>();
		consolidar(empresas, melhores);
		consolidar(empresas, melhoresTITelecom);
		consolidar(empresas, melhoresPequenasMediasMultinacionais);
		consolidar(empresas, melhoresPequenasMediasNacional);
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

	private static List<Empresa> processar(String arquivo, ListaEnum lista) {
		List<Empresa> empresas = new ArrayList<Empresa>();
		File file = new File(arquivo);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		int linha = 0;
		int contador = 0;
		Empresa empresa = new Empresa();
		try {
			String readLine = null;
			while ((readLine = bufferedReader.readLine()) != null) {
				if (linha == 5) {
					empresa.addLista(lista);
					empresas.add(empresa);
					empresa = new Empresa();
					linha = 0;
					if (lista == ListaEnum.MELHORES || lista == ListaEnum.PEMES_MULTINACIONAL || lista == ListaEnum.PEMES_NACIONAL) {
						continue;
					}
				}
				switch (linha) {
				case 0:
					if (lista == ListaEnum.TI_TELECOM) {
						String colocacao = readLine.substring(0, 2);
						colocacao = colocacao.replace(".", "");
						colocacao = colocacao.trim();
						empresa.addColocacao(colocacao);

						String nome = readLine.substring(2);
						nome = nome.replace(".", "");
						nome = nome.trim();
						empresa.setNome(nome);
					} else if (lista == ListaEnum.MELHORES) {
						if (contador < 20) {
							String colocacao = readLine.substring(0, 2);
							colocacao = colocacao.replace(".", "");
							colocacao = colocacao.trim();
							empresa.addColocacao(colocacao);

							String nome = readLine.substring(2);
							nome = nome.replace(".", "");
							nome = nome.trim();
							empresa.setNome(nome);
						} else {
							empresa.setNome(readLine);
						}
					} else {
						if (contador < 10) {
							String colocacao = readLine.substring(0, 2);
							colocacao = colocacao.replace(".", "");
							colocacao = colocacao.trim();
							empresa.addColocacao(colocacao);

							String nome = readLine.substring(2);
							nome = nome.replace(".", "");
							nome = nome.trim();
							empresa.setNome(nome);
						} else {
							empresa.setNome(readLine);
						}
					}
					contador += 1;
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