package br.com.ironcoorp.dogbank.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ErrorMessageDetails {

    private String titulo;
    private int status;
    private String detalhe;
    private LocalDateTime tempo;
    private String messageDevelper;

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

    public static final class ErrorMessageDetailsBuilder {
        private String titulo;
        private int status;
        private String detalhe;
        private LocalDateTime tempo;
        private String messageDevelper;

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

        public ErrorMessageDetails build() {
            ErrorMessageDetails errorMessageDetails = new ErrorMessageDetails();
            errorMessageDetails.titulo = this.titulo;
            errorMessageDetails.messageDevelper = this.messageDevelper;
            errorMessageDetails.detalhe = this.detalhe;
            errorMessageDetails.tempo = this.tempo;
            errorMessageDetails.status = this.status;
            return errorMessageDetails;
        }
    }
}
