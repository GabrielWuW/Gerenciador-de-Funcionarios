package br.dev.gabriel.tarefas;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import br.dev.gabriel.tarefas.model.Funcionario;
import br.dev.gabriel.tarefas.model.Tarefa;
import br.dev.gabriel.tarefas.ui.FuncionarioListaFrame;
import br.dev.gabriel.tarefas.ui.GerenciamentoFrame;

public class Main {

	public static void main(String[] args) {
		// Funcionario funcionario = new Funcionario("Maria", "Gerente");
		// funcionario.setSetor("Tecnologia da Informação");
		// funcionario.setSalario(9987.98);
		//
		// FuncionarioDAO dao = new FuncionarioDAO(funcionario);
		// dao.gravar();

		// new FuncionarioFrame();

		// new FuncionarioListaFrame()
		// new GerenciamentoFrame();

		Funcionario funcionario = new Funcionario("Destruidor de planetas");
		Tarefa tarefa = new Tarefa(funcionario);
		tarefa.setNome("Destruir Jupiter");
		tarefa.setDescricao("Vá até Júpiter e destrua-o com um ataque de laser, e faça seus habitantes sofrerem.");

		tarefa.setDataInicio("15/06/2025");

		tarefa.setPrazo(10);

		System.out.println(tarefa);
	}
}