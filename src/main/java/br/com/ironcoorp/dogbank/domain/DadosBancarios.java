package br.com.ironcoorp.dogbank.domain;



public class DadosBancarios {

    public DadosBancarios(String agencia, String conta) {
        this.agencia = agencia;
        this.conta = conta;
    }

    private String agencia;

    private String conta;

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
}
