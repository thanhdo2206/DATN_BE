package com.ces.hospitalcare.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Departments")
public class DepartmentEntity extends BaseEntity {
  @Column
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column
  private String backgroundImage;

  @OneToMany(mappedBy = "departmentEntity")
  private List<MedicalExaminationEntity> listMedicalExaminationEntity = new ArrayList<>();
}
