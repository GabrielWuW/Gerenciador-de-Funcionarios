package br.dev.gabriel.tarefas.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Tarefa {
	private String nome;
	private String descricao;
	private Funcionario responsavel;
	private LocalDate dataInicio;
	private int prazo;
	private Status status;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Tarefa(Funcionario responsavel) {
		this.responsavel = responsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public boolean setDataInicio(String dataInicioStr) {
		try {
			LocalDate data = LocalDate.parse(dataInicioStr, FORMATTER);
			this.dataInicio = data;
			return true;
		} catch (DateTimeParseException e) {
			System.err.println("Formato de data inválido: " + dataInicioStr + ". Use dd/MM/yyyy.");
			return false;
		}
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public LocalDate getDataEntrega() {
		if (dataInicio != null) {
			return dataInicio.plusDays(prazo);
		}
		return null;
	}

	public Status getStatus() {
		atualizarStatus();
		return status;
	}

	public void setStatus(Status statusManual) {
		this.status = statusManual;
	}

	private void atualizarStatus() {
		LocalDate hoje = LocalDate.now();

		if (dataInicio == null) {
			this.status = Status.NAO_INICIADO;
			return;
		}

		LocalDate dataPrevistaEntrega = getDataEntrega();

		if (hoje.isBefore(dataInicio)) {
			this.status = Status.NAO_INICIADO;
		} else if (!hoje.isAfter(dataPrevistaEntrega)) {
			this.status = Status.EM_ANDAMENTO;
		} else {
			this.status = Status.EM_ATRASO;
		}
	}

	@Override
	public String toString() {
		return nome + "," +
				descricao + "," +
				(responsavel != null ? responsavel.getNome() : "sem-responsavel") + "," +
				(dataInicio != null ? dataInicio : "sem-data-inicio") + "," +
				prazo + "," +
				(getDataEntrega() != null ? getDataEntrega() : "sem-data-entrega") + "," +
				getStatus();
	}
}