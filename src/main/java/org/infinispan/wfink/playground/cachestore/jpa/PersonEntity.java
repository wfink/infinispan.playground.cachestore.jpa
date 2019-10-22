package org.infinispan.wfink.playground.cachestore.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  String identifier;
  String firstName;
  String lastName;
  Date creation;

  PersonEntity() {
  }

  public PersonEntity(String identifier, String firstName, String lastName) {
    super();
    this.identifier = identifier;
    this.firstName = firstName;
    this.lastName = lastName;
    this.creation = new Date();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getIdentifier() {
    return identifier;
  }

  @Override
  public String toString() {
    return "PersonEntity [identifier=" + identifier + ", firstName=" + firstName + ", lastName=" + lastName + ", creation=" + creation + "]";
  }

}
