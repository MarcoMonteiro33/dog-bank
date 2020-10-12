package br.com.ironcoorp.dogbank.dto;




import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class TutorSegundaEtapaDTO extends DadosPropostaDTO{

    @NotBlank(message = "{cep.not.blank}")
    @Pattern(regexp = "\\d{5}[-]\\d{3}", message = "{cep.incorreto}")
    private String cep;

    @NotBlank(message = "{rua.not.blank}")
    private String rua;

    @NotBlank(message = "{bairro.not.blank}")
    private String bairro;

    @NotBlank(message = "{complemento.not.blank}")
    private String complemento;

    @NotBlank(message = "{cidade.not.blank}")
    private String cidade;

    @NotBlank(message = "{uf.not.blank}")
    @Size(min = 2, max = 2, message = "{uf.size}")
    private String uf;

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
