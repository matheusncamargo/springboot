package br.com.ada.projetoweb1atletasolimpicos.controllers;

import br.com.ada.projetoweb1atletasolimpicos.model.Athlete;
import br.com.ada.projetoweb1atletasolimpicos.services.AthleteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/athletes")
public class AthleteController {

    @Autowired
    private AthleteServices athleteServices;
    @GetMapping("/athletes")
    public String getAllAthletes(Model model){
        List<Athlete> athletes = athleteServices.findAll();
        model.addAttribute("athletes", athletes);
        return "athletes";
    }
    @GetMapping("/athletes/{id}")
    public String getAthlete( @PathVariable("id") Long id, Model model){
        Athlete athlete = athleteServices.findById(id);
        model.addAttribute("athlete", athlete);
        return "add-athletes";
    }
    @PostMapping("/athletes")
    public String addAthlete(@RequestParam("name") String name,@RequestParam("gender") String gender,@RequestParam("birthDate") LocalDate birthdate,
                  @RequestParam("federation") String federation, @RequestParam("modality") String modality){

        Athlete athlete = new Athlete();
        athlete.setName(name);
        athlete.setGender(gender);
        athlete.setBirthDate(birthdate);
        athlete.setFederation(federation);
        athlete.setModality(modality);
        athleteServices.save(athlete);
        return "redirect:athletes";
    }
    @PostMapping("/athletes/{id}")
    public String editAthlete(@RequestParam("name") String name, @RequestParam("gender") String gender,
                              @RequestParam("birthDate") LocalDate birthdate, @RequestParam("federation") String federation,
                              @RequestParam("modality") String modality, @RequestParam("id") Long id) {
        Athlete athlete = new Athlete();
        athlete.setName(name);
        athlete.setGender(gender);
        athlete.setBirthDate(birthdate);
        athlete.setFederation(federation);
        athlete.setModality(modality);
        athleteServices.update(id, athlete);
        return "redirect:/athletes/athletes";
    }

    @GetMapping("add-athlete")
    public String createAthlete(Model model){
        model.addAttribute("athlete", new Athlete());
        return "add-athletes";
    }

    @PostMapping("/delete={id}")
    public String deleteAthlete(@PathVariable("id") Long id){
        athleteServices.deleteById(id);
        return "redirect:athletes";
    }

    @PostMapping("/federation")
    public String searchForFederation(@RequestParam("federation") String federation, Model model){
        List<Athlete> athletes = athleteServices.findByFederation(federation);
        model.addAttribute("athletes", athletes);
        return  "athletes";
    }

    @PostMapping("/name")
    public String searchForName(@RequestParam("name") String name, Model model){
        List<Athlete> athletes = athleteServices.findByNameStartingWith(name);
        model.addAttribute("athletes", athletes);
        return  "athletes";
    }
    @PostMapping("/id")
    public String searchForId(@RequestParam("id") String id, Model model){
        List <Athlete> athletes = List.of(athleteServices.findById(Long.parseLong(id)));
        model.addAttribute("athletes", athletes);
        return  "athletes";
    }


    @PostMapping("/modality")
    public String searchForModality(@RequestParam("modality") String modality, Model model){
        List<Athlete> athletes = athleteServices.findByModality(modality);
        model.addAttribute("athletes", athletes);
        return  "athletes";
    }
}
