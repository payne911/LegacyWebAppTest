package main.java.com.test.webApp.superWebApp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter @Setter
public class Person {
    private String email, firstName, lastName;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Person))
            return false;

        if(obj == this)
            return true;

        /* Same person only if same email+name. */
        Person p = (Person) obj;
        return (p.getEmail().equals(getEmail())
                && p.getFirstName().equals(getFirstName())
                && p.getLastName().equals(getLastName()));
    }
}
