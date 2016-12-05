package com.special.gift.app.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = ApplicationMenu.TABLE_NAME)
public class ApplicationMenu {

  public static final String TABLE_NAME = "application_menu";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(columnDefinition = "CHAR(32)")
  private String id;

  @Column(name = "label", unique = true, nullable = false)
  private String label;

  @Column(name = "url", unique = true, nullable = false)
  private String url;

  @Column(name = "active", nullable = false)
  private boolean active;

  @Column(name = "created_datetime", columnDefinition = "DATETIME")
  @Temporal(TemporalType.DATE)
  private Date createdDate;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "updated_datetime", columnDefinition = "DATETIME")
  @Temporal(TemporalType.DATE)
  private Date updatedDate;

  @Column(name = "updated_by", nullable = true)
  private String updatedBy;

  @Column(name = "description", nullable = false)
  private String description;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "parent_id")
  private ApplicationMenu parent;

  @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
  private List<ApplicationMenu> children;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "role_menu",
      joinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
  private List<Role> role;


  public ApplicationMenu() {
    super();
    // TODO Auto-generated constructor stub
  }

  public ApplicationMenu getParent() {
    return parent;
  }

  public void setParent(ApplicationMenu parent) {
    this.parent = parent;
  }

  public List<Role> getRole() {
    return role;
  }

  public void setRole(List<Role> role) {
    this.role = role;
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

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public List<ApplicationMenu> getChildren() {
    return children;
  }

  public void setChildren(List<ApplicationMenu> children) {
    this.children = children;
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

  @Override
  public String toString() {
    return "ApplicationMenu [id=" + id + ", label=" + label + ", url=" + url + ", active=" + active
        + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate="
        + updatedDate + ", updatedBy=" + updatedBy + ", description=" + description + ", parent="
        + parent + ", children=" + children + ", role=" + role + "]";
  }



}
