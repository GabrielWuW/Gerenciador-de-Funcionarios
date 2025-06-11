package br.dev.gabriel.tarefas.factory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoFuncionarioFactory {
    private String caminho = "/Users/25132745/eclipse-workspace/tarefas/funcionarios.csv";
    private FileWriter fw;
    private BufferedWriter bw;
    private FileReader fr;
    private BufferedReader br;

    public BufferedWriter getBw() throws IOException {
        fw = new FileWriter(caminho, true);
        bw = new BufferedWriter(fw);
        return bw;
    }

    public BufferedReader getBr() throws IOException {
        fr = new FileReader(caminho);
        br = new BufferedReader(fr);
        return br;
    }
}
