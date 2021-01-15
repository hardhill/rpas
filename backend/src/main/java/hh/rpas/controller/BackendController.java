package hh.rpas.controller;


import hh.rpas.models.DataAllProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BackendController {


    @Autowired
    JdbcTemplate jdbcTemplate;
    private InfocenterDAO infocenterDAO;

    // все логины в системе
    @RequestMapping(path = "/logins")
    public List<String> getLogins() {
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        return infocenterDAO.getLogins();
    }
    //все ошибочные процессы RPA и пользователя если логин указан
    @RequestMapping(path="/errproc")
    public int getErrorProcess(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        if (login.length()==0){
            result = infocenterDAO.getErrorProcess();
        }else{
            result = infocenterDAO.getErrorProcess(login);
        }

        return result;
    }
    //все успешные процессы RPA и пользователя если логин указан
    @RequestMapping(path="/succproc")
    public int getSuccProcess(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        if (login.length()==0){
            result = infocenterDAO.getSuccessProcs();
        }else {
            result = infocenterDAO.getSuccessProcs(login);
        }
        return result;
    }
    //все успешные процессы RPA за 30 дней
    @RequestMapping(path="/successmonth")
    public int getSuccessProcsMonth(){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getSuccessMonth();
        return result;
    }
    //все процессы RPA за 30 дней
    @RequestMapping(path="/allprocsmonth")
    public int getAllProcsMonth(){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getAllProcsMonth();
        return result;
    }

    //время затраченное на успешное подписание за последние 30 дней
    @RequestMapping(path = "/secondsmonth")
    public int getSuccessTimeMonth() {
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getSuccessSecondsMonth();
        return result;
    }

    //сумарное время затраченное пользователем
    @RequestMapping(path="/seconds")
    public int getSeconds(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getSeconds(login);
        return result;
    }
    //сумарное время затраченное пользователем
    @RequestMapping(path="/secondsday")
    public int getSecondsToDay(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getSecondsToDay(login);
        return result;
    }
    //общее число процессов пользователя сегодня
    @RequestMapping(path="/processtoday")
    public int getAllProcsToDay(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getProcessToDay(login);
        return result;
    }
    //общее число успешных процессов пользователя сегодня
    @RequestMapping(path="/successtoday")
    public int getSuccessToDay(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getSuccessProcessToDay(login);
        return result;
    }

    // данные за 10 рабочих дней
    @RequestMapping(path = "/dataprocess")
    public List<DataAllProcess> getDataProcess() {
        List<DataAllProcess> result = new ArrayList<DataAllProcess>();
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getDataProcess();
        return result;
    }

}
