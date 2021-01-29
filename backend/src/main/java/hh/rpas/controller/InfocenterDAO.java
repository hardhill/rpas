package hh.rpas.controller;

import hh.rpas.Util;
import hh.rpas.models.DataAllProcess;
import hh.rpas.models.DataProcess;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InfocenterDAO {


    private static final String GET_SUCCESSSECONDS20 = "SELECT SUM(t1.tt) as tt FROM (SELECT DATE(workend),sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt FROM v_logsuccess GROUP BY DATE(workend) ORDER BY 1 DESC LIMIT 20) AS t1";
    private static final String GET_DATAPROCESSOFDAY = "SELECT typeproc, COUNT(*) cnt, SUM(TIMESTAMPDIFF(SECOND,workbegin,workend)) tt FROM v_log WHERE DATE(v_log.workend)= '%s' GROUP BY v_log.typeproc ORDER BY 1";
    private static final String GET_DATES = "SELECT DATE(workend) as dt FROM v_logsuccess GROUP BY dt ORDER BY 1 DESC LIMIT 20";
    private final String GET_USERTODAY = "SELECT COUNT(*) AS cnt FROM v_log WHERE login = '%s' and v_log.workend > DATE('%s')";
    final private String GET_LOGINS = "SELECT login FROM v_login";
    final private String GET_SUCCESS = "SELECT COUNT(*) AS succ FROM v_log WHERE result = 'успешно' and login = '%s'";
    final private String GET_USERSUCCESSTODAY = "SELECT COUNT(*) AS cnt FROM v_log WHERE result = 'успешно' and login = '%s' and v_log.workend > DATE('%s')";
    final private String GET_SUCCESSMONTH = "SELECT COUNT(*) AS cnt FROM v_log WHERE result = 'успешно' AND v_log.workend>DATE('%s')";
    final private String GET_SUCCESS20 = "Select sum(t1.cnt) as cnt FROM (SELECT DATE(workend),COUNT(*) AS cnt FROM v_logsuccess GROUP BY DATE(workend) ORDER BY 1 DESC LIMIT 20) AS t1";
    private static final String GET_ALLPROCS20 = "Select sum(t1.cnt) as cnt FROM (SELECT DATE(workend),COUNT(*) AS cnt FROM v_log GROUP BY DATE(workend) ORDER BY 1 DESC LIMIT 20) AS t1";
    final private String GET_ALLSUCCESS = "SELECT COUNT(*) AS succ FROM v_log WHERE result = 'успешно'";
    final private String GET_ALLERRORS = "SELECT COUNT(*) AS err FROM v_log WHERE result <> 'успешно'";
    final private String GET_ALLPROCSMONTH = "SELECT COUNT(*) AS cnt FROM v_log WHERE v_log.workend>DATE('%s')";
    final private String GET_ERRORS = "SELECT COUNT(*) AS err FROM v_log WHERE result <> 'успешно' and login = '%s'";
    final private String GET_SECONDS = "SELECT sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt FROM v_log WHERE login = '%s'";
    final private String GET_SECONDSTODAY = "SELECT sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt FROM v_log WHERE login = '%s' and v_log.workend > DATE('%s')";
    final private String GET_SUCCESSSECONDSMONTH = "SELECT sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt FROM v_logsuccess WHERE v_logsuccess.workend > DATE('%s')";
    final private String GET_DATESPROCESS = "SELECT DATE(workend) as dt,COUNT(*) as cnt FROM v_log GROUP BY DATE(workend) ORDER BY 1 DESC LIMIT 20";
    final private String GET_SUCCPROCBYTYPE = "SELECT typeproc,COUNT(*) cnt, sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt " +
            "FROM v_logsuccess WHERE DATE(v_logsuccess.workend)='%s' GROUP BY v_logsuccess.typeproc ORDER BY 1 ASC";
    final private String GET_SUCCPROCBYTYPELOGIN = "SELECT typeproc,COUNT(*) cnt, sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt " +
            "FROM v_logsuccess WHERE DATE(v_logsuccess.workend)='%s' and v_logsuccess.login = '%s' GROUP BY v_logsuccess.typeproc ORDER BY 1 ASC";
    final private String GET_PROCBYTYPE = "SELECT typeproc, COUNT(*) cnt FROM v_log WHERE DATE(v_log.workend)='%s' GROUP BY v_log.typeproc ORDER BY 1 ASC";
    final private String GET_PROCBYTYPELOGIN = "SELECT typeproc, COUNT(*) cnt FROM v_log WHERE DATE(v_log.workend)='%s' and v_log.login = '%s' GROUP BY v_log.typeproc ORDER BY 1 ASC";
    final private String GET_DATASUCCESSPROCESS = "SELECT DATE(workend) as dt,COUNT(*) as cnt FROM v_logsuccess GROUP BY DATE(workend) ORDER BY 1 DESC LIMIT 20";

    JdbcTemplate jdbcTemplate;
    public InfocenterDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getLogins() {
        List<String> result = new ArrayList<String>();
        SqlRowSet rows = jdbcTemplate.queryForRowSet(GET_LOGINS);
        while (rows.next()) {
            result.add(rows.getString("login"));
        }
        return result;
    }

    public int getErrorProcess(String login) {
        String sql = String.format(GET_ERRORS,login);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        int result = 0;
        while(rowSet.next()){
            result = rowSet.getInt("err");
        }
        return result;

    }

    public int getErrorProcess() {
        String sql = String.format(GET_ALLERRORS);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        int result = 0;
        while(rowSet.next()){
            result = rowSet.getInt("err");
        }
        return result;
    }

    public int getSuccessProcs(String login) {
        String sql = String.format(GET_SUCCESS,login);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        int result = 0;
        while(rowSet.next()){
            result = rowSet.getInt("succ");
        }
        return result;
    }
    public int getSuccessProcs() {
        int result = 0;
        String sql = String.format(GET_ALLSUCCESS);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            result = rowSet.getInt("succ");
        }
        return result;
    }

    public int getSeconds(String login){
        int result = 0;
        String sql = String.format(GET_SECONDS,login);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            result = rowSet.getInt("tt");
        }
        return result;
    }


    public int getSuccessMonth(){
        int result = 0;
        String days30 = Util.getDatebyFormat(Util.minusDays(30),"yyyy-MM-dd");
        String sql = String.format(GET_SUCCESSMONTH,days30);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            result = rowSet.getInt("cnt");
        }
        return result;
    }

    public int getSuccess20() {
        int result = 0;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(GET_SUCCESS20);
        while (rowSet.next()) {
            result = rowSet.getInt("cnt");
        }
        return result;
    }

    //все поцессы за 30 календарных дней
    public int getAllProcsMonth(){
        int result = 0;
        String days30 = Util.getDatebyFormat(Util.minusDays(30),"yyyy-MM-dd");
        String sql = String.format(GET_ALLPROCSMONTH,days30);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            result = rowSet.getInt("cnt");
        }
        return result;
    }

    public int getAllProcs20() {
        int result = 0;
        String sql = String.format(GET_ALLPROCS20);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            result = rowSet.getInt("cnt");
        }
        return result;
    }
    //количество успешных процессов сегодня по логину
    public int getSuccessProcessToDay(String login){
        int result = 0;
        String day = Util.getDatebyFormat(Util.ToDay(),"yyyy-MM-dd");
        String sql = String.format(GET_USERSUCCESSTODAY,login,day);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            result = rowSet.getInt("cnt");
        }
        return result;
    }
    //общее количество процессов сегодня по логину
    public int getProcessToDay(String login){
        int result = 0;
        String day = Util.getDatebyFormat(Util.ToDay(),"yyyy-MM-dd");
        String sql = String.format(GET_USERTODAY,login,day);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            result = rowSet.getInt("cnt");
        }
        return result;
    }

    //потраченное время роботом за сегодня
    public int getSecondsToDay(String login) {
        int result = 0;
        String day = Util.getDatebyFormat(Util.ToDay(),"yyyy-MM-dd");
        String sql = String.format(GET_SECONDSTODAY,login, day);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            result = rowSet.getInt("tt");
        }
        return result;
    }

    //потраченное время за последние 30 календарных дней
    public int getSuccessSecondsMonth() {
        int result = 0;
        String days30 = Util.getDatebyFormat(Util.minusDays(30), "yyyy-MM-dd");
        String sql = String.format(GET_SUCCESSSECONDSMONTH, days30);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            result = rowSet.getInt("tt");
        }
        return result;
    }

    //потраченное время на успешные процессы за последние 20 рабочих дней
    public int getSuccessSeconds20() {
        int result = 0;
        String sql = String.format(GET_SUCCESSSECONDS20);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            result = rowSet.getInt("tt");
        }
        return result;
    }

    public List<DataAllProcess> getDataAllProcess() {
        List<DataAllProcess> result = new ArrayList<DataAllProcess>();
        String sql = GET_DATESPROCESS;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            Date d = rowSet.getDate("dt");
            int pr = rowSet.getInt("cnt");
            result.add(0, new DataAllProcess(d, pr, 0));
        }
        sql = GET_DATASUCCESSPROCESS;
        rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            Date d = rowSet.getDate("dt");
            int pr = rowSet.getInt("cnt");
            result.stream().filter(obj -> obj.getWorkdate().equals(d)).findFirst().get().setSuccessproc(pr);
        }
        return result;
    }

    // последние 20 рабочих дней
    public List<String> getDates() {
        List<String> result = new ArrayList<>();
        String sql = GET_DATES;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            String ds = Util.getDatebyFormat(rowSet.getDate("dt"), "yyyy-MM-dd");
            result.add(ds);
        }
        return result;
    }
    // данные по типам процессов с разбивкой по датам
    public List<DataProcess> getDataProcess(String ds) {
        List<DataProcess> result = new ArrayList<>();
        String sql = String.format(GET_PROCBYTYPE, ds);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            DataProcess dataProcess = new DataProcess();
            dataProcess.setDatework(ds);
            dataProcess.setProcessname("");
            dataProcess.setProcess(rowSet.getInt("typeproc"));
            dataProcess.setCountprocess(rowSet.getInt("cnt"));
            dataProcess.setSuccessproc(0);
            dataProcess.setSeconds(0);
            result.add(dataProcess);
        }
        sql = String.format(GET_SUCCPROCBYTYPE, ds);
        rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            int idproc = rowSet.getInt("typeproc");
            DataProcess data = result.stream().filter(i -> i.getProcess() == idproc).findFirst().get();
            data.setSuccessproc(rowSet.getInt("cnt"));
            data.setSeconds(rowSet.getInt("tt"));
        }

        return result;
    }

    public List<DataProcess> getLoginDataProcess(String ds, String login) {
        List<DataProcess> result = new ArrayList<>();
        String sql = String.format(GET_PROCBYTYPELOGIN, ds, login);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            DataProcess dataProcess = new DataProcess();
            dataProcess.setDatework(ds);
            dataProcess.setProcessname("");
            dataProcess.setProcess(rowSet.getInt("typeproc"));
            dataProcess.setCountprocess(rowSet.getInt("cnt"));
            dataProcess.setSuccessproc(0);
            dataProcess.setSeconds(0);
            result.add(dataProcess);
        }
        sql = String.format(GET_SUCCPROCBYTYPELOGIN, ds, login);
        rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            int idproc = rowSet.getInt("typeproc");
            DataProcess data = result.stream().filter(i -> i.getProcess() == idproc).findFirst().get();
            data.setSuccessproc(rowSet.getInt("cnt"));
            data.setSeconds(rowSet.getInt("tt"));
        }
        return result;
    }
}
