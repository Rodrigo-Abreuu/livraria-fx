package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ImportarArquivo {

	public static void main(String[] args) {

		try{
			InputStream is = new FileInputStream("teste.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			String linha = reader.readLine();
			while (linha != null){
				System.out.println(linha);
				linha = reader.readLine();
			}
			reader.close();
		}catch(IOException e){
			System.out.println("Erro ao tentar ler arquivo" + e);
		}
		
	}

}
