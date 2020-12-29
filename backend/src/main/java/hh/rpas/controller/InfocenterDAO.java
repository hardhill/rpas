package hh.rpas.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

public class InfocenterDAO {
    final private String GET_LOGINS = "SELECT login FROM v_login";
    final private String GET_SUCCESS = "SELECT COUNT(*) AS succ FROM v_log WHERE result = 'успешно' and login = '%s'";
    final private String GET_ERRORS = "SELECT COUNT(*) AS err FROM v_log WHERE result <> 'успешно' and login = '%s'";
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

    public int getSuccProcess(String login) {
        String sql = String.format(GET_SUCCESS,login);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        int result = 0;
        while(rowSet.next()){
            result = rowSet.getInt("succ");
        }
        return result;
    }
}
