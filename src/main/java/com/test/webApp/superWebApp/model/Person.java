package com.test.webApp.superWebApp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/* [START] Lombok. */
@AllArgsConstructor
@NoArgsConstructor
@Data
/* [END] Lombok. */

@Entity
@Table(name = "USERS", schema = "PUBLIC_TEST")
public class Person implements Serializable {

    @Id
    private String email;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
