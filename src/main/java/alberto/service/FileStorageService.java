package alberto.service;

import java.io.IOException;
import java.util.stream.Stream;

import alberto.model.FileDB;
import alberto.model.Peticion;
import alberto.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file, Peticion peticion) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(),peticion);

        return fileDBRepository.save(FileDB);
    }
}
