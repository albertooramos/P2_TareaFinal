package alberto.repository;

import alberto.model.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PeticionRepository extends JpaRepository<Peticion, Integer>, JpaSpecificationExecutor<Peticion> {
    /*Iterable<Peticion> getPeticionByAmbito(String ambito);
     Iterable<Peticion> getPeticionByTema(String tema);
     Iterable<Peticion> getPeticionByTitulo(String titulo);*/
}
