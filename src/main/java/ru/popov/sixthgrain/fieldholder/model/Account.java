package ru.popov.sixthgrain.fieldholder.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
public class Account extends AbstractEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @SequenceGenerator(name = "ACCOUNT_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Size(min = 2)
    @Column(name = "name", unique = true)
    private String name;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Field> fields = new HashSet<>();

    public Account() {
    }

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email);
    }
}
