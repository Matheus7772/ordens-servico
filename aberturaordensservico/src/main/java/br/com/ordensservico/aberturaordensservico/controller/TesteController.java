package br.com.ordensservico.aberturaordensservico.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class TesteController {

    @GetMapping ("/ordens_servico")
    public  String teste(){
        return "Ta funcionando em!!!!!!!!!";
    }
    
}
