package br.dev.gabriel.tarefas;

import java.util.List;

import br.dev.gabriel.tarefas.dao.TarefaDAO;
import br.dev.gabriel.tarefas.model.Funcionario;
import br.dev.gabriel.tarefas.model.Tarefa;

public class Main {

    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario("Lord Destruidor", "Destruidor de Mundos");
        funcionario.setSetor("Galaxia Sul");
        funcionario.setSalario(9987.98);

        Tarefa novaTarefa = new Tarefa(funcionario);
        novaTarefa.setNome("Destruir Júpiter");
        novaTarefa.setDescricao("Vá até Júpiter e destrua-o com um ataque de laser e faça seus habitantes sofrerem.");
        novaTarefa.setDataInicio("15/06/2025");
        novaTarefa.setPrazo(10);

        TarefaDAO tarefaDAO = new TarefaDAO(novaTarefa);
        if (tarefaDAO.gravar()) {
            System.out.println("Tarefa gravada:");
            System.out.println(novaTarefa);
        } else {
            System.out.println("Erro ao gravar tarefa.");
        }

        System.out.println("\nTarefas cadastradas:");
        List<Tarefa> tarefas = tarefaDAO.getTarefas();

        if (tarefas != null && !tarefas.isEmpty()) {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        } else {
            System.out.println("Nenhuma tarefa.");
        }
    }
}