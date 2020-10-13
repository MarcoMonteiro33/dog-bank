package br.com.ironcoorp.dogbank.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class Disco {

    @Value("${diretorio.upload.proposta}")
    private String diretorio;

    @Value("${upload.documentos}")
    private String folder;


    public String salvar(MultipartFile arquivo) {
        return this.salvarNoDisco(this.folder, arquivo);
    }

    public String salvarNoDisco(String folder, MultipartFile arquivo) {

        Path diretorioPath = Paths.get(this.diretorio, folder);
        Path arquivoPath = diretorioPath.resolve(UUID.randomUUID()+"_"+arquivo.getOriginalFilename());

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao Salvar arquivo", e);
        }
        return arquivoPath.toString();
    }

}
