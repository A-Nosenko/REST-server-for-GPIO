package remote.to.gpio.controllers.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import remote.to.gpio.models.relay.RelayReport;
import remote.to.gpio.services.mode.ModeService;
import remote.to.gpio.services.relay.RelaysService;
import remote.to.gpio.services.user.SecurityService;

import static remote.to.gpio.values.Constants.*;
import java.util.List;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/18/2018.
 */
@Controller
public class ClientController {

    @Autowired
    ModeService modeService;

    @Autowired
    RelaysService relaysService;

    @Autowired
    SecurityService securityService;

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView clientEnterPoint() {

        String mode = modeService.getMode();
        LOG.info(LOG_MARKER + "\tMODE = " + mode);

        switch (mode) {
            case "relay" :
                List<RelayReport> relayReports = relaysService.getRelayReports();
                ModelAndView modelAndView = new ModelAndView("clientRelays", "relaysList", relayReports);
                LOG.info(LOG_MARKER + "\tRelays count = " + relayReports.size());
                return modelAndView;

            default:
                return new ModelAndView("clientRelays");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/switchRelay")
    public ModelAndView switchRelay(@RequestParam(value = "id")int id,
                            @RequestParam(value = "status")boolean status,
                            @RequestParam(value = "hour")int hour,
                            @RequestParam(value = "min")int min,
                            @RequestParam(value = "sec") int second) {
        LOG.info("SWITCH: " + id + " ==> " + status + " ## " + hour + " : " + min + " : " +second);
        if (!status) {
            relaysService.switchRelayOff(id);
            return clientEnterPoint();
        }
        int timeToGo;
        timeToGo = hour * 60 * 60 + min * 60 + second;
        if (timeToGo == 0) {
            LOG.info("SWITCH: " + id + " ==> " + status + " ## ");
            relaysService.switchRelayOn(id);
            return clientEnterPoint();
        }
        LOG.info(LOG_MARKER + "RequestMethod.POST \n " +
                "switchRelay(" + id + ", " + status + ")" + LOG_MARKER);
        relaysService.switchRelayOn(id, timeToGo);
        return clientEnterPoint();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/switchAllRelaysOn")
    public ModelAndView switchAllRelaysOn() {
        relaysService.switchAllRelaysOn();
        return clientEnterPoint();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/switchAllRelaysOff")
    public ModelAndView switchAllRelaysOff() {
        relaysService.switchAllRelaysOff();
        return clientEnterPoint();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTime")
    public ModelAndView addTime(@RequestParam(value = "id")int id,
                                @RequestParam(value = "hour")int hour,
                                @RequestParam(value = "min")int min,
                                @RequestParam(value = "sec") int second){
        relaysService.addTime(id, hour * 60 * 60 + min * 60 + second);
        LOG.info(LOG_MARKER + "\tTIME ADDED: " + hour + " : " + min);
        return clientEnterPoint();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/settingRelayNames")
    public ModelAndView getSettingRelayNames() {
        List<RelayReport> relayReports = relaysService.getRelayReports();
        return new ModelAndView("settingRelayNames", "relaysList", relayReports);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setRelayNames")
     public ModelAndView setRelayNames(@RequestParam(value = "ides")int[] ides,
                                        @RequestParam(value = "customNames")String[] customNames){
        relaysService.setRelayNames(ides, customNames);
        return getSettingRelayNames();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setRelayName")
    public ModelAndView setRelayName(@RequestParam(value = "id")int id,
                                       @RequestParam(value = "customName")String customName){
        relaysService.setRelayName(id, customName);
        return getSettingRelayNames();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/settingUserData")
    public ModelAndView getSettingUserData() {
        return new ModelAndView("settingUserData");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateUserData")
    public ModelAndView updateUserData(@RequestParam(value = "login")String login,
                                       @RequestParam(value = "newLogin")String newLogin,
                                       @RequestParam(value = "password")String password,
                                       @RequestParam(value = "newPassword")String newPassword,
                                       @RequestParam(value = "newPasswordRe")String newPasswordRe) {
        if (newPassword == null || !newPassword.equals(newPasswordRe)) {
            return new ModelAndView("settingUserData", "error", "Passwords not equals!");
        }

        if (newLogin == null || newLogin.length() < 1) {
            return new ModelAndView("settingUserData", "error", "New login too short!");
        }

        if (newLogin.length() > 100) {
            return new ModelAndView("settingUserData", "error", "New login too long!");
        }

        if (!securityService.updatePersonalData(login, newLogin, password, newPassword)) {
            return new ModelAndView("settingUserData", "error", "Password incorrect!");
        }
        return clientEnterPoint();
    }
}
