package umk.mat.jakuburb.QrmGame.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
