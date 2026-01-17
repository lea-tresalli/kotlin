package hepl.faad.model.entity;

import java.io.Serializable;

public class Doctor implements Serializable {

    private Integer id;
    private Specialty specialty;
    private String last_name;

    private String first_name;

    public Doctor() {

    }
    public Doctor(Integer id) {
        this.id = id;
        this.specialty = null;
        this.last_name = null;
        this.first_name = null;

    }

    public Doctor(Integer id, Specialty specialty, String last_name, String first_name) {
        this.id = id;
        this.specialty = specialty;
        this.last_name = last_name;
        this.first_name = first_name;

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Specialty getSpecialty() {
        return specialty;
    }
    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String toString() {
        return "Doctor [id=" + id + ", specialty=" + specialty + ", last_name=" + last_name + ", first_name=" + first_name + "]";
    }

}
