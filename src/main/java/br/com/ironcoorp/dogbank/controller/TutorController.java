package br.com.ironcoorp.dogbank.controller;


import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.dto.PropostaTerceiraEtapaDTO;
import br.com.ironcoorp.dogbank.dto.TutorDTO;
import br.com.ironcoorp.dogbank.dto.TutorSegundaEtapaDTO;
import br.com.ironcoorp.dogbank.error.ErrorMessageDetails;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import br.com.ironcoorp.dogbank.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="v1")
public class TutorController {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    SegundaEtapaService segundaEtapaService;

    @Autowired
    TerceiraEtapaService terceiraEtapaService;

    @PostMapping(path = "tutores/etapa1")
    @ApiOperation(value = "Criar Solicitaçao de Proposta para conta digital: Etapa-1", tags = {"Proposta-Tutores"})
    @ApiResponses({
        @ApiResponse(code = 201, message = "Proposta enviada com Sucesso", response = Tutor.class),
        @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class)})
    public  ResponseEntity<?> newTutor(@Valid @RequestBody TutorDTO tutorDto, HttpServletRequest request) throws EmailCadastradorException {

        AnaliseDados verificaEmail = new VerificaEmailProposta(tutorRepository);
        AnaliseDados verificaCPF = new VerificaCPFProposta(tutorRepository);
        AnaliseDados validaIdade = new VerificaIdadePermitida();
        verificaEmail.validaInformacao(tutorDto);
        verificaCPF.validaInformacao(tutorDto);
        validaIdade.validaInformacao(tutorDto);
        Tutor tutor = TutorDTO.convertToTutor(tutorDto);
        tutor.setStatusProposta(StatusProposta.ANDAMENTO);
        Tutor tutorOptional = tutorRepository.save(tutor);

        URI location = ServletUriComponentsBuilder
                .fromServletMapping(request)
                .path("/tutores/{id}/etapa2")
                .build()
                .expand(tutorOptional.getCodigo()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(tutorOptional,headers,HttpStatus.CREATED);

    }

    @PutMapping(value = "tutores/{id}/etapa2")
    @ApiOperation(value = "Continuar criação da Solicitaçao de Proposta para conta digital: Etapa-2", tags = {"Proposta-Tutores"})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Proposta enviada com Sucesso", response = Tutor.class),
            @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class)})
    public ResponseEntity<?> newTutorSegundaEtapa(@PathVariable(name = "id") Long id,
                                                  @Valid
                                                  @RequestBody TutorSegundaEtapaDTO tutorSegundaEtapaDTO,
                                                  HttpServletRequest request){

        Tutor tutor = segundaEtapaService.processar(tutorSegundaEtapaDTO,id);

        URI location = ServletUriComponentsBuilder
                .fromServletMapping(request)
                .path("/tutores/{id}/etapa3/foto")
                .build()
                .expand(tutor.getCodigo()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(tutor,headers,HttpStatus.CREATED);

    }
    @PutMapping(value = "tutores/{id}/etapa3/foto", consumes = {"multipart/form-data"})
    @ApiOperation(value = "Upload arquivo para criação da Solic. de Proposta para conta digital: Etapa-3", tags = {"Proposta-Tutores"})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Arquivo enviado com Sucesso", response = Tutor.class),
            @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class),
            @ApiResponse(code = 404, message = "A Solicitação da Proposta não foi localizada", response = ErrorMessageDetails.class)
    })
    public ResponseEntity<?> newPropostaTerceiraEtapa(@PathVariable(name = "id") Long id,
                                                      MultipartFile arquivoUpload,
                                                      HttpServletRequest request)  {

            Tutor tutor = terceiraEtapaService.processar(id, arquivoUpload);

        URI location = ServletUriComponentsBuilder
                .fromServletMapping(request)
                .path("/tutores/{id}/etapa4/aceite")
                .build()
                .expand(id).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(tutor,headers,HttpStatus.CREATED);

    }



}
