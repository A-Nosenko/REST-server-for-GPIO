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

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView clientEnterPoint() {

        String mode = modeService.getMode();
        logger.info(LOG_MARKER + "\tMODE = " + mode);

        switch (mode) {
            case "relay" :
                List<RelayReport> relayReports = relaysService.getRelays();
                ModelAndView modelAndView = new ModelAndView("clientRelays", "relaysList", relayReports);
                logger.info(LOG_MARKER  + "\tRelays count = " + relayReports.size());
                return modelAndView;

            default:
                return new ModelAndView("clientRelays");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "switchRelay")
    public ModelAndView switchRelay(@RequestParam(value = "id")int id,
                                  @RequestParam(value = "status")boolean status) {
        relaysService.switchRelay(id, status);
        return clientEnterPoint();
    }
}
