package io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ExportandoArquivo {
	
	public static void main(String[] args) throws IOException {
			
		OutputStream os = new FileOutputStream("Saida.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
	
		bw.write("Testando a escrita de um arquivo");
		bw.newLine();
		bw.write("Conteudo da proxima linha");
		bw.close();
	}
}
