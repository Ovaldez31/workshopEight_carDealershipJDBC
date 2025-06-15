package com.ps;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void save(SalesContract contract) {
        String query = "INSERT INTO sales_contracts (vin, customer_name, sales_price, date) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, contract.getSalesVin());
            preparedStatement.setString(2, contract.getCustomerName());
            preparedStatement.setDouble(3, contract.getSalesPrice());
            preparedStatement.setString(4, contract.getDate());

            preparedStatement.executeUpdate();
            System.out.println("Sales contract saved.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
