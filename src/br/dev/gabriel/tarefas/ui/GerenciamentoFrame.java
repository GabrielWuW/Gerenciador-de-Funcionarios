package br.dev.gabriel.tarefas.ui;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GerenciamentoFrame {
	private JLabel labelTitulo;
	private JButton btnFuncionarios;
	private JButton btnTarefas;
	
	public GerenciamentoFrame() {
		criarTela();
	}
	
	private void criarTela() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        JFrame telaGerenciador = new JFrame();
        telaGerenciador.setSize(700, 500);
        telaGerenciador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaGerenciador.setLayout(null);
        telaGerenciador.setLocationRelativeTo(null);
        telaGerenciador.setResizable(false);
        
		Container painel = telaGerenciador.getContentPane();
		
		labelTitulo = new JLabel("Controller Employees Gengar");
		labelTitulo.setBounds(120, 10, 500, 40);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		labelTitulo.setForeground(Color.magenta);
		
		btnFuncionarios = new JButton("Funcionários");
		btnFuncionarios.setBounds(100, 100, 500, 80);
		btnFuncionarios.setFont(new Font("Arial", Font.BOLD, 24));

		btnTarefas = new JButton("Tarefas");
		btnTarefas.setBounds(100, 200, 500, 80);
		btnTarefas.setFont(new Font("Arial", Font.BOLD, 24));

		btnFuncionarios.addChangeListener(e -> {
			if (btnFuncionarios.getModel().isPressed()) {
				new FuncionarioListaFrame();
			}
		});

		btnTarefas.addChangeListener(e -> {
			if (btnTarefas.getModel().isPressed()) {
				
			}
		});
		
		painel.add(labelTitulo);
		painel.add(btnFuncionarios);
		painel.add(btnTarefas);
		telaGerenciador.setVisible(true);

		
		
	}
}
