package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class indexController {

	@FXML
	private TextField txtTitulo;

	@FXML
	private TextField txtEditorial;

	@FXML
	private ChoiceBox cbAutor;

	@FXML
	private ChoiceBox cbPaginas;
	
	@FXML
	private TableView <Libro> tableLibros;
	
	@FXML
	private TableColumn <Libro, String> columTitulo;
	
	@FXML
	private TableColumn <Libro, String> columEditorial;
	
	@FXML
	private TableColumn <Libro, String> columAutor;
	
	@FXML
	private TableColumn <Libro, Integer> columPaginas;
	
	private ObservableList<Libro> listaLibros = FXCollections.observableArrayList(
			new Libro("La biblia","Planeta","Jesus",500));

	public ObservableList<String> listaAutores = FXCollections.observableArrayList("Sanderson", "Pio Baroja", "Unamuno",
			"Yo");
	
	@FXML
	private void initialize() {
		cbAutor.setItems(listaAutores);
		columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		columAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		
		tableLibros.setItems(listaLibros);


	}
}
