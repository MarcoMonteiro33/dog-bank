package br.com.ironcoorp.dogbank.domain.builder;

import br.com.ironcoorp.dogbank.domain.Endereco;
import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;

import java.time.LocalDate;

public class TutorBuilder {

    private String nome;

    private String sobrenome;

    private LocalDate dataNascimento;

    private String cpf;

    private String email;

    private  String cnh;

    private StatusProposta statusProposta;

    private Endereco endereco;

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

    public TutorBuilder statusProposta(StatusProposta statusProposta){
        this.statusProposta = statusProposta;
        return this;
    }

    public TutorBuilder endereco(Endereco endereco){
        this.endereco = endereco;
        return this;
    }

    public Tutor build(){
        return new Tutor(nome, sobrenome,email,cpf, cnh, dataNascimento, statusProposta,endereco) ;
    }


}
