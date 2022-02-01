package Persistence;

import Utils.CustomInterfaceList;

import java.io.IOException;
import java.sql.SQLException;

public interface DataAccountCRUD<T> {
    public Integer create(T t) throws SQLException, IOException;

    public T read(Integer id) throws SQLException, IOException;

    public T update(T t) throws SQLException, IOException;

    public void delete(Integer id) throws SQLException, IOException;

    CustomInterfaceList<AccountClientsModel> getAllAccounts(Integer accountId) throws SQLException, IOException;
}
