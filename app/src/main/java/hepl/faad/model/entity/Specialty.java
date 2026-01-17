package hepl.faad.model.entity;

import java.io.Serializable;

public class Specialty implements Cloneable, Serializable {
    private Integer id;
    private String name;


    public Specialty(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    Specialty clone(Specialty obj) {
        return new Specialty(obj.getId(), obj.getName());
    }

    @Override
    public String toString() {
        return "Specialty [id=" + id + ", name=" + name + "]";
    }
}
