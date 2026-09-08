package org.mineapi.mineapi.infraestructure.persistence;

import org.mineapi.mineapi.core.application.out.ServerRepository;
import org.springframework.stereotype.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ServerRepositoryImpl implements ServerRepository {

    private final DataSource dataSource;

    public ServerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String obtenerLink(String software, String build, String version) {

        String sql = "SELECT link FROM " + software +
                " WHERE build = ? AND version = ?";

        try (
                Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {

            statement.setString(1, build);
            statement.setString(2, version);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {
                    return rs.getString("link");
                }

                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error consultando el enlace", e);
        }
    }
}
