package br.com.ironcoorp.dogbank.controller;


import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.dto.response.EtapaResponseDTO;
import br.com.ironcoorp.dogbank.dto.response.PropostaTutorResponseDTO;
import br.com.ironcoorp.dogbank.dto.request.PropostaPrimeiraEtapaDTO;
import br.com.ironcoorp.dogbank.dto.request.PropostaSegundaEtapaDTO;
import br.com.ironcoorp.dogbank.error.ErrorMessageDetails;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import br.com.ironcoorp.dogbank.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="v1")
public class TutorController {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    PrimeiraEtapaService primeiraEtapaService;

    @Autowired
    SegundaEtapaService segundaEtapaService;

    @Autowired
    TerceiraEtapaService terceiraEtapaService;

    @PostMapping(path = "tutores/etapa1")
    @ApiOperation(value = "Criar Solicitaçao de Proposta para conta digital: Etapa-1", tags = {"Proposta-Tutores"})
    @ApiResponses({
        @ApiResponse(code = 201, message = "Dados enviados com Sucesso", response = EtapaResponseDTO.class),
        @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class)
    })
    public  ResponseEntity<?> criarProposta(@Valid @RequestBody PropostaPrimeiraEtapaDTO propostaPrimeiraEtapaDto
                                            ,HttpServletRequest request) throws EmailCadastradorException {

        EtapaResponseDTO responseDTO = primeiraEtapaService.processa(propostaPrimeiraEtapaDto);
        URI location = proximaEtapa(responseDTO.getIdRecurso(), "/tutores/{id}/etapa2",request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(responseDTO);
    }

    @PutMapping(value = "tutores/{id}/etapa2")
    @ApiOperation(value = "Continuar criação da Solicitaçao de Proposta para conta digital: Etapa-2", tags = {"Proposta-Tutores"})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Dados enviados com Sucesso", response = EtapaResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class),
            @ApiResponse(code = 404, message = "A Proposta não foi localizada", response = ErrorMessageDetails.class)
            })
    public ResponseEntity<?> newTutorSegundaEtapa(@PathVariable(name = "id") Long id,
                                                  @Valid
                                                  @RequestBody PropostaSegundaEtapaDTO propostaSegundaEtapaDTO,
                                                  HttpServletRequest request){

        EtapaResponseDTO responseDTO = segundaEtapaService.processar(propostaSegundaEtapaDTO,id);
        URI location = proximaEtapa(responseDTO.getIdRecurso(),"/tutores/{id}/etapa3/foto",request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(responseDTO);
    }

    @PutMapping(value = "tutores/{id}/etapa3/foto", consumes = {"multipart/form-data"})
    @ApiOperation(value = "Upload de arquivo para Solic. de Proposta de conta digital: Etapa-3", tags = {"Proposta-Tutores"})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Arquivo enviado com Sucesso", response = EtapaResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class),
            @ApiResponse(code = 404, message = "A Proposta não foi localizada", response = ErrorMessageDetails.class)
    })
    public ResponseEntity<?> newPropostaTerceiraEtapa(@PathVariable(name = "id") Long id,
                                                      MultipartFile arquivoUpload,
                                                      HttpServletRequest request)  {

        EtapaResponseDTO responseDTO = terceiraEtapaService.processar(id, arquivoUpload);
        URI location = proximaEtapa(responseDTO.getIdRecurso(),"/tutores/{id}/etapa4/aceite",request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(responseDTO);
    }

    public URI proximaEtapa(Long id, String proximaEtapa, HttpServletRequest request){
       return ServletUriComponentsBuilder
                .fromServletMapping(request)
                .path(proximaEtapa)
                .build()
                .expand(id).toUri();
    }



}
