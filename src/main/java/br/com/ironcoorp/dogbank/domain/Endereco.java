package br.com.ironcoorp.dogbank.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

    private String cep;

    private String rua;

    private String bairro;

    private String complemento;

    private String cidade;

    private String uf;

    private Endereco(String cep, String rua, String bairro, String complemento, String cidade, String uf) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
    }

    protected Endereco() {
    }

    public static class Builder {

        private String cep;

        private String rua;

        private String bairro;

        private String complemento;

        private String cidade;

        private String uf;

        private Builder() {
        }

        public static Builder newBuilder(){
            return new Builder();
        }

        public Builder cep(String cep){
            this.cep = cep;
            return this;
        }

        public Builder rua(String rua){
            this.rua = rua;
            return this;
        }

        public Builder bairro(String bairro){
            this.bairro = bairro;
            return this;
        }

        public Builder complemento  (String complemento){
            this.complemento = complemento;
            return this;
        }

        public Builder cidade(String cidade){
            this.cidade = cidade;
            return this;
        }

        public Builder uf(String uf){
            this.uf = uf;
            return this;
        }

        public Endereco build(){
            Endereco endereco = new Endereco();
            endereco.cep = cep;
            endereco.rua = rua;
            endereco.complemento = complemento;
            endereco.bairro = bairro;
            endereco.cidade = cidade;
            endereco.uf = uf;

            return endereco;
        }

    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
