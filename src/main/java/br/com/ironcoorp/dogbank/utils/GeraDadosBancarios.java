package br.com.ironcoorp.dogbank.utils;

import br.com.ironcoorp.dogbank.domain.DadosBancarios;
import br.com.ironcoorp.dogbank.repository.ContaDigitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeraDadosBancarios {

    @Autowired
    ContaDigitalRepository contaDigitalRepository;

    public DadosBancarios gerarDadosBancarios(){

        var existe = true;
        String ag = null;
        String conta = null;

        while (existe){
            ag = geraAgencia();
            conta = geraConta();
            existe = contaDigitalRepository.findByAgenciaAndConta(ag,conta);
        }
        return new DadosBancarios(ag,conta);
    }

    private String geraAgencia(){
     return String.valueOf(Math.round(4));
    }

    private String geraConta(){
        return String.valueOf(Math.round(8));
    }
}
