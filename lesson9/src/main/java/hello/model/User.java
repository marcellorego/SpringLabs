package hello.model;

import javax.persistence.Entity;
import javax.persistence.Table;

 // This tells Hibernate to make a table out of this class
@Entity
@Table(name = "user")
public class User extends BaseUser {

}