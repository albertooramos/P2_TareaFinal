package alberto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.File;

@Entity
@NoArgsConstructor
public class Peticion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPeticion;

    @NonNull
    private String ambito;

    @NonNull
    private String tema;

    @NonNull
    private String titulo;

    @NonNull
    private String descripcion;

    @NonNull
    private String estado;

    @Nullable
    private String fileName;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idFile")
    private FileDB filePeticion;

    public int getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(int idPeticion) {
        this.idPeticion = idPeticion;
    }

    @NonNull
    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(@NonNull String ambito) {
        this.ambito = ambito;
    }

    @NonNull
    public String getTema() {
        return tema;
    }

    public void setTema(@NonNull String tema) {
        this.tema = tema;
    }

    @NonNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    public String getEstado() {
        return estado;
    }

    public void setEstado(@NonNull String estado) {
        this.estado = estado;
    }

    @Nullable
    public String getFileName() {
        return fileName;
    }

    public void setFileName(@Nullable String fileName) {
        this.fileName = fileName;
    }

    public void setFilePeticion(FileDB filePeticion) {
        this.filePeticion = filePeticion;
    }
}
