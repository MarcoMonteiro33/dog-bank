package br.com.ironcoorp.dogbank.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ContaDigital {

    public ContaDigital(String agencia, String conta, Tutor tutor) {
        this.agencia = agencia;
        this.conta = conta;
        this.tutor = tutor;
    }

    @Column(length = 3)
    private final String CODIGO_BANCO = "033";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String agencia;

    @Column(length = 8)
    private String conta;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAlteracao;

    @OneToOne
    private Tutor tutor;

    private BigDecimal saldo = BigDecimal.ZERO;

    public ContaDigital() {

    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getCODIGO_BANCO() {
        return CODIGO_BANCO;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaDigital)) return false;
        ContaDigital that = (ContaDigital) o;
        return getAgencia().equals(that.getAgencia()) &&
                getConta().equals(that.getConta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAgencia(), getConta());
    }
}
