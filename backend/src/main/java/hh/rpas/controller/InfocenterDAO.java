package hh.rpas.controller;

import hh.rpas.Util;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InfocenterDAO {


    private final String GET_USERTODAY = "SELECT COUNT(*) AS cnt FROM v_log WHERE login = '%s' and v_log.workend > DATE('%s')";
    final private String GET_LOGINS = "SELECT login FROM v_login";
    final private String GET_SUCCESS = "SELECT COUNT(*) AS succ FROM v_log WHERE result = 'успешно' and login = '%s'";
    final private String GET_USERSUCCESSTODAY = "SELECT COUNT(*) AS cnt FROM v_log WHERE result = 'успешно' and login = '%s' and v_log.workend > DATE('%s')";
    final private String GET_SUCCESSMONTH = "SELECT COUNT(*) AS cnt FROM v_log WHERE result = 'успешно' AND v_log.workend>DATE('%s')";
    final private String GET_ALLSUCCESS = "SELECT COUNT(*) AS succ FROM v_log WHERE result = 'успешно'";
    final private String GET_ALLERRORS = "SELECT COUNT(*) AS err FROM v_log WHERE result <> 'успешно'";
    final private String GET_ALLPROCSMONTH = "SELECT COUNT(*) AS cnt FROM v_log WHERE v_log.workend>DATE('%s')";
    final private String GET_ERRORS = "SELECT COUNT(*) AS err FROM v_log WHERE result <> 'успешно' and login = '%s'";
    final private String GET_SECONDS = "SELECT sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt FROM v_log WHERE login = '%s'";
    final private String GET_SECONDSTODAY = "SELECT sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt FROM v_log WHERE login = '%s' and v_log.workend > DATE('%s')";
    final private String GET_SUCCESSSECONDSMONTH = "SELECT sum(TIMESTAMPDIFF(SECOND,workbegin,workend)) AS tt FROM v_logsuccess WHERE v_logsuccess.workend > DATE('%s')";

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
}
