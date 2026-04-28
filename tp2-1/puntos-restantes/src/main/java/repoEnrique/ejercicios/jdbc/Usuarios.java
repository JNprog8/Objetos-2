package oop2Enrique.ejercicios.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuarios {

    private final String jdbcUrl;

    public Usuarios(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void insertar(String nombre, String email) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO usuarios (nombre, email) VALUES (?, ?)")) {
            connection.setAutoCommit(false);
            statement.setString(1, nombre);
            statement.setString(2, email);
            try {
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al insertar usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }
    }

    public void actualizarEmail(int id, String nuevoEmail) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement("UPDATE usuarios SET email = ? WHERE id = ?")) {
            connection.setAutoCommit(false);
            statement.setString(1, nuevoEmail);
            statement.setInt(2, id);
            try {
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al actualizar usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }
}