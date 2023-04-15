package com.clinic.helpers;

import com.clinic.dbTables.Surgery;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SurgeryListRowMapper implements RowMapper<List<Surgery>> {

  @SneakyThrows
  @Override
  public List<Surgery> mapRow(ResultSet rs, int rowNum) {
    List<Surgery> result = new ArrayList<>();
    Surgery firstSurgery = Surgery.getSurgeryFromResultSet(rs);
    result.add(firstSurgery);
    while (rs.next()) {
      Surgery newSurgery = Surgery.getSurgeryFromResultSet(rs);
      result.add(newSurgery);
    }
    return result;
  }
}
