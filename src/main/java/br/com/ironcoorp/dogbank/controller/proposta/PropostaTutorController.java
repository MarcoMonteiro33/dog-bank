package br.com.ironcoorp.dogbank.controller.proposta;


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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="v1")
public class PropostaTutorController {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    PrimeiraEtapaService primeiraEtapaService;

    @Autowired
    SegundaEtapaService segundaEtapaService;

    @Autowired
    TerceiraEtapaService terceiraEtapaService;

    @Autowired
    QuartaEtapaService quartaEtapaService;

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
    @ApiOperation(value = "Complemento de informações para Solicitaçao de Proposta para conta digital: Etapa-2", tags = {"Proposta-Tutores"})
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

    @PutMapping(value = "tutores/{id}/etapa3/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
        URI location = proximaEtapa(responseDTO.getIdRecurso(),"/tutores/{id}/etapa4/dados",request);

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

    @GetMapping(value = "tutores/{id}/etapa4/dados")
    @ApiOperation(value = "Valida as Informações enviadas: Etapa-4", tags = {"Proposta-Tutores"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Visualiza todos os dados informados", response = PropostaTutorResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class),
            @ApiResponse(code = 404, message = "A Proposta não foi localizada", response = ErrorMessageDetails.class)
    })
    public ResponseEntity<?> newPropostaQuartaEtapa(@PathVariable(name = "id") Long id,
                                                      HttpServletRequest request)  {

        PropostaTutorResponseDTO responseDTO = quartaEtapaService.processar(id);
        URI location = proximaEtapa(id,"/tutores/{id}/etapa4/aceite",request);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(responseDTO);
    }

    @PutMapping(value = "tutores/{id}/etapa4/dados/{aceite}")
    @ApiOperation(value = "Finaliza Solicitaçao da Proposta: Etapa-4", tags = {"Proposta-Tutores"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Solicitaçao de Proposta Finalizada", response = EtapaResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class),
            @ApiResponse(code = 404, message = "A Proposta não foi localizada", response = ErrorMessageDetails.class)
    })
    public ResponseEntity<?> newPropostaQuartaEtapaAceite(@PathVariable(name = "id") Long id,
                                                          @PathVariable(name = "aceite") Boolean aceite,
                                                    HttpServletRequest request)  {

        EtapaResponseDTO responseDTO = quartaEtapaService.finalizaProposta(id, aceite);
        URI location = proximaEtapa(id,"/tutores/{id}/etapa4/aceite",request);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(responseDTO);
    }


}
