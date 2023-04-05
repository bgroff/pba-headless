package io.airbyte.domain;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bakery {
  @Id
  @GeneratedValue
  Long id;

  String name;

  UUID workspaceId;

  Integer numberOfMembers;

  OffsetDateTime createdOn;

  OffsetDateTime updatedOn;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getWorkspaceId() {
    return workspaceId;
  }

  public void setWorkspaceId(UUID workspaceId) {
    this.workspaceId = workspaceId;
  }

  public Integer getNumberOfMembers() {
    return numberOfMembers;
  }

  public void setNumberOfMembers(Integer numberOfMembers) {
    this.numberOfMembers = numberOfMembers;
  }

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }
}
