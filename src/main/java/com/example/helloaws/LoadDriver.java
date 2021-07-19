package com.example.helloaws;

import com.gremlin.*;

import java.sql.*;

public class LoadDriver {
    private Connection connection;

    public static void main(String[] args) throws SQLException {


        final GremlinCoordinatesProvider coordinatesProvider = new GremlinCoordinatesProvider() {
            @Override
            public ApplicationCoordinates initializeApplicationCoordinates() {
                return new ApplicationCoordinates.Builder()
                        .withType("HelloAWSExample")
                        .withField("name", "database").build();
            }
        };
        final GremlinServiceFactory gremlinServiceFactory = new GremlinServiceFactory(coordinatesProvider);
        final GremlinService gremlinService = gremlinServiceFactory.getGremlinService();

        final TrafficCoordinates injectionPoint = new TrafficCoordinates.Builder()
                .withType("main")
                .withField("name", "database")
                .build();
        gremlinService.applyImpact(injectionPoint);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        String jdbcURL = "jdbc:mysql://ecommerce-backend.ciregdtqs6dp.us-west-2.rds.amazonaws.com:3306";
        String username = "";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            System.out.println("Database connection established.");
            String query = "select * from ecommerce.categories";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                //Print one row
                for(int i = 1 ; i <= columnsNumber; i++){
                    System.out.print(rs.getString(i) + " ");

                }
                System.out.println();

            }
            return;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
