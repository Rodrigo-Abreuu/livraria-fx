package application;
	
import java.io.IOException;

import br.com.casadocodigo.livraria.produtos.Produto;
import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import threads.ExportadorCSV;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Group group = new Group();
		Scene scene = new Scene(group, 690, 510);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		ObservableList<Produto> produtos = new ProdutoDAO().lista();
		
		TableView tableView = new TableView<>(produtos);
		
		TableColumn nomeColumn = new TableColumn<>("Nome");
		nomeColumn.setMinWidth(180);
		nomeColumn.setCellValueFactory( new PropertyValueFactory("nome"));
		
		TableColumn descricaoColumn = new TableColumn("Descrição");
		descricaoColumn.setMinWidth(230);
		descricaoColumn.setCellValueFactory( new PropertyValueFactory("descricao"));
		
		TableColumn valorColumn = new TableColumn("Valor");
		valorColumn.setMinWidth(60);
		valorColumn.setCellValueFactory( new PropertyValueFactory("valor"));

		TableColumn isbnColumn = new TableColumn("ISBN");
		isbnColumn.setMinWidth(180);
		isbnColumn.setCellValueFactory( new PropertyValueFactory<>("isbn"));
		
		tableView.getColumns().addAll(nomeColumn, descricaoColumn, valorColumn, isbnColumn);
		
		VBox vBox = new VBox(tableView);
		vBox.setId("vbox");
		
		Label label = new Label("Listagem de Livros");
		label.setId("label-listagem");
		
		Label progresso = new Label();
		label.setId("label-progresso");
		
		Button button = new Button("Exportando CSV");
		button.setOnAction(event -> {
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					dormePorVInteSegundos();
					exportarEmCSV(produtos);	
					return null;
				}
			};
			
			task.setOnRunning(e -> progresso.setText("Exportando..."));
			
			task.setOnSucceeded(e -> progresso. setText("Concluído"));
			
			new Thread(task).start();
		});
		
		double valorTotal = produtos.stream().mapToDouble(Produto::getValor).sum();
			
		Label labelFooter = new Label(String.format("Você tem R$%.2f em estoque,"
				+ " com um total de %d produtos", valorTotal, produtos.size()));
		labelFooter.setId("label-footer");
		
		group.getChildren().addAll(label, vBox, button, progresso, labelFooter);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistema da livraria com Java FX");
		
		primaryStage.show();
	}
	
	private void dormePorVInteSegundos() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println("Erro ao exportar: "+e);
		}
	}

	private void exportarEmCSV(ObservableList<Produto> produtos) {
		try{
			new ExportadorCSV().paraCSV(produtos);
		}catch(IOException e){
			System.out.println("Erro ao exportar: "+ e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
