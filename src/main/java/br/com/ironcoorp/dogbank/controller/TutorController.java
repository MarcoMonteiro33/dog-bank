package br.com.ironcoorp.dogbank.controller;


import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.dto.TutorDTO;
import br.com.ironcoorp.dogbank.error.ErrorMessageDetails;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import br.com.ironcoorp.dogbank.service.VerificaCPFProposta;
import br.com.ironcoorp.dogbank.service.VerificaConsistenciaDaInformacaoProposta;
import br.com.ironcoorp.dogbank.service.VerificaEmailProposta;
import br.com.ironcoorp.dogbank.service.VerificaIdadePermitida;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="v1")
public class TutorController {

    @Autowired
    TutorRepository tutorRepository;

    @PostMapping(path = "tutores")
    @ApiOperation(value = "Criar Solicitaçao de Proposta para conta digital: Etapa-1", tags = {"Proposta-Tutores"})
    @ApiResponses({
        @ApiResponse(code = 201, message = "Proposta enviada com Sucesso", response = Tutor.class),
        @ApiResponse(code = 400, message = "Erro na Solicitação", response = ErrorMessageDetails.class)})
    public  ResponseEntity<?> newTutor(@Valid @RequestBody TutorDTO tutorDto, HttpServletRequest request) throws EmailCadastradorException {

        VerificaConsistenciaDaInformacaoProposta verificaEmail = new VerificaEmailProposta(tutorRepository);
        VerificaConsistenciaDaInformacaoProposta verificaCPF = new VerificaCPFProposta(tutorRepository);
        VerificaConsistenciaDaInformacaoProposta validaIdade = new VerificaIdadePermitida();
        verificaEmail.validaInformacao(tutorDto);
        verificaCPF.validaInformacao(tutorDto);
        validaIdade.validaInformacao(tutorDto);
        Tutor tutor = TutorDTO.convertToTutor(tutorDto);
        Tutor tutorOptional = tutorRepository.save(tutor);

        URI location = ServletUriComponentsBuilder
                .fromServletMapping(request)
                .path("/tutores/{id}/etapa1")
                .build()
                .expand(tutorOptional.getCodigo()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(tutorOptional,headers,HttpStatus.CREATED);

    }



}
