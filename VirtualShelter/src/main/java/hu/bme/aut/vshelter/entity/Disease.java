package hu.bme.aut.vshelter.entity;

import javax.persistence.*;

@Entity
@Table(name = "disease", catalog = "vshelter", uniqueConstraints = {
        @UniqueConstraint(columnNames = "diseaseName")})
public class Disease {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "diseaseName", unique = true, nullable = false)
    private String diseaseName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiceaseName() {
        return diseaseName;
    }

    public void setDiceaseName(String diceaseName) {
        this.diseaseName = diceaseName;
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
        Disease other = (Disease) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
