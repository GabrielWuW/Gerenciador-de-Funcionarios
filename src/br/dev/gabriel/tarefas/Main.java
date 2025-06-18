package br.dev.gabriel.tarefas;


import br.dev.gabriel.tarefas.model.Funcionario;
import br.dev.gabriel.tarefas.model.Tarefa;


public class Main {
	public static void main(String[] args) {

		Funcionario funcionario = new Funcionario("Destruidor de planetas");
		Tarefa tarefa = new Tarefa(funcionario);
		tarefa.setNome("Destruir Jupiter");
		tarefa.setDescricao("Vá até Júpiter e destrua-o com um ataque de laser, e faça seus habitantes sofrerem.");

		tarefa.setDataInicio("15/06/2025");

		tarefa.setPrazo(10);

		System.out.println(tarefa);
	}
}