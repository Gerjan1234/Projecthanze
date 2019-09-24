package backend.aanspraak.repository;

import backend.Application;
import backend.aanspraak.model.Aanspraak;
import backend.aanspraak.model.Werkgever;
import jdk.internal.org.objectweb.asm.tree.FieldInsnNode;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class AanspraakRepository {

    public List<Aanspraak> getAanspraken() {
        // FIXME SQL

        Aanspraak a = new Aanspraak("1");
        Aanspraak b = new Aanspraak("2");
        return Arrays.asList(a, b);

//        return Collections.emptyList();
    }

    public int getAantalWerknemersPerWerkgever(String employerId) {
        return -1;
    }

    public List<Werkgever> getWerkgeversZonderPersoneel() {

        // werkgevers zonder personeel-2
        String query = "SELECT employers.employer_id, employers.company_name, employers.adress_id, employers.government_id, employers.communication_type\n" +
                "FROM employers LEFT JOIN employees ON employers.employer_id = employees.employer_id\n" +
                "GROUP BY employers.employer_id, employees.socialsecurity_id, employers.company_name, employers.adress_id, employers.government_id, employers.communication_type\n" +
                "HAVING (((Count(employees.socialsecurity_id))=0))\n" +
                "ORDER BY employers.company_name;";

        List<Werkgever> werkgevers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                werkgevers.add(new Werkgever(
                        resultSet.getString("employer_id"),
                        resultSet.getString("company_name")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return werkgevers;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Application.HOST_NAME, Application.USER_NAME, Application.PASSWORD);
    }
}
