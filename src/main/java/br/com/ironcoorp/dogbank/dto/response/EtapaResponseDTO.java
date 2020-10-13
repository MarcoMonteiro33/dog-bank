package br.com.ironcoorp.dogbank.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class EtapaResponseDTO {

    public EtapaResponseDTO(String mensagem, Long idRecurso) {
        this.mensagem = mensagem;
        this.idRecurso = idRecurso;
    }

    private String mensagem;

    @JsonIgnore(value = true)
    private Long idRecurso;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }
}
