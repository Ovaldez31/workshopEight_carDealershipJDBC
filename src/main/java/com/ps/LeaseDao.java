package com.ps;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void save(LeaseContract contract) {
        String query = "INSERT INTO lease_contracts (vin, customer_name, monthly_payment, lease_term, date) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, contract.getVin());
            preparedStatement.setString(2, contract.getCustomerName());
            preparedStatement.setDouble(3, contract.getMonthlyPayment());
            preparedStatement.setDouble(4, contract.getLeaseTerm());
            preparedStatement.setString(5, contract.getDate());

            preparedStatement.executeUpdate();
            System.out.println("Lease contract saved.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
