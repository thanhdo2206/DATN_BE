package com.ces.hospitalcare.entity;
import com.ces.hospitalcare.util.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@Data
@AllArgsConstructor
@Table(name = "Users")
public class UserEntity extends BaseEntity implements UserDetails {
  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String profilePicture;

  @Column
  private String password;

  @Column
  private String email;

  @Column
  private String address;

  @Column
  private String phoneNumber;

  @Column
  private Boolean gender;

  @Column
  private Role role;

  @OneToMany(mappedBy = "patient")
  private List<AppointmentEntity> listAppointment = new ArrayList<>();

  @OneToMany(mappedBy = "doctor")
  private List<AppointmentEntity> listAppointmentDoctor = new ArrayList<>();

  @OneToOne(mappedBy = "doctor")
  private MedicalExaminationEntity medicalExaminationEntity;

  @OneToMany(mappedBy = "doctor")
  private List<TimeSlotEntity> listTimeSlotEntity = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
