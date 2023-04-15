package com.clinic.dbTables;

import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

@Data
public class Surgery {
  private int surgeryId;
  private String name;
  private UserType authorizationNeeded;
  private int regularPrice;
  private int prescriptionPrice;
  private String description;
  private int duration;

  @NotNull
  @SneakyThrows
  public static Surgery getSurgeryFromResultSet(ResultSet rs) {
    Surgery surgery = new Surgery();
    surgery.setSurgeryId(rs.getInt("surgery_id"));
    surgery.setName(rs.getString("name"));
    surgery.setAuthorizationNeeded(UserType.setUserType(rs.getString("authorization_needed")));
    surgery.setRegularPrice(rs.getInt("regular_price"));
    surgery.setPrescriptionPrice(rs.getInt("prescription_price"));
    surgery.setDescription(rs.getString("description"));
    surgery.setDuration(rs.getInt("duration"));
    return surgery;
  }

}
