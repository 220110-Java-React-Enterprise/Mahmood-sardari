package Persistence;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DataSourceCRUD<T> {
        //CRUD - create, read, update, delete
        public Integer create(T t) throws SQLException, IOException;

        public T read(Integer id) throws SQLException, IOException;

        public T update(T t) throws SQLException, IOException;

        public void delete(Integer id) throws SQLException, IOException;

        //List<AccountClientsModel> getAllItemsByAccountId(Integer accountId);
}