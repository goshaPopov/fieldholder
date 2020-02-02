package ru.popov.sixthgrain.fieldholder.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "FIELD")
public class Field extends AbstractEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FIELD_SEQ")
    @SequenceGenerator(name = "FIELD_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Size(min = 2)
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "lat")
    private Double lat;

    @NotNull
    @Column(name = "lon")
    private Double lon;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Field() {
    }

    public Field(@Size(min = 2) String name, @NotBlank Double lat, @NotBlank Double lon, @NotNull Account account) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.account = account;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;
        Field field = (Field) o;
        return Objects.equals(id, field.id) &&
                Objects.equals(name, field.name) &&
                Objects.equals(account, field.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, account);
    }
}
