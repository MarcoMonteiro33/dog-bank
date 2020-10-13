package br.com.ironcoorp.dogbank.domain;

import br.com.ironcoorp.dogbank.state.*;
import br.com.ironcoorp.dogbank.utils.GeraInstanciaStatus;
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

    public Tutor(String nome, String sobrenome, String email, String cpf, String cnh, LocalDate dataNascimento, StatusProposta statusProposta, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cpf = cpf;
        this.cnh = cnh;
        this.dataNascimento = dataNascimento;
        this.statusProposta = statusProposta;
        this.endereco = endereco;
        this.propostaState = new PropostaIniciadaState();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String sobrenome;

    private String email;

    private  String cpf;

    private String cnh;

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @Transient
    private PropostaState propostaState;

    @Embedded
    private Endereco endereco;

    @OneToOne
    private Documento documento;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public void iniciaProposta(){
        propostaState.iniciaProposta(this);
    }

    public void complementoProposta(){
        propostaState = criarPropostaState();
        propostaState.complementoProposta(this);
    }

    public void andamentoProposta(){
        propostaState = criarPropostaState();
        propostaState.andamentoProposta(this);
    }

    public void aceiteProposta(){
        propostaState = criarPropostaState();
        propostaState.aceiteProposta(this);
    }

    public void concluiProposta(){
        propostaState = criarPropostaState();
        propostaState.finalizaProposta(this);
    }

    public PropostaState criarPropostaState(){
        return GeraInstanciaStatus.criaInstancia(this.statusProposta);
    }

}
