package hepl.faad.model.entity;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Patient implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Patient(Integer id, String firstName, String lastName, LocalDate birthDate) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
    }

    public Patient() {
    }

    Patient clone(Patient obj){
        return new  Patient(obj.id, obj.firstName, obj.lastName, obj.birthDate);
    }

    public String toString() {
        return getLastName() + " " + getFirstName() + "(" + getId() + ")" ;
    }

}
