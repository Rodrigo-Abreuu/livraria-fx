package threads;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import br.com.casadocodigo.livraria.produtos.Produto;

public class ExportadorCSV implements Runnable{

	public void paraCSV(List<Produto> produtos) throws IOException{
		PrintStream ps = new PrintStream("produtos.csv");
		ps.println("Nome, Descricao, Valor, ISBN");
		
		for (Produto produto : produtos){
			ps.println(String.format("%s, %s, %s, %s",
					produto.getNome(),
					produto.getDescricao(),
					produto.getValor(),
					produto.getIsbn()));
		}
		
		ps.close();
	}

	@Override
	public void run() {
		System.out.println("Rodando em paralelo");		
	}
	
}
