package br.com.ironcoorp.dogbank.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErrorMessageDetails {

    private String titulo;
    private int status;
    private String detalhe;
    private LocalDateTime tempo;
    private String messageDevelper;
    private List<?> listErros;

    protected ErrorMessageDetails() {
    }

    public String getTitulo() {
        return titulo;
    }

    public int getStatus() {
        return status;
    }

    public String getDetalhe() {
        return detalhe;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone="GMT-3")
    public LocalDateTime getTempo() {
        return tempo;
    }

    public String getMessageDevelper() {
        return messageDevelper;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public void setTempo(LocalDateTime tempo) {
        this.tempo = tempo;
    }

    public void setMessageDevelper(String messageDevelper) {
        this.messageDevelper = messageDevelper;
    }

    public List<?> getListErros() {
        return listErros;
    }

    public void setListErros(List<?> listErros) {
        this.listErros = listErros;
    }

    public static final class ErrorMessageDetailsBuilder {
        private String titulo;
        private int status;
        private String detalhe;
        private LocalDateTime tempo;
        private String messageDevelper;
        private List<?> listErros = new ArrayList<>();

        private ErrorMessageDetailsBuilder() {
        }

        public static ErrorMessageDetailsBuilder newBuilder() {
            return new ErrorMessageDetailsBuilder();
        }

        public ErrorMessageDetailsBuilder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public ErrorMessageDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorMessageDetailsBuilder detalhe(String detalhe) {
            this.detalhe = detalhe;
            return this;
        }

        public ErrorMessageDetailsBuilder tempo(LocalDateTime tempo) {
            this.tempo = tempo;
            return this;
        }

        public ErrorMessageDetailsBuilder messageDevelper(String message) {
            this.messageDevelper = message;
            return this;
        }

        public ErrorMessageDetailsBuilder listErros(List<?> listErros){
            this.listErros = listErros;
            return this;
        }

        public ErrorMessageDetails build() {
            ErrorMessageDetails errorMessageDetails = new ErrorMessageDetails();
            errorMessageDetails.setTitulo(titulo);
            errorMessageDetails.setMessageDevelper(messageDevelper);
            errorMessageDetails.setDetalhe(detalhe);
            errorMessageDetails.setTempo(tempo);
            errorMessageDetails.setStatus(status);
            errorMessageDetails.setListErros(listErros);

            return errorMessageDetails;
        }
    }
}
