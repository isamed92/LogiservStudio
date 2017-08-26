package application.view;

import application.model.compra.FacturaCompra;
import application.repository.info.FacturaCompraRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CompraComprasController {

	@FXML
	private TableView<FacturaCompra> comprasTable;
	@FXML
	private TableColumn<FacturaCompra, String> codigoFacturaColumn;
	@FXML
	private TableColumn<FacturaCompra, String> fechaColumn;
	@FXML
	private TableColumn<FacturaCompra, String> proveedorColumn;
	@FXML
	private TableColumn<FacturaCompra, String> montoColumn;
	@FXML
	private TableColumn<FacturaCompra, String> creditoFiscalColumn;

	private Stage owner;
	private ObservableList<FacturaCompra> articuloObservableList = FXCollections.observableArrayList();
	private FacturaCompraRepository facturaCompraRepository = new FacturaCompraRepository();

	@FXML
	private void initialize(){
	    codigoFacturaColumn.setCellValueFactory(cellData -> cellData.getValue().idFacturaCompraProperty().asString());
	    fechaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
	    proveedorColumn.setCellValueFactory(cellData -> cellData.getValue().nombre_proveedorProperty());
	    montoColumn.setCellValueFactory(cellData -> cellData.getValue().idFacturaCompraProperty().asString());//todo: esto esta mal :3
		
	}
	
	public void setOwner(Stage owner){
		this.owner = owner;
	}
}
