package Persistence;



import Utils.ConnectionManager;
import Utils.CustomArrayList;
import Utils.CustomInterfaceList;
import org.mariadb.jdbc.internal.com.read.dao.Results;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountClientsRepo implements DataAccountCRUD<AccountClientsModel>{
    @Override
    public Integer create(AccountClientsModel accountClientModel) throws SQLException, IOException {
        String sql = "INSERT INTO accounts (account_name, balance, user_id ) VALUES (?, ?, ?)";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, accountClientModel.getAccount_name());
        pstmt.setDouble(2, accountClientModel.getBalance());
        pstmt.setInt(3, accountClientModel.getUserId());

        pstmt.execute();
        ResultSet rs = pstmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);

    }

    @Override
    public AccountClientsModel read(Integer accountId) throws SQLException, IOException {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, accountId);
        ResultSet rs = pstmt.executeQuery();

        AccountClientsModel account = new AccountClientsModel();
        if(rs.next()) {
            account.setAccountId(rs.getInt("account_id"));
            account.setUserId(rs.getInt("user_id"));
            account.setBalance(rs.getDouble("balance"));
            account.setAccount_name(rs.getString("account_name"));
            return account;
        } else {
            return null;
        }

    }

    @Override
    public AccountClientsModel update(AccountClientsModel accountClientsModel) throws SQLException, IOException {
        String sql = "UPDATE accounts SET user_id = ?, account_name = ?, balance = ? WHERE account_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, accountClientsModel.getUserId());
        pstmt.setString(2, accountClientsModel.getAccount_name());
        pstmt.setDouble(3, accountClientsModel.getBalance());
        pstmt.setInt(4, accountClientsModel.getAccountId());
        pstmt.executeUpdate();

        String verify = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement vstmt = ConnectionManager.getConnection().prepareStatement(verify);
        vstmt.setInt(1, accountClientsModel.getAccountId());
        ResultSet rs = vstmt.executeQuery();

        if(rs.next()) {
            AccountClientsModel verifiedAccountClientsModel = new AccountClientsModel();
            verifiedAccountClientsModel.setAccountId(rs.getInt("account_id"));
            verifiedAccountClientsModel.setUserId(rs.getInt("user_id"));
            verifiedAccountClientsModel.setBalance(rs.getDouble("balance"));
            verifiedAccountClientsModel.setAccount_name(rs.getString("account_name"));
            return verifiedAccountClientsModel;
        }

        return null;

    }

    @Override
    public void delete(Integer accountId) throws SQLException, IOException {
        String sql = "DELETE FROM accounts WHERE account_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, accountId);
        pstmt.executeUpdate();

    }

    @Override
    public CustomInterfaceList<AccountClientsModel> getAllAccounts(Integer accountId) throws SQLException, IOException {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, accountId);
        ResultSet rs = pstmt.executeQuery();
        CustomInterfaceList<AccountClientsModel> list = new CustomArrayList<>();

        while(rs.next()) {
            AccountClientsModel singleAccount = new AccountClientsModel(rs.getInt("user_id"),
                    rs.getInt("account_id"),rs.getDouble("balance"),rs.getString("account_name"));
            list.add(singleAccount);

        }
        return list;
    }

//    public ClientModel authenticate(String username, String password) throws SQLException, IOException {
//        String sql = "SELECT * FROM users WHERE username = ?";
//        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
//        pstmt.setString(1, username);
//        ResultSet rs = pstmt.executeQuery();
//
//        if(rs.next() && rs.getString("password").equals(password)) {
//            return new ClientModel(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
//        }
//        return null;
//    }
}
