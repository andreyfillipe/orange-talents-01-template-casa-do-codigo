package br.com.zup.casadocodigo.validacao;

public class FormResponse {

    private String campo;
    private String mensagem;

    public FormResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
