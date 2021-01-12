package hh.rpas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

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

    //сумарное время затраченное пользователем
    @RequestMapping(path="/seconds")
    public int getSeconds(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getSeconds(login);
        return result;
    }
    //



}
