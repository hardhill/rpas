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

    @RequestMapping(path = "/logins")
    public List<String> getLogins() {
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        return infocenterDAO.getLogins();
    }
    @RequestMapping(path="/errproc")
    public int getErrorProcess(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getErrorProcess(login);
        return result;
    }
    @RequestMapping(path="/succproc")
    public int getSuccProcess(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getSuccProcess(login);
        return result;
    }

    @RequestMapping(path="/seconds")
    public int getSeconds(@RequestParam String login){
        int result = 0;
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        result = infocenterDAO.getSeconds(login);
        return result;
    }
    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details


}
