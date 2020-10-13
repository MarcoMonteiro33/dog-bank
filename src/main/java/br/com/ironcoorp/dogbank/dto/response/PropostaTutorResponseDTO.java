package br.com.ironcoorp.dogbank.dto.response;


import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Component
public class PropostaTutorResponseDTO implements Serializable {


    public PropostaTutorResponseDTO convertToDTO(Tutor tutor){
        this.nome = tutor.getNome();
        this.sobrenome = tutor.getSobrenome();
        this.dataNascimento = tutor.getDataNascimento();
        this.cpf = tutor.getCpf();
        this.email = tutor.getEmail();
        this.cnh = tutor.getCnh();
        this.cep = tutor.getEndereco().getCep();
        this.rua = tutor.getEndereco().getRua();
        this.complemento = tutor.getEndereco().getComplemento();
        this.bairro = tutor.getEndereco().getBairro();
        this.cidade = tutor.getEndereco().getCidade();
        this.uf = tutor.getEndereco().getUf();
        this.anexoDocumento = tutor.getDocumento().getAnexoDocumento();
        return this;
    }

    private String nome;

    private String sobrenome;

    private String email;

    private  String cpf;

    private String cnh;

    private StatusProposta statusProposta;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone="GMT-3")
    private LocalDate dataNascimento;

    private String cep;

    private String rua;

    private String bairro;

    private String complemento;

    private String cidade;

    private String uf;

    private Boolean anexoDocumento = false;

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public Boolean getAnexoDocumento() {
        return anexoDocumento;
    }
}
