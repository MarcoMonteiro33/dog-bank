package br.com.ironcoorp.dogbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Documento {

    public Documento(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
        this.anexoDocumento = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore(value = true)
    private Long codigo;

    @JsonIgnore(value = true)
    private String nome;

    @JsonIgnore(value = true)
    private String documento;

    private Boolean anexoDocumento = false;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    public Documento() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento documento = (Documento) o;
        return getCodigo().equals(documento.getCodigo()) &&
                getNome().equals(documento.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo(), getNome());
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Boolean getAnexoDocumento() {
        return anexoDocumento;
    }

    public void setAnexoDocumento(Boolean anexoDocumento) {
        this.anexoDocumento = anexoDocumento;
    }
}
