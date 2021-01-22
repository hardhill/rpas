package hh.rpas.models;

public class DataProcess {
    private String Datework;
    private String Processname;
    private int Process;
    private int Countprocess;
    private int Successproc;
    private int Seconds;

    public String getDatework() {
        return Datework;
    }

    public void setDatework(String datework) {
        Datework = datework;
    }

    public String getProcessname() {
        return Processname;
    }

    public void setProcessname(String processname) {
        Processname = processname;
    }

    public int getProcess() {
        return Process;
    }

    public void setProcess(int process) {
        Process = process;
    }

    public int getCountprocess() {
        return Countprocess;
    }

    public void setCountprocess(int countprocess) {
        Countprocess = countprocess;
    }

    public int getSuccessproc() {
        return Successproc;
    }

    public void setSuccessproc(int successproc) {
        Successproc = successproc;
    }

    public int getSeconds() {
        return Seconds;
    }

    public void setSeconds(int seconds) {
        Seconds = seconds;
    }
}
