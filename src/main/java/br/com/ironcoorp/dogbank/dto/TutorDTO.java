package br.com.ironcoorp.dogbank.dto;


import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.domain.builder.TutorBuilder;
import br.com.ironcoorp.dogbank.exception.CPFCadastradoException;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;


public class TutorDTO {

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone="GMT-3")
    private LocalDate dataNascimento;

    @NotNull(message = "{cpf.not.null}")
    @CPF(message = "{cpf.error}")
    private String cpf;

     @NotNull(message = "{cnh.not.null}")
     @Size(min = 11, message = "{cnh.error}")
     private String cnh;


    public static Tutor convertToTutor(TutorDTO tutorDTO) throws EmailCadastradorException, CPFCadastradoException {
        return new TutorBuilder()
                .nome(tutorDTO.nome)
                .sobrenome(tutorDTO.sobrenome)
                .email(tutorDTO.email)
                .dataNascimento(tutorDTO.dataNascimento)
                .cpf(tutorDTO.cpf)
                .cnh(tutorDTO.cnh)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TutorDTO)) return false;
        TutorDTO tutorDTO = (TutorDTO) o;
        return getCpf() == tutorDTO.getCpf() &&
                Objects.equals(getEmail(), tutorDTO.getEmail());
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

    public @NotNull(message = "{cnh.not.null}") @Size(min = 11, message = "{cnh.error}") String getCnh() {
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
    public void setCnh(@NotNull(message = "{cnh.not.null}") @Size(min = 11, message = "{cnh.error}") String cnh) {
        this.cnh = cnh;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
