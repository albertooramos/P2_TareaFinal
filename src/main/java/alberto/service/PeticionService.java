package alberto.service;

import alberto.model.Peticion;
import alberto.repository.FileDBRepository;
import alberto.repository.PeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeticionService {

    @Autowired
    private PeticionRepository peticionRepository;

    public Peticion getPeticion(int id) {
        return peticionRepository.findById(id).get();
    }


    public Page<Peticion> getPeticionesAceptadas(Pageable pageable, @SearchSpec Specification<Peticion> specs) {
        return peticionRepository.findAll((Pageable) getPeticionesAceptadas(pageable, specs));

    }

    public Iterable<Peticion> getPeticionesByTema(String tema) {
        return peticionRepository.getPeticionByTema();
    }

    public Iterable<Peticion> getPeticionesByTitulo(String titulo) {
        return peticionRepository.getPeticionByTitulo();
    }

    public Iterable<Peticion> getPeticionesByAmbito(String ambito) {
        return peticionRepository.getPeticionByAmbito();
    }

    public boolean addPeticion(Peticion peticion) {
        try {
            peticionRepository.save(peticion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updatePeticion(Peticion peticion) {
        try {
            peticionRepository.save(peticion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarPeticion(Peticion peticion) {
        try {
            peticionRepository.delete(peticion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeEstado(Peticion peticion) {
        peticion.setEstado("aceptada");
        try {
            peticionRepository.save(peticion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
