package br.com.zup.casadocodigo.validacao;

import java.util.List;

public class FormResponse {

    private List<String> erros;

    public FormResponse(List<String> erros) {
        this.erros = erros;
    }

    public List<String> getErros() {
        return erros;
    }
}
