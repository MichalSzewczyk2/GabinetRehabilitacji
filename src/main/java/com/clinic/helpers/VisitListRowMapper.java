package com.clinic.helpers;

import com.clinic.dbTables.Visit;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VisitListRowMapper implements RowMapper<List<Visit>> {

  @SneakyThrows
  @Override
  public List<Visit> mapRow(ResultSet rs, int rowNum) {
    List<Visit> result = new ArrayList<>();
    result.add(Visit.getVisitFromResultSet(rs));
    while (rs.next()) {
      result.add(Visit.getVisitFromResultSet(rs));
    }
    return result;
  }


}
