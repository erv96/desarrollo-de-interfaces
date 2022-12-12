package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController {
	@FXML
	private TextField txtTitulo;

	@FXML
	private TextField txtPrecio;

	@FXML
	private ChoiceBox cbConsola;

	@FXML
	private ChoiceBox cbPegi;

	@FXML
	private TableView<Videojuego> tableVideojuegos;

	@FXML
	private TableColumn<Videojuego, String> columTitulo;

	@FXML
	private TableColumn<Videojuego, Integer> columPrecio;

	@FXML
	private TableColumn<Videojuego, String> columConsola;

	@FXML
	private TableColumn<Videojuego, Integer> columPegi;

	private ObservableList<Videojuego> listaVideojuegos = FXCollections.observableArrayList();

	public ObservableList<String> listaConsolas = FXCollections.observableArrayList("PS5", "Nintendo Switch",
			"Xbox Series X");
	public ObservableList<Integer> listaPegi = FXCollections.observableArrayList(3, 7, 12, 16, 18);

	@FXML
	private void initialize() {
		cbConsola.setItems(listaConsolas);
		cbPegi.setItems(listaPegi);
		columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		columPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));

		ObservableList listaVideojuegosBD = getJuegosBd();

		tableVideojuegos.setItems(getJuegosBd());

	}

	private ObservableList<Videojuego> getJuegosBd() {

		/**
		 * Creamos la ObservableList donde almacenaremos los libros contenidos en la BD
		 */
		ObservableList<Videojuego> listaVideojuegoBD = FXCollections.observableArrayList();

		// NOS CONECTAMOS A LA BD

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		String query = "select * from videojuegos";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Videojuego juego = new Videojuego(rs.getInt("id"), rs.getString("titulo"), rs.getInt("precio"),
						rs.getString("consola"), rs.getInt("pegi"));
				listaVideojuegoBD.add(juego);
			}

			// CERRAMOS LA CONEXIÓN
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaVideojuegoBD;
	}

	@FXML
	public void anadirJuego(ActionEvent event) {

		Videojuego l = new Videojuego(txtTitulo.getText(), Integer.parseInt(txtPrecio.getText()),
				cbConsola.getValue().toString(), Integer.parseInt(cbPegi.getValue().toString()));
		listaVideojuegos.add(l);

		txtTitulo.clear();
		txtPrecio.clear();
		cbConsola.getSelectionModel().clearSelection();
		cbPegi.getSelectionModel().clearSelection();

		// NOS CONECTAMOS A LA BD

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		try {
			String query = "INSERT INTO videojuegos (titulo,precio,consola,pegi) values(?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, l.getTitulo());
			ps.setInt(2, l.getPrecio());
			ps.setString(3, l.getConsola());
			ps.setInt(4, l.getPegi());

			ps.executeUpdate();

			// CERRAMOS LA CONEXIÓN
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Despues de insertar actualizamos la tabla

		ObservableList listaVideojuegosBD = getJuegosBd();

		tableVideojuegos.setItems(getJuegosBd());

	}

	@FXML
	public void borrarJuego(ActionEvent event) {

		System.out.println("Borrando un juego");

		int indiceSeleccionado = tableVideojuegos.getSelectionModel().getSelectedIndex();

		System.out.println("Indice a borrar: " + indiceSeleccionado);
		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR AL BORRAR");
			alerta.setHeaderText("No se ha seleccionado ningún juego al borrar");
			alerta.setContentText("Por favor selecciona un juego para borrarlo");
			alerta.showAndWait();
		} else {

			// NOS CONECTAMOS A LA BD

			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();

			try {
				String query = "Delete from videojuegos Where id=?";
				PreparedStatement ps = connection.prepareStatement(query);
				Videojuego juego = tableVideojuegos.getSelectionModel().getSelectedItem();
				ps.setInt(1, juego.getId());
				ps.executeUpdate();

				ObservableList listaVideojuegoBD = getJuegosBd();
				tableVideojuegos.setItems(listaVideojuegoBD);

				// CERRAMOS LA CONEXIÓN
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
