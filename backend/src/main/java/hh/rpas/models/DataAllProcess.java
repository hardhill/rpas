package hh.rpas.models;

import hh.rpas.Util;

import java.util.Date;

public class DataAllProcess {
    private Date workdate;
    private int processes;
    private int successproc;

    public DataAllProcess(Date workdate, int processes, int successproc) {
        this.workdate = workdate;
        this.processes = processes;
        this.successproc = successproc;
    }

    public DataAllProcess(String workdate, int processes, int successproc) {
        this.workdate = Util.getDatebyFormat(workdate, "yyyy-MM-dd");
        this.processes = processes;
        this.successproc = successproc;
    }

    public Date getWorkdate() {
        return workdate;
    }

    public String getSWorkdate() {
        return Util.getDatebyFormat(workdate, "dd-MMM");
    }

    public void setWorkdate(Date workdate) {
        this.workdate = workdate;
    }

    public int getProcesses() {
        return processes;
    }

    public void setProcesses(int processes) {
        this.processes = processes;
    }

    public int getSuccessproc() {
        return successproc;
    }

    public void setSuccessproc(int successproc) {
        this.successproc = successproc;
    }
}
