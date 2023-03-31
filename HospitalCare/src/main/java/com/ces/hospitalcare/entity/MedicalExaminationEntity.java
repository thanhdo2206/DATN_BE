package com.ces.hospitalcare.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "MedicalExaminations")
public class MedicalExaminationEntity extends BaseEntity {
  @Column
  private Long examinationPrice;

  @Column
  private String title;

  @Column(columnDefinition = "TEXT")
  private String shortDescription;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column
  private String image;

  @OneToMany(mappedBy = "medicalExamination")
  private List<TimeSlotEntity> timeSlots = new ArrayList<>();

  @OneToOne
  @JoinColumn(name = "doctorId")
  private UserEntity doctor;

  @ManyToOne
  @JoinColumn(name = "departmentId")
  private DepartmentEntity departmentEntity;

  @ManyToMany
  @JoinTable(
      name = "CategoriesMedicalExaminations",
      joinColumns = @JoinColumn(name = "examinationId"),
      inverseJoinColumns = @JoinColumn(name = "categoryId"))
  private List<CategoryEntity> listCategoryEntity = new ArrayList<>();
}
