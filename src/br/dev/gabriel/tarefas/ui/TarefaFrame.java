package br.dev.gabriel.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.dev.gabriel.tarefas.dao.FuncionarioDAO;
import br.dev.gabriel.tarefas.dao.TarefaDAO;
import br.dev.gabriel.tarefas.model.Funcionario;
import br.dev.gabriel.tarefas.model.Status;
import br.dev.gabriel.tarefas.model.Tarefa;

public class TarefaFrame {

    private JLabel labelNome;
    private JLabel labelDescricao;
    private JLabel labelResponsavel;
    private JLabel labelDataInicio;
    private JLabel labelPrazo;
    private JLabel labelStatus;

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JComboBox<Funcionario> comboResponsavel;
    private JTextField txtDataInicio;
    private JTextField txtPrazo;
    private JComboBox<Status> comboStatus;

    private JButton btnSalvar;
    private JButton btnSair;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TarefaFrame(JFrame pai) {
        criarTela(pai);
    }

    private void criarTela(JFrame pai) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JDialog telaTarefa = new JDialog(pai, true);
        telaTarefa.setSize(550, 550);
        telaTarefa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaTarefa.setLayout(null);
        telaTarefa.setResizable(false);
        telaTarefa.setLocationRelativeTo(null);

        Container painel = telaTarefa.getContentPane();

        labelNome = new JLabel("Nome da Tarefa:");
        labelNome.setBounds(10, 20, 200, 30);
        txtNome = new JTextField();
        txtNome.setBounds(10, 50, 400, 30);

        labelDescricao = new JLabel("Descrição:");
        labelDescricao.setBounds(10, 85, 150, 30);
        txtDescricao = new JTextField();
        txtDescricao.setBounds(10, 115, 400, 30);

        labelResponsavel = new JLabel("Responsável:");
        labelResponsavel.setBounds(10, 150, 150, 30);
        comboResponsavel = new JComboBox<>();
        comboResponsavel.setBounds(10, 180, 300, 30);
        carregarFuncionarios();
        comboResponsavel.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Funcionario) {
                    value = ((Funcionario) value).getNome();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        labelDataInicio = new JLabel("Data de Início (dd/MM/yyyy):");
        labelDataInicio.setBounds(10, 215, 250, 30);
        txtDataInicio = new JTextField();
        txtDataInicio.setBounds(10, 245, 150, 30);

        labelPrazo = new JLabel("Prazo (dias):");
        labelPrazo.setBounds(10, 280, 150, 30);
        txtPrazo = new JTextField();
        txtPrazo.setBounds(10, 310, 100, 30);

        labelStatus = new JLabel("Status:");
        labelStatus.setBounds(10, 345, 150, 30);
        comboStatus = new JComboBox<>(Status.values());
        comboStatus.setBounds(10, 375, 200, 30);
        comboStatus.setEnabled(false);
        comboStatus.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Status) {
                    value = formatarStatus((Status) value);
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 440, 200, 40);

        btnSair = new JButton("Sair");
        btnSair.setBounds(220, 440, 120, 40);

        painel.add(labelNome);
        painel.add(txtNome);
        painel.add(labelDescricao);
        painel.add(txtDescricao);
        painel.add(labelResponsavel);
        painel.add(comboResponsavel);
        painel.add(labelDataInicio);
        painel.add(txtDataInicio);
        painel.add(labelPrazo);
        painel.add(txtPrazo);
        painel.add(labelStatus);
        painel.add(comboStatus);
        painel.add(btnSalvar);
        painel.add(btnSair);

        txtDataInicio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                atualizarStatus();
            }
        });

        txtPrazo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                atualizarStatus();
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarTarefa(telaTarefa);
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaTarefa.dispose();
            }
        });

        telaTarefa.setVisible(true);
    }

    private void carregarFuncionarios() {
        FuncionarioDAO dao = new FuncionarioDAO(null);
        List<Funcionario> funcionarios = dao.getFuncionarios();
        for (Funcionario f : funcionarios) {
            comboResponsavel.addItem(f);
        }
    }

    private void salvarTarefa(JDialog telaTarefa) {
        try {
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();
            Funcionario responsavel = (Funcionario) comboResponsavel.getSelectedItem();
            LocalDate dataInicio = LocalDate.parse(txtDataInicio.getText().trim(), formatter);
            int prazo = Integer.parseInt(txtPrazo.getText().trim());
            Status status = calcularStatus(dataInicio, prazo);

            Tarefa tarefa = new Tarefa(responsavel);
            tarefa.setNome(nome);
            tarefa.setDescricao(descricao);
            tarefa.setDataInicio(dataInicio);
            tarefa.setPrazo(prazo);
            tarefa.setStatus(status);

            TarefaDAO dao = new TarefaDAO(tarefa);
            boolean sucesso = dao.gravar();

            if (sucesso) {
                JOptionPane.showMessageDialog(telaTarefa, "Tarefa gravada com sucesso!");
                limparFormulario();
            } else {
                JOptionPane.showMessageDialog(telaTarefa, "Erro ao gravar tarefa. Tente novamente.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(telaTarefa, "Erro ao salvar tarefa: " + ex.getMessage());
        }
    }

    private void atualizarStatus() {
        try {
            LocalDate dataInicio = LocalDate.parse(txtDataInicio.getText().trim(), formatter);
            int prazo = Integer.parseInt(txtPrazo.getText().trim());
            Status status = calcularStatus(dataInicio, prazo);
            comboStatus.setSelectedItem(status);
        } catch (DateTimeParseException | NumberFormatException e) {
            comboStatus.setSelectedItem(Status.NAO_INICIADO);
        }
    }

    private Status calcularStatus(LocalDate dataInicio, int prazo) {
        LocalDate hoje = LocalDate.now();
        LocalDate prazoFinal = dataInicio.plusDays(prazo);

        if (hoje.isBefore(dataInicio)) {
            return Status.NAO_INICIADO;
        } else if ((hoje.isEqual(dataInicio) || hoje.isAfter(dataInicio)) && hoje.isBefore(prazoFinal.plusDays(1))) {
            return Status.EM_ANDAMENTO;
        } else if (hoje.isAfter(prazoFinal)) {
            return Status.EM_ATRASO;
        } else {
            return Status.NAO_INICIADO;
        }
    }

    private void limparFormulario() {
        txtNome.setText("");
        txtDescricao.setText("");
        txtDataInicio.setText("");
        txtPrazo.setText("");
        comboResponsavel.setSelectedIndex(0);
        comboStatus.setSelectedItem(Status.NAO_INICIADO);
        txtNome.requestFocus();
    }

    private String formatarStatus(Status status) {
        if (status == null) return "";
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
                return status.name();
        }
    }
}