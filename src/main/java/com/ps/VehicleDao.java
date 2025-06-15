package com.ps;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        System.out.println(query);

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            if (resultSet.next()) {
                do {
                    Vehicle vehicle = new Vehicle(
                            resultSet.getString("vin"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getInt("mileage"),
                            resultSet.getDouble("price")
                    );
                    vehicles.add(vehicle);
                } while (resultSet.next());
            } else {
                System.out.println("No vehicles found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    public List<Vehicle> searchByVehicleModelMake(String keyword) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE model LIKE ? OR make LIKE ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)

        ) {
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        Vehicle vehicle = new Vehicle(
                                resultSet.getString("vin"),
                                resultSet.getInt("year"),
                                resultSet.getString("make"),
                                resultSet.getString("model"),
                                resultSet.getString("color"),
                                resultSet.getInt("mileage"),
                                resultSet.getDouble("price")
                        );
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("No matching vehicles found.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public void add(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (vin, year, make, model, color, mileage, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setInt(6, vehicle.getMileage());
            preparedStatement.setDouble(7, vehicle.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vehicle added successfully.");
            } else {
                System.out.println("Vehicle NOT added.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String vin) {
        String query = "DELETE FROM vehicles WHERE vin = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, vin);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vehicle deleted successfully.");
            } else {
                System.out.println("No vehicle found with VIN: " + vin);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}