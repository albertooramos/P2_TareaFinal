package alberto.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFile;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data, Peticion peticion) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.peticion = peticion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getId_file() {
        return idFile;
    }

    public void setId_file(int id_file) {
        this.idFile = id_file;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }

    @OneToOne
    @JoinColumn(name = "idPeticion")
    private Peticion peticion;
}