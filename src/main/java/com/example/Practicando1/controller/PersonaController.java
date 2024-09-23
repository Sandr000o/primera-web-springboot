package com.example.Practicando1.controller;

import com.example.Practicando1.entities.Persona;
import com.example.Practicando1.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping
    public String listarClientes(Model model) {
        List<Persona> clientes = personaService.obtenerTodas();
        model.addAttribute("listaClientes", clientes);
        return "listarClientes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCliente(Model model) {
        model.addAttribute("cliente", new Persona());
        model.addAttribute("accion", "/clientes/nuevo");
        return "formulario";
    }

    @PostMapping("/nuevo")
    public String formularioCliente(@ModelAttribute Persona cliente) {
        personaService.crearPersona(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Long id,@ModelAttribute Persona cliente,Model model){
        model.addAttribute("cliente",cliente);
        model.addAttribute("accion","/clientes/editar/"+id);
        return "formulario";
    }

    @PostMapping("/editar/{id}")
    public String formularioEditarCliente(@PathVariable Long id,@ModelAttribute Persona cliente){
        personaService.actualizarPersona(id,cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id){
        personaService.eliminarPersona(id);
        return "redirect:/clientes";
    }

}


