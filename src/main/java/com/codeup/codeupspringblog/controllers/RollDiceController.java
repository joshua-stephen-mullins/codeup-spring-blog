package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDiceGuess(@PathVariable int guess, Model model){
        model.addAttribute("guess", guess);
        int answer = new Random().nextInt(6) + 1;
        model.addAttribute("answer", answer);
        if (guess == answer){
            model.addAttribute("correctGuess", true);
        } else {
            model.addAttribute("incorrectGuess", true);
        }
        return "roll-dice";
    }

}
