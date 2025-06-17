package br.dev.gabriel.tarefas.factory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class ArquivoFuncionarioFactory {
    private final String caminho = Paths.get(System.getProperty("user.dir"), "funcionarios.csv").toString();

    public BufferedWriter getBufferedWriter(boolean append) throws IOException {
        FileWriter fw = new FileWriter(caminho, append);
        return new BufferedWriter(fw);
    }

    public BufferedReader getBufferedReader() throws IOException {
        FileReader fr = new FileReader(caminho);
        return new BufferedReader(fr);
    }
}