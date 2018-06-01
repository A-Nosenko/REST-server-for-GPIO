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

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.util.List;


/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
@RestController
public class RelaysController {

    @Autowired
    RelaysService relaysService;

    @RequestMapping(method = RequestMethod.GET, value = "getRelays", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonArray getRelays(){
        List<RelayReport> relayReports = relaysService.getRelayReports();
        final Gson gson = new Gson();
        JsonArrayBuilder builder = Json.createArrayBuilder();
        relayReports.forEach((report) -> builder.add(gson.toJson(report)));
        return builder.build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRelay", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonObject getRelay(@RequestParam(value = "id")int id){
        final Gson gson = new Gson();
        JsonObject json = new JsonObject();
        JsonElement jsonElement = gson.toJsonTree(relaysService.getRelayReport(id));
        json.add("relay", jsonElement);
        return json;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRelaysCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getRelaysCount(){
        return relaysService.count();
    }

    @RequestMapping(method = RequestMethod.GET, value = "setRelaysNames", produces = MediaType.APPLICATION_JSON_VALUE)
    public int setRelaysNames(@RequestParam(value = "ides")int[] ides,
                               @RequestParam(value = "customNames")String[] customNames){
        return relaysService.setRelayName(ides, customNames);
    }

    @RequestMapping(method = RequestMethod.GET, value = "switch")
    public int switchRelay(@RequestParam(value = "id")int id,
                                  @RequestParam(value = "status")boolean status) {
        return relaysService.switchRelay(id, status);
    }
}
