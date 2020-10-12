package br.com.ironcoorp.dogbank.dto;

import javax.validation.constraints.NotBlank;

public class PropostaTerceiraEtapaDTO {

    @NotBlank(message = "nao pode")
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
