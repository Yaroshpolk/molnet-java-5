package com.yaroshevich.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Mapper<T> {

    List<T> map(ResultSet resultSet) throws SQLException;

}
