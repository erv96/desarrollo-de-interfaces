package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class indexController {

	@FXML
	private TextField txtTitulo;

	@FXML
	private TextField txtEditorial;

	@FXML
	private ChoiceBox cbAutor;

	@FXML
	private TextField txtPaginas;

	@FXML
	private TableView<Libro> tableLibros;

	@FXML
	private TableColumn<Libro, String> columTitulo;

	@FXML
	private TableColumn<Libro, String> columEditorial;

	@FXML
	private TableColumn<Libro, String> columAutor;

	@FXML
	private TableColumn<Libro, Integer> columPaginas;

	@FXML
	private Button btnAnadir;

	private ObservableList<Libro> listaLibros = FXCollections
			.observableArrayList(new Libro("La biblia", "Planeta", "Jesus", 500));

	public ObservableList<String> listaAutores = FXCollections.observableArrayList("Sanderson", "Pio Baroja", "Unamuno",
			"Yo");

	@FXML
	private void initialize() {
		cbAutor.setItems(listaAutores);
		columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		columAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

		ObservableList listaLibrosBD = getLibrosBd();

		tableLibros.setItems(getLibrosBd());

	}

	private ObservableList<Libro> getLibrosBd() {

		/**
		 * Creamos la ObservableList donde almacenaremos los libros contenidos en la BD
		 */
		ObservableList<Libro> listaLibrosBd = FXCollections.observableArrayList();

		// NOS CONECTAMOS A LA BD

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		String query = "select * from libros";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Libro libro = new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("editorial"),
						rs.getString("autor"), rs.getInt("paginas"));
				listaLibrosBd.add(libro);
			}

			// CERRAMOS LA CONEXIÓN
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaLibrosBd;
	}

	public boolean esNumero(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@FXML
	public void anadirLibro(ActionEvent event) {

		Libro l = new Libro(txtTitulo.getText(), txtEditorial.getText(), cbAutor.getValue().toString(),
				Integer.parseInt(txtPaginas.getText().toString()));
		listaLibros.add(l);

		txtTitulo.clear();
		txtEditorial.clear();
		cbAutor.getSelectionModel().clearSelection();
		txtPaginas.clear();

		// NOS CONECTAMOS A LA BD

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		try {
			String query = "INSERT INTO libros (titulo,editorial,autor,paginas) values(?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, l.getTitulo());
			ps.setString(2, l.getEditorial());
			ps.setString(3, l.getAutor());
			ps.setInt(4, l.getPaginas());

			ps.executeUpdate();

			// CERRAMOS LA CONEXIÓN
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Despues de insertar actualizamos la tabla

		ObservableList listaLibrosBD = getLibrosBd();

		tableLibros.setItems(getLibrosBd());

	}
	
	@FXML
	public void borrarLibro(ActionEvent event) {
		System.out.println("Borrando un libro");

		int indiceSeleccionado = tableLibros.getSelectionModel().getSelectedIndex();

		System.out.println("Indice a borrar: " + indiceSeleccionado);
		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR AL BORRAR");
			alerta.setHeaderText("No se ha seleccionado ningún libro al borar");
			alerta.setContentText("Por favor selecciona un libro para borrarlo");
			alerta.showAndWait();
		} else {
			// NOS CONECTAMOS A LA BD

			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();

			try {
				String query = "Delete from libros Where id=?";
				PreparedStatement ps = connection.prepareStatement(query);
				Libro libro = tableLibros.getSelectionModel().getSelectedItem();
				ps.setInt(1, libro.getId());	
				ps.executeUpdate();
				
				tableLibros.getSelectionModel().clearSelection();
				
				ObservableList listaLibrosBD = getLibrosBd();
				tableLibros.setItems(listaLibrosBD);
				
				// CERRAMOS LA CONEXIÓN
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		

	}
}
