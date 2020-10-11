package br.com.ironcoorp.dogbank.error;

import java.time.LocalDateTime;

public class ValidationErrorDetails{
    private String field;
    private String fieldMessage;

    public static final class Builder{
        private String titulo;
        private int status;
        private String detalhe;
        private LocalDateTime tempo;
        private String messageDevelper;
        private String field;
        private String fieldMessage;

        public Builder() {
        }

        public static Builder newbuilder(){
            return new Builder();
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detalhe(String detalhe) {
            this.detalhe = detalhe;
            return this;
        }

        public Builder tempo(LocalDateTime tempo) {
            this.tempo = tempo;
            return this;
        }

        public Builder messageDevelper(String messageDevelper) {
            this.messageDevelper = messageDevelper;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build(){
            ValidationErrorDetails errorDetails = new ValidationErrorDetails();
            errorDetails.fieldMessage = fieldMessage;
            errorDetails.field = field;
            return errorDetails;
        }
    }

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }
}
