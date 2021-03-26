package io;

import java.io.IOException;
import java.io.PrintStream;

public class ExportandoArquivo {
	
	public static void main(String[] args) throws IOException {
			
		PrintStream out = new PrintStream("Saida.txt");
		out.println("Testando a escrita de um arquivo");
		out.println("Conteudo da proxima linha");
		out.close();
	}
}
