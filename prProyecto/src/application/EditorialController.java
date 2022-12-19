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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class EditorialController {

	@FXML
	private ChoiceBox cbNombre;

	@FXML
	private TextField txtFundador;

	@FXML
	private DatePicker myDatePicker;

	@FXML
	private TableView<Editorial> tableEditoriales;

	@FXML
	private TableColumn<Editorial, String> columNombre;

	@FXML
	private TableColumn<Editorial, String> columFundador;

	@FXML
	private TableColumn<Editorial, String> columFecha;
	
	private ObservableList<Editorial> listaEditorial = FXCollections.observableArrayList();
	
	private ObservableList<String> listaNombre = FXCollections.observableArrayList("Marvel","DC");

	
	@FXML
	private void initialize() {
		cbNombre.setItems(listaNombre);
		columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columFundador.setCellValueFactory(new PropertyValueFactory("fundador"));
		columFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
		
		ObservableList listaEditorialesBD = getEditBd();
		
		tableEditoriales.setItems(getEditBd());

	}

	private ObservableList<Editorial> getEditBd() {

		/**
		 * Creamos la ObservableList donde almacenaremos los libros contenidos en la BD
		 */
		ObservableList<Editorial> listaEditorialesBd = FXCollections.observableArrayList();

		// NOS CONECTAMOS A LA BD

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		String query = "select * from editoriales";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Editorial edit = new Editorial(rs.getInt("id"),rs.getString("nombre"), rs.getString("fundador_fundadores"),
						rs.getString("fecha_fundacion"));
				listaEditorialesBd.add(edit);
			}

			// CERRAMOS LA CONEXIÓN
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaEditorialesBd;
	}

	public void anadirEditorial(ActionEvent event) {
		Editorial l = new Editorial(cbNombre.getValue().toString(), txtFundador.getText(), myDatePicker.getValue().toString());
		listaEditorial.add(l);

		cbNombre.getSelectionModel();
		txtFundador.clear();
		myDatePicker.getEditor().clear();

		// NOS CONECTAMOS A LA BD

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		try {
			String query = "INSERT INTO editoriales (nombre,fundador_fundadores,fecha_fundacion) values(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, l.getNombre());
			ps.setString(2, l.getFundador());
			ps.setString(3, l.getFecha());

			ps.executeUpdate();

			// CERRAMOS LA CONEXIÓN
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ObservableList listaEditorialesBD = getEditBd();

		tableEditoriales.setItems(getEditBd());
	}
	
	@FXML
	public void borrarEditorial(ActionEvent event) {
		int indiceSeleccionado = tableEditoriales.getSelectionModel().getSelectedIndex();

		
		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR AL BORRAR");
			alerta.setHeaderText("No se ha seleccionado ninguna Editorial al borrar");
			alerta.setContentText("Por favor selecciona una editorial para borrarla");
			alerta.showAndWait();
		} else {
			// NOS CONECTAMOS A LA BD

			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();

			try {
				String query = "Delete from editoriales Where id=?";
				PreparedStatement ps = connection.prepareStatement(query);
				Editorial libro = tableEditoriales.getSelectionModel().getSelectedItem();
				ps.setInt(1, libro.getId());	
				ps.executeUpdate();
				
				String query2 = "ALTER TABLE editoriales AUTO_INCREMENT = 1;";
				PreparedStatement ps2 = connection.prepareStatement(query2);
				ps2.executeUpdate();
				
				tableEditoriales.getSelectionModel().clearSelection();
				
				ObservableList listaEditBD = getEditBd();
				tableEditoriales.setItems(listaEditBD);
				
				// CERRAMOS LA CONEXIÓN
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		

	}
}
