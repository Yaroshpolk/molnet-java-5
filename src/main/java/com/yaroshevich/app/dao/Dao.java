package com.yaroshevich.app.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws SQLException;

}
