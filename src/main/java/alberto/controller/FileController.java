package alberto.controller;

import java.util.List;
import java.util.stream.Collectors;

import alberto.model.FileDB;
import alberto.model.Peticion;
import alberto.service.FileStorageService;
import alberto.service.PeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileStorageService storageService;
    @Autowired
    private PeticionService peticionService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("peticion") int idPeticion) {
        String message = "";
        try {
            Peticion peticion = peticionService.getPeticion(idPeticion);
            FileDB filedb = storageService.store(file, peticion);
            peticion.setFilePeticion(filedb);
            peticion.setFileName(filedb.getName());
            peticionService.updatePeticion(peticion);

            message = "Archivo guardado con éxito " + file.getOriginalFilename();
            return message;
        } catch (Exception e) {
            message = "No se pudo guardar el archivo " + file.getOriginalFilename() + "!";
            return message;
        }
    }
}
