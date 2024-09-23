package com.example.Practicando1.controller;

import com.example.Practicando1.entities.Cliente;
import com.example.Practicando1.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/listar")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model){
        List<Cliente> clientes=clienteService.obtenerTodos();
        model.addAttribute("listaClientes",clientes);
        return "listar";
    }

    @GetMapping("/nuevos")
    public String mostrarFormularioNuevoCliente(Model model){
        model.addAttribute("cliente",new Cliente());
        model.addAttribute("accion","/listar/nuevos");
        return"formulario_clientes";
    }

    @PostMapping("/nuevos")
    public String crearUsuario(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "formulario_clientes";
        }else{
            clienteService.crearCliente(cliente);
            return "redirect:/listar";
        }

    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Integer id,Model model){
        model.addAttribute("cliente",clienteService.buscarClientePorId(id));
        model.addAttribute("accion","/listar/editar/"+id);
        return"formulario_clientes";
    }

    @PostMapping("/editar/{id}")
    public String editarClient(@Valid @ModelAttribute Cliente cliente,BindingResult bindingResult ,@PathVariable Integer id){

        if(bindingResult.hasErrors()){
            return "formulario_clientes";
        }else{
            clienteService.actualizarCliente(id,cliente);
            return"redirect:/listar";
        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id){
        clienteService.eliminarCliente(id);
        return "redirect:/listar";
    }
}
