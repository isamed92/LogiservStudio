package application.repository.info;

import application.comunes.Alerta;
import application.database.JDBCConnection;
import application.model.compra.Articulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticuloRepository {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void save(Articulo articulo){
        try {
            connection= JDBCConnection.getInstanceConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO ARTICULO values(?,?,?,?,?)");
            preparedStatement.setString(1,null);
            preparedStatement.setString(2,articulo.getMarca());
            preparedStatement.setString(3,articulo.getModelo());
            preparedStatement.setString(4,articulo.getDescripcion());
            preparedStatement.setInt(5,articulo.getCategoria());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            String cuerpoMsj = "Artículo " + articulo.getDescripcion() + " agregado correctamente.\n";
            Alerta.alertaInfo("Artículos",cuerpoMsj);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void update(Articulo articulo){
        try {
            connection = JDBCConnection.getInstanceConnection();
            preparedStatement= connection.prepareStatement("" +
                    "UPDATE ARTICULO " +
                        "SET Marca=?, Modelo=?, Descripcion=?, " +
                    "categoria_articulo_idCategoriaArticulo=?" +
                    "WHERE idArticulo=?");
            preparedStatement.setString(1,articulo.getMarca());
            preparedStatement.setString(2,articulo.getModelo());
            preparedStatement.setString(3,articulo.getDescripcion());
            preparedStatement.setInt(4,articulo.getCategoria());
            preparedStatement.setInt(5,articulo.getIdArticulo());
            preparedStatement.close();
            connection.close();
            String headerMsj="Actualización: artículo actualizado";
            String cuerpoMsj = "Artículo: " + articulo.getDescripcion() + " modificado correctamente.";
            Alerta.alertaInfo("Artículos", headerMsj, cuerpoMsj);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void delete(Articulo articulo){
        try {
            connection= JDBCConnection.getInstanceConnection();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM ARTICULO WHERE idArticulo=?");
            preparedStatement.setInt(1, articulo.getIdArticulo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public ObservableList<Articulo> viewAll(){
        ObservableList<Articulo> list = FXCollections.observableArrayList();
        try {
            connection= JDBCConnection.getInstanceConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM ARTICULO");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Articulo articulo = new Articulo();
                articulo.setIdArticulo(resultSet.getInt(1));
                articulo.setMarca(resultSet.getString(2));
                articulo.setModelo(resultSet.getString(3));
                articulo.setDescripcion(resultSet.getString(4));
                articulo.setCategoria(resultSet.getInt(5));
                list.add(articulo);
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void search(Articulo articulo){
        try {
            connection= JDBCConnection.getInstanceConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM ARTICULO where idArticulo=?");
            preparedStatement.setInt(1,articulo.getIdArticulo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
