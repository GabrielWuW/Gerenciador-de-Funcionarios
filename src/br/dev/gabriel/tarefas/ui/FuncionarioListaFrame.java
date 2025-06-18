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

import br.dev.gabriel.tarefas.dao.FuncionarioDAO;
import br.dev.gabriel.tarefas.model.Funcionario;

public class FuncionarioListaFrame {
	
	private JLabel labelTitulo;
	private JButton btnNovo;
	private DefaultTableModel model;
	private JTable tabelaFuncionarios;
	private JScrollPane scrollFuncionarios;
	String[] colunas = {"CÓDIGO", "NOME", "FUNCIONÁRIO", "CARGO"};
	
	
	public FuncionarioListaFrame() {
		criarTela();
	}
	
	private void criarTela() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		JFrame telaFuncionarioLista = new JFrame("Lista de Funcionários");
		telaFuncionarioLista.setSize(700, 500);
		telaFuncionarioLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaFuncionarioLista.setLayout(null);
		telaFuncionarioLista.setLocationRelativeTo(null);
		telaFuncionarioLista.setResizable(false);
		Container painel = telaFuncionarioLista.getContentPane();
		
		labelTitulo = new JLabel("Cadastro de Funcionários");
		labelTitulo.setBounds(10, 10, 500, 40);
		labelTitulo.setFont(null);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		labelTitulo.setForeground(Color.magenta);
		
		
		model = new DefaultTableModel(colunas, 10);
		tabelaFuncionarios = new JTable(model);
		scrollFuncionarios = new JScrollPane(tabelaFuncionarios);
		scrollFuncionarios.setBounds(10, 70, 680, 300);
		
		carregarDadosTabela();
		
		
		btnNovo = new JButton("Cadastrar novo funcionário");
		btnNovo.setBounds(10, 400, 250, 50);
		
		painel.add(labelTitulo);
		painel.add(scrollFuncionarios);
		painel.add(btnNovo);
		
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FuncionarioFrame(telaFuncionarioLista);
				carregarDadosTabela();
			}
		});
		
		telaFuncionarioLista.setVisible(true);
	}
	
	private void carregarDadosTabela() {
		List<Funcionario> funcionarios = new ArrayList<>();
		FuncionarioDAO dao = new FuncionarioDAO(null);
		funcionarios = dao.getFuncionarios();
		
		int i = 0;
		Object[][] dados = new Object[funcionarios.size()] [3];
		
		for(Funcionario f: funcionarios) {
			dados[i][0] = f.getMatricula().toUpperCase();
			dados[i][1] = f.getNome();
			dados[i][2] = f.getCargo();
			i++;
			
		}
		model.setDataVector(dados, colunas);
	
	}
}