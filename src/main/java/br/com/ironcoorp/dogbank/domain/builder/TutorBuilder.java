package br.com.ironcoorp.dogbank.domain.builder;

import br.com.ironcoorp.dogbank.domain.Etapa;
import br.com.ironcoorp.dogbank.domain.Tutor;

import java.time.LocalDate;

public class TutorBuilder {

    private String nome;

    private String sobrenome;

    private LocalDate dataNascimento;

    private String cpf;

    private String email;

    private  String cnh;

    private Etapa etapa;

    public TutorBuilder nome(String nome){
        this.nome = nome;
        return this;
    }

    public TutorBuilder sobrenome(String sobrenome){
        this.sobrenome = sobrenome;
        return this;
    }

   public TutorBuilder dataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public TutorBuilder cpf(String cpf){
        this.cpf = cpf;
        return this;
    }

    public TutorBuilder email(String email){
        this.email = email;
        return this;
    }

    public TutorBuilder cnh(String cnh){
        this.cnh = cnh;
        return this;
    }

    public TutorBuilder status(Etapa etapa){
        this.etapa = etapa;
        return this;
    }

    public Tutor build(){
        return new Tutor(nome, sobrenome,email,cpf, cnh, dataNascimento, etapa) ;
    }


}
