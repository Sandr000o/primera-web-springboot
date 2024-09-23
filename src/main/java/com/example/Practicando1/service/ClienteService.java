package com.example.Practicando1.service;

import com.example.Practicando1.entities.Cliente;
import com.example.Practicando1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClienteService {

   List<Cliente> obtenerTodos();

   Cliente crearCliente(Cliente cliente);

   Cliente buscarClientePorId(Integer id);

   Cliente actualizarCliente(Integer id,Cliente cliente);

   void eliminarCliente(Integer id);

   Long contarRegistros();

}
