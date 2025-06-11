package br.dev.gabriel.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.dev.gabriel.tarefas.factory.ArquivoFuncionarioFactory;
import br.dev.gabriel.tarefas.model.Funcionario;

public class FuncionarioDAO {
	private Funcionario funcionario;
	
	public FuncionarioDAO(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public boolean gravar() {
		ArquivoFuncionarioFactory aff = new ArquivoFuncionarioFactory();
		try {
			BufferedWriter bw =  aff.getBw();
			bw.write(funcionario.toString());
			bw.flush();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public List<Funcionario> getFuncionarios() {
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		ArquivoFuncionarioFactory aff = new ArquivoFuncionarioFactory();
		
		try {
			BufferedReader br = aff.getBr();
			
			String linha = br.readLine();
			
			while (linha != null) {
				linha = br.readLine();
				if(linha != null) {
					String[] funcionarioVetor = linha.split(",");
					Funcionario funcionario = new Funcionario(null);
					funcionario.setMatricula(funcionarioVetor[0]);
					funcionario.setNome(funcionarioVetor[1]);
					funcionario.setCargo(funcionarioVetor[2]);
					funcionario.setSetor(funcionarioVetor[3]);
					funcionario.setSalario(Double.parseDouble(funcionarioVetor[4]));
					funcionarios.add(funcionario);
				}
			}
			return funcionarios;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}