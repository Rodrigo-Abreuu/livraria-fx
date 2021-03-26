package application;
	
import java.io.IOException;

import br.com.casadocodigo.livraria.produtos.Produto;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import repositorio.RepositorioDeProdutos;
import threads.ExportadorCSV;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Group group = new Group();
		Scene scene = new Scene(group, 690, 510);
		
		ObservableList<Produto> produtos = new RepositorioDeProdutos().lista();
		
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
		vBox.setPadding(new Insets(70,0,0,10));
		
		Label label = new Label("Listagem de Livros");
		label.setFont(Font.font("Lucida grande", FontPosture.REGULAR, 30));
		label.setPadding(new Insets( 20, 0, 10, 10));
		
		Button button = new Button("Exportando CSV");
		button.setLayoutX(575);
		button.setLayoutY(25);
		button.setOnAction(event -> exportarEmCSV(produtos));
			
		group.getChildren().addAll(label, vBox, button);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistema da livraria com Java FX");
		
		primaryStage.show();
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
