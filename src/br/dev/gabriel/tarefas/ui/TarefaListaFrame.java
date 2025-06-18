package br.dev.gabriel.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.dev.gabriel.tarefas.dao.TarefaDAO;
import br.dev.gabriel.tarefas.model.Tarefa;
import br.dev.gabriel.tarefas.model.Status;

public class TarefaListaFrame {

    private JLabel labelTitulo;
    private JButton btnNovaTarefa;
    private DefaultTableModel model;
    private JTable tabelaTarefas;
    private JScrollPane scrollTarefas;
    String[] colunas = {"NOME", "DESCRIÇÃO", "RESPONSÁVEL", "DATA INÍCIO", "PRAZO (DIAS)", "DATA ENTREGA", "STATUS"};

    public TarefaListaFrame() {
        criarTela();
    }

    private void criarTela() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame telaTarefaLista = new JFrame("Lista de Tarefas");
        telaTarefaLista.setSize(900, 500);
        telaTarefaLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaTarefaLista.setLayout(null);
        telaTarefaLista.setLocationRelativeTo(null);
        telaTarefaLista.setResizable(false);
        Container painel = telaTarefaLista.getContentPane();

        labelTitulo = new JLabel("Lista de Tarefas");
        labelTitulo.setBounds(10, 10, 500, 40);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        labelTitulo.setForeground(Color.magenta);

        model = new DefaultTableModel(colunas, 0);
        tabelaTarefas = new JTable(model);
        scrollTarefas = new JScrollPane(tabelaTarefas);
        scrollTarefas.setBounds(10, 70, 870, 300);

        carregarDadosTabela();

        btnNovaTarefa = new JButton("Cadastrar nova tarefa");
        btnNovaTarefa.setBounds(10, 400, 250, 50);

        painel.add(labelTitulo);
        painel.add(scrollTarefas);
        painel.add(btnNovaTarefa);

        btnNovaTarefa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TarefaFrame(telaTarefaLista);
                carregarDadosTabela();
            }
        });

        telaTarefaLista.setVisible(true);
    }

    private void carregarDadosTabela() {
        List<Tarefa> tarefas = new ArrayList<>();
        TarefaDAO dao = new TarefaDAO(null);
        tarefas = dao.getTarefas();

        Object[][] dados = new Object[tarefas.size()][colunas.length];
        int i = 0;

        for (Tarefa t : tarefas) {
            dados[i][0] = t.getNome();
            dados[i][1] = t.getDescricao();
            dados[i][2] = t.getResponsavel() != null ? t.getResponsavel().getNome() : "sem-responsavel";
            dados[i][3] = t.getDataInicio() != null ? t.getDataInicio().format(Tarefa.getFormatter()) : "sem-data-inicio";
            dados[i][4] = t.getPrazo();
            dados[i][5] = t.getDataEntrega() != null ? t.getDataEntrega().format(Tarefa.getFormatter()) : "sem-data-entrega";
            dados[i][6] = formatarStatus(t.getStatus());
            i++;
        }

        model.setDataVector(dados, colunas);
    }

    private String formatarStatus(Status status) {
        if (status == null) return "Desconhecido";

        switch (status) {
            case NAO_INICIADO:
                return "Não Iniciado";
            case EM_ANDAMENTO:
                return "Em Andamento";
            case EM_ATRASO:
                return "Em Atraso";
            case CONCLUIDO:
                return "Concluído";
            default:
                return "Desconhecido";
        }
    }
}