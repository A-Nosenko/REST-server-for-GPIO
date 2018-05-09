package remote.to.gpio.controllers.REST;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import remote.to.gpio.models.relay.RelayReport;
import remote.to.gpio.services.relay.RelaysService;

import java.util.List;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
@RestController
public class RelaysController {

    @Autowired
    RelaysService relaysService;

    @RequestMapping(method = RequestMethod.GET, value = "/getRelays", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonObject getRelays(){
        List<RelayReport> relayReports = relaysService.getRelays();
        final Gson gson = new Gson();
        JsonObject json = new JsonObject();
        JsonElement jsonElement = gson.toJsonTree(relayReports);
        json.add("relays", jsonElement);
        return json;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setRelaysNames")
    public void setRelaysNames(@RequestParam(value = "ides")int[] ides,
                               @RequestParam(value = "customNames")String[] customNames){
        relaysService.setRelayName(ides, customNames);
    }

    @RequestMapping(method = RequestMethod.POST, value = "switch")
    public void switchRelay(@RequestParam(value = "id")int id,
                                  @RequestParam(value = "status")boolean status) {
        relaysService.switchRelay(id, status);
    }
}
