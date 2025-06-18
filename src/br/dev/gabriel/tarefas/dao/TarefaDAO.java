package br.dev.gabriel.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.dev.gabriel.tarefas.factory.ArquivoTarefaFactory;
import br.dev.gabriel.tarefas.model.Funcionario;
import br.dev.gabriel.tarefas.model.Status;
import br.dev.gabriel.tarefas.model.Tarefa;

public class TarefaDAO {
    private Tarefa tarefa;

    public TarefaDAO(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public boolean gravar() {
        ArquivoTarefaFactory atf = new ArquivoTarefaFactory();
        try (BufferedWriter bw = atf.getBufferedWriter(true)) {
            bw.write(tarefa.toString());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Tarefa> getTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        ArquivoTarefaFactory atf = new ArquivoTarefaFactory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader br = atf.getBufferedReader()) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    String[] tarefaVetor = linha.split(",");
                    if (tarefaVetor.length < 7)
                        continue;

                    Funcionario responsavel = new Funcionario(tarefaVetor[2]);
                    Tarefa tarefa = new Tarefa(responsavel);
                    tarefa.setNome(tarefaVetor[0]);
                    tarefa.setDescricao(tarefaVetor[1]);

                    if (!tarefaVetor[3].equals("sem-data-inicio")) {
                        tarefa.setDataInicio(LocalDate.parse(tarefaVetor[3], formatter));
                    }

                    tarefa.setPrazo(Integer.parseInt(tarefaVetor[4]));

                    tarefa.setStatus(Status.valueOf(tarefaVetor[6]));

                    tarefas.add(tarefa);
                }
            }
            return tarefas;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}