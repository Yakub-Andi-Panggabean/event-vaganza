package com.special.gift.app.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = Role.TABLE_NAME)
public class Role {

  public static final String TABLE_NAME = "role";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(columnDefinition = "CHAR(32)")
  private String id;

  @Column(name = "role_name", nullable = false)
  private String roleName;

  @Column(name = "active", nullable = false)
  private boolean active;

  @Column(name = "created_datetime", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "updated_datetime", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;

  @Column(name = "updated_by", nullable = true)
  private String updatedBy;

  @Column(name = "description", nullable = false)
  private String description;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role",
      joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
  private List<User> users;

  public Role() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Role(String id, String roleName, boolean active, Date createdDate, String createdBy,
      Date updatedDate, String updatedBy, String description, List<User> users) {
    super();
    this.id = id;
    this.roleName = roleName;
    this.active = active;
    this.createdDate = createdDate;
    this.createdBy = createdBy;
    this.updatedDate = updatedDate;
    this.updatedBy = updatedBy;
    this.description = description;
    this.users = users;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Role [id=" + id + ", roleName=" + roleName + ", active=" + active + ", createdDate="
        + createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", updatedBy="
        + updatedBy + ", description=" + description + ", users=" + users + "]";
  }



}
