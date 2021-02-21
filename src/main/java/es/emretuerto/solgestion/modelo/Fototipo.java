package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "FOTOTIPOS")
public class Fototipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FOTOTIPO", length = 3, nullable = false, unique = true)
    private String fototipo;

    public Fototipo(String fototipo) {
        this.fototipo = fototipo;
    }

    public Fototipo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFototipo() {
        return fototipo;
    }

    public void setFototipo(String fototipo) {
        this.fototipo = fototipo;
    }

    @Override
    public String toString() {
        return "Fototipo{" + "fototipo=" + fototipo + '}';
    }

}
