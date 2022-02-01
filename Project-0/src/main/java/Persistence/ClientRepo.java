package Persistence;



import Utils.ConnectionManager;
import org.mariadb.jdbc.internal.com.read.dao.Results;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientRepo implements DataSourceCRUD<ClientModel>{
    @Override
    public Integer create(ClientModel clientModel) throws SQLException, IOException {
        String sql = "INSERT INTO clients (username, password) VALUES (?, ?)";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, clientModel.getUsername());
        pstmt.setString(2, clientModel.getPassword());

        pstmt.execute();
        ResultSet rs = pstmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);

    }

    @Override
    public ClientModel read(Integer user_id) throws SQLException, IOException {
        String sql = "SELECT * FROM clients WHERE user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, user_id);
        ResultSet rs = pstmt.executeQuery();

        ClientModel user = new ClientModel();
        if(rs.next()) {
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } else {
            return null;
        }

    }

    @Override
    public ClientModel update(ClientModel clientModel) throws SQLException, IOException {
        String sql = "UPDATE clients SET username = ?, password = ? WHERE user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, clientModel.getUsername());
        pstmt.setString(2, clientModel.getPassword());
        pstmt.setInt(3, clientModel.getUserId());

        pstmt.executeUpdate();

        String verify = "SELECT * FROM clients WHERE user_id = ?";
        PreparedStatement vstmt = ConnectionManager.getConnection().prepareStatement(verify);
        pstmt.setInt(1, clientModel.getUserId());
        ResultSet rs = vstmt.executeQuery();

        if(rs.next()) {
            ClientModel verifiedClientModel = new ClientModel();
            verifiedClientModel.setUserId(rs.getInt("user_id"));
            verifiedClientModel.setUsername(rs.getString("username"));
            verifiedClientModel.setPassword(rs.getString("password"));
            return verifiedClientModel;
        }

        return null;

    }

    @Override
    public void delete(Integer accountId) throws SQLException, IOException {
        String sql = "DELETE FROM clients WHERE user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, accountId);
        pstmt.executeUpdate();

    }

    public ClientModel authenticate(String username, String password) throws SQLException, IOException {
        String sql = "SELECT * FROM clients WHERE username = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next() && rs.getString("password").equals(password)) {
            return new ClientModel(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
        }
        return null;
    }
}
