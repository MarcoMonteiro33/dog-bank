package br.com.ironcoorp.dogbank.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tutor")
public class Tutor {

    public Tutor() {
    }

    public Tutor(String nome, String sobrenome, String email, String cpf, String cnh, LocalDate dataNascimento, Etapa etapa) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cpf = cpf;
        this.cnh = cnh;
        this.dataNascimento = dataNascimento;
        this.etapa = etapa;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String sobrenome;

    private String email;

    private  String cpf;

    private String cnh;

    private Etapa etapa;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone="GMT-3")
    private LocalDate dataNascimento;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone="GMT-3")
    private LocalDateTime dataCriação;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone="GMT-3")
    private LocalDateTime dataAlteracao;

    public Long getCodigo() {
        return codigo;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalDateTime getDataCriação() {
        return dataCriação;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public Etapa getStatus() {
        return etapa;
    }
}
