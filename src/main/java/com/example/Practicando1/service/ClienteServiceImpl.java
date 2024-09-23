package com.example.Practicando1.service;

import com.example.Practicando1.entities.Cliente;
import com.example.Practicando1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarClientePorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        Cliente clienteBD=clienteRepository.findById(id).orElse(null);
        if(clienteBD!=null){
            clienteBD.setNombre(cliente.getNombre());
            clienteBD.setApellido(cliente.getApellido());
            clienteBD.setTelefono(cliente.getTelefono());
            clienteBD.setEmail(cliente.getEmail());
            return clienteRepository.save(clienteBD);
        }
        return null;
    }

    @Override
    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Long contarRegistros() {
        return clienteRepository.count();
    }
}
