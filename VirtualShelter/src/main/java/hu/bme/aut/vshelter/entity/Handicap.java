package hu.bme.aut.vshelter.entity;

import javax.persistence.*;

@Entity
@Table(name = "handicap", catalog = "vshelter", uniqueConstraints = {
        @UniqueConstraint(columnNames = "handicapName")})
public class Handicap {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "handicapName", unique = true, nullable = false)
    private String handicapName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHandicapName() {
        return handicapName;
    }

    public void setHandicapName(String handicapName) {
        this.handicapName = handicapName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Handicap other = (Handicap) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
