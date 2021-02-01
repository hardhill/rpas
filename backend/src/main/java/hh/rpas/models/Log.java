package hh.rpas.models;

import java.time.LocalDateTime;

public class Log {
    private String login;
    private LocalDateTime workbegin;
    private LocalDateTime workend;
    private String strahovatel;
    private int typeproc;
    private String nomerobr;
    private String subtypeproc;
    private String result;

    public Log(String login, LocalDateTime workbegin, LocalDateTime workend, String strahovatel, int typeproc, String nomerobr, String subtypeproc, String result) {
        this.login = login;
        this.workbegin = workbegin;
        this.workend = workend;
        this.strahovatel = strahovatel;
        this.typeproc = typeproc;
        this.nomerobr = nomerobr;
        this.subtypeproc = subtypeproc;
        this.result = result;
    }

    public Log() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getWorkbegin() {
        return workbegin;
    }

    public void setWorkbegin(LocalDateTime workbegin) {
        this.workbegin = workbegin;
    }

    public LocalDateTime getWorkend() {
        return workend;
    }

    public void setWorkend(LocalDateTime workend) {
        this.workend = workend;
    }

    public String getStrahovatel() {
        return strahovatel;
    }

    public void setStrahovatel(String strahovatel) {
        this.strahovatel = strahovatel;
    }

    public int getTypeproc() {
        return typeproc;
    }

    public void setTypeproc(int typeproc) {
        this.typeproc = typeproc;
    }

    public String getNomerobr() {
        return nomerobr;
    }

    public void setNomerobr(String nomerobr) {
        this.nomerobr = nomerobr;
    }

    public String getSubtypeproc() {
        return subtypeproc;
    }

    public void setSubtypeproc(String subtypeproc) {
        this.subtypeproc = subtypeproc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
