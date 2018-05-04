package remote.to.gpio.controllers.REST;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import remote.to.gpio.services.mode.ModeService;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/19/2018.
 */
@RestController
public class ModeController {

    @Autowired
    ModeService modeService;

    @RequestMapping(method = RequestMethod.GET, value = "/getMode", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonObject getMode(){
        return getJsonObject();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setMode")
    public void setMode(@RequestParam(value = "mode")String mode){
        modeService.setMode(mode);
    }

    private JsonObject getJsonObject() {
        JsonObject json = new JsonObject();
        json.addProperty("mode", modeService.getMode());
        return json;
    }
}
