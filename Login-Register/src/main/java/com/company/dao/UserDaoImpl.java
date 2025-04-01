package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.company.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean isValidUser(String username, String password) {

		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("sucessfully query executed");

			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addUser(User user) {
		try (Connection connection = DBUtil.getConnection()) {

			// **Check if username or email already exists**
			String checkQuery = "SELECT * FROM users WHERE username = ? OR email = ?";
			try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
				checkStmt.setString(1, user.getUsername());
				checkStmt.setString(2, user.getEmail());
				ResultSet rs = checkStmt.executeQuery();

				if (rs.next()) {
					// **Username or Email already exists**
					System.out.println("Username or Email already exists");
					return false;
				}
			}

			// **Insert new user**
			String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
			try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
				insertStmt.setString(1, user.getUsername());
				insertStmt.setString(2, user.getEmail());
				insertStmt.setString(3, user.getPassword());

				int rowsAffected = insertStmt.executeUpdate();
				System.out.println("User added successfully!");

				return rowsAffected > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
