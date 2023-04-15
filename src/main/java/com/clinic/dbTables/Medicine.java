package com.clinic.dbTables;

import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

@Data
public class Medicine {
  private int medicineId;
  private String name;
  private String description;
  private int regularPrice;
  private int prescriptionPrice;

  @NotNull
  @SneakyThrows
  public static Medicine getMedicineFromResultSet(ResultSet rs) {
    Medicine medicine = new Medicine();
    medicine.setMedicineId(rs.getInt("medicine_id"));
    medicine.setName(rs.getString("name"));
    medicine.setDescription(rs.getString("description"));
    medicine.setRegularPrice(rs.getInt("regular_price"));
    medicine.setPrescriptionPrice(rs.getInt("prescription_price"));
    return medicine;
  }
}
