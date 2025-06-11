package br.dev.gabriel.tarefas;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import br.dev.gabriel.tarefas.ui.FuncionarioListaFrame;


public class Main {
	
	public static void main(String[] args) {
//		Funcionario funcionario = new Funcionario("Maria", "Gerente");
//		funcionario.setSetor("Tecnologia da Informação");
//		funcionario.setSalario(9987.98);
//		
//		FuncionarioDAO dao = new FuncionarioDAO(funcionario);
//		dao.gravar();
		
//		new FuncionarioFrame();
		
		new FuncionarioListaFrame();
		
	}

	private static void testarLeituraEscritaArquivo() {
		String so = System.getProperty("os.name");
		System.out.println(so);
		
		String caminho = "/Users/25132745/projetoTarefas/tarefas";
		FileReader fr = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fr = new FileReader(caminho);
			br = new BufferedReader(fr);
			
			fw = new FileWriter(caminho, true);
			bw = new BufferedWriter(fw);
			
			bw.append("Marcel\n");
			bw.flush();
			
			String linha = br.readLine();
			System.out.println(linha);
			linha = br.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = br.readLine();
				
			}
			
		} catch (FileNotFoundException erro) {
			System.out.println("Arquivo não encontrado!");
		} catch ( IOException erro) {
			System.out.println("Arquivo protegido para leitura");
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
	}

}