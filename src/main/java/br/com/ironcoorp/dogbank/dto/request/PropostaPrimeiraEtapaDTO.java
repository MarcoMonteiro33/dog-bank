package br.com.ironcoorp.dogbank.dto.request;


import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.domain.builder.TutorBuilder;
import br.com.ironcoorp.dogbank.exception.CPFCadastradoException;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;


public class PropostaPrimeiraEtapaDTO {

    @NotBlank(message = "{nome.not.blank}")
    @Size(min =  3, max = 50, message = "{nome.size}")
    private String nome;

    @NotBlank(message = "{sobrenome.not.blank}")
    @Size(min =  5, max = 50, message = "{sobrenome.size}")
    private String sobrenome;

    @Email(message = "{email.invalido}")
    @NotBlank(message = "{email.not.blank}")
    private String email;

    @NotNull(message = "{dataNascimento.not.null}")
    @Past(message = "{dataNascimento.passado}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone="GMT-3")
    private LocalDate dataNascimento;

    @NotNull(message = "{cpf.not.null}")
    @CPF(message = "{cpf.error}")
    private String cpf;

     @NotNull(message = "{cnh.not.null}")
     @Size(min = 11, message = "{cnh.error}")
     private String cnh;


    public static Tutor convertToTutor(PropostaPrimeiraEtapaDTO propostaPrimeiraEtapaDTO) throws EmailCadastradorException, CPFCadastradoException {
        return new TutorBuilder()
                .nome(propostaPrimeiraEtapaDTO.nome)
                .sobrenome(propostaPrimeiraEtapaDTO.sobrenome)
                .email(propostaPrimeiraEtapaDTO.email)
                .dataNascimento(propostaPrimeiraEtapaDTO.dataNascimento)
                .cpf(propostaPrimeiraEtapaDTO.cpf)
                .cnh(propostaPrimeiraEtapaDTO.cnh)
                .build();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropostaPrimeiraEtapaDTO)) return false;
        PropostaPrimeiraEtapaDTO propostaPrimeiraEtapaDTO = (PropostaPrimeiraEtapaDTO) o;
        return getCpf() == propostaPrimeiraEtapaDTO.getCpf() &&
                Objects.equals(getEmail(), propostaPrimeiraEtapaDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, cpf);
    }

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
