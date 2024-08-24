package com.task;

import java.sql.*;
import java.sql.ResultSet;

public class DatabaseOperations {
    public void insertData(String description, TaskStatus status, Timestamp created_at, Timestamp updated_at) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO task ( description, status, created_at, updated_at ) VALUES ( ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, description);
            preparedStatement.setString(2, String.valueOf(status));
            preparedStatement.setTimestamp(3, created_at);
            preparedStatement.setTimestamp(4, updated_at);
            int rowsAffected = preparedStatement.executeUpdate();


            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1); // O índice 1 refere-se à primeira coluna no ResultSet
                    System.out.println("tarefa adicionada com sucesso (ID: " + generatedId + ")");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public enum TaskStatus {
        TODO, IN_PROGRESS, DONE
    }

    public void updateData(int id, String description, Timestamp updated_at) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE task SET description = ?, updated_at = ? WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, description);
            preparedStatement.setTimestamp(2, updated_at);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

