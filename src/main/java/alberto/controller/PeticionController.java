package alberto.controller;

import alberto.model.Peticion;
import alberto.service.FileStorageService;
import alberto.service.PeticionService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/peticion")
public class PeticionController {
    @Autowired
    private PeticionService peticionService;

    @GetMapping("/getPeticionById")
    public @ResponseBody Peticion getPeticionById(@RequestParam(name = "id") int id) {
        return peticionService.getPeticion(id);
    }

    /*@GetMapping("/getPeticionByAmbito")
    public @ResponseBody Peticion getPeticionByAmbito(@RequestParam(name = "ambito") String ambito) {
        return getPeticionByAmbito(ambito);
    }

    @GetMapping("/getPeticionByTema")
    public @ResponseBody Peticion getPeticionByTema(@RequestParam(name = "tema") String tema) {
        return getPeticionByTema(tema);
    }

    @GetMapping("/getPeticionByTitulo")
    public @ResponseBody Peticion getPeticionByTitulo(@RequestParam(name = "titulo") String titulo) {
        return getPeticionByTitulo(titulo);
    }*/

    @GetMapping("/getPeticionesPendientes")
    public @ResponseBody Page<Peticion> getPeticionesPendientes(@PageableDefault(size = 5, page = 0) Pageable pageable, @SearchSpec Specification<Peticion> specs) {
        return peticionService.getPeticionesAceptadas(pageable, specs);
    }

    /*@GetMapping("/getPeticionesConFiltro")
    public @ResponseBody Iterable<Peticion> getPeticionesConFiltro(@RequestParam(name = "ambito", required = false) String ambito,
                                                                   @RequestParam(name = "tema", required = false) String tema,
                                                                   @RequestParam(name = "titulo", required = false) String titulo) {
        return peticionService.getPeticionesPorFiltro(ambito, tema, titulo);
    }*/

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addPeticion(@RequestParam("ambito") String ambito,
                              @RequestParam("tema") String tema,
                              @RequestParam("titulo") String titulo,
                              @RequestParam("descripcion") String descripcion) {
        String message = "";
        try {
            Peticion peticion = new Peticion();
            peticion.setEstado("pendiente");
            peticion.setAmbito(ambito);
            peticion.setTema(tema);
            peticion.setTitulo(titulo);
            peticion.setDescripcion(descripcion);

            peticionService.addPeticion(peticion);

            message = "Peticion creada con exito";
            return message;
        } catch (Exception e) {
            message = "No se pudo crear la peticion";
            return message;
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updatePeticion(@RequestParam(name = "id") int id,
                                 @RequestParam(name = "ambito", required = false) String ambito,
                                 @RequestParam(name = "tema", required = false) String tema,
                                 @RequestParam(name = "titulo", required = false) String titulo,
                                 @RequestParam(name = "descripcion", required = false) String descripcion) {
        String message = "";
        try {
            Peticion peticion = peticionService.getPeticion(id);
            peticion.setEstado("pendiente");
            if (ambito != null) peticion.setAmbito(ambito);
            if (tema != null) peticion.setTema(tema);
            if (titulo != null) peticion.setTitulo(titulo);
            if (descripcion != null) peticion.setDescripcion(descripcion);

            peticionService.updatePeticion(peticion);

            message = "Peticion actualizada con exito";
            return message;
        } catch (Exception e) {
            message = "No se pudo actualizar la peticion";
            return message;
        }
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePeticion(@RequestParam("id") int id) {
        String message = "";
        try {
            Peticion peticion = peticionService.getPeticion(id);

            peticionService.eliminarPeticion(peticion);

            message = "Peticion eliminada con exito";
            return message;
        } catch (Exception e) {
            message = "No se pudo eliminar la peticion";
            return message;
        }
    }

    @PostMapping("/changeEstado")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String changeEstado(@RequestParam("id") int id) {
        String message = "";
        try {
            Peticion peticion = peticionService.getPeticion(id);

            peticionService.changeEstado(peticion);

            message = "El estado de la petición ha sido cambiado con exito";
            return message;
        } catch (Exception e) {
            message = "No se pudo cambiar el estado de la peticion";
            return message;
        }
    }
}
