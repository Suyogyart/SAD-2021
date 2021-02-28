package com.example.revenueRecognition.tableDataGateway;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProductTableDataGateway extends AbstractTableDataGateway {

    private static final String SQL_GET_ALL = "SELECT * from products";
    private static final String SELECT_PRODUCT_SQL = "SELECT * FROM products WHERE id = ?";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO PRODUCTS (name, type) VALUES (?, ?)";

    public ProductTableDataGateway(DataSource dataSource) {
        super(dataSource);
    }

    public ResultSet findAll() {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL);

            ResultSet rs = ps.executeQuery();
            // Convert result to CachedRowSet for better efficiency
            // CachedRowSet stores data in memory so you can work on the data
            // without keeping the connection open all the time
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet crs = factory.createCachedRowSet();
            crs.populate(rs);

            return crs;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ResultSet findOne(int productId) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_SQL);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet crs = factory.createCachedRowSet();
            crs.populate(rs);

            return crs;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int insert(String name, String type) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL);

            ps.setString(1, name);
            ps.setString(2, type);
            // Get Id using getGeneratedKeys()
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
