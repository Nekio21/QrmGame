package umk.mat.jakuburb.QrmGame;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import umk.mat.jakuburb.QrmGame.game.GameData;
import umk.mat.jakuburb.QrmGame.game.Machine;

import java.time.DayOfWeek;

@Controller
public class GraWebSite {

    @GetMapping("/")
    public String startGet(){
        return "index.html";
    }

    @GetMapping("/gra")
    public String graGet(){
        return "gra.html";
    }
}
