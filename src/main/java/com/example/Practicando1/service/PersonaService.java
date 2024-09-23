package com.example.Practicando1.service;

import com.example.Practicando1.entities.Persona;
import com.example.Practicando1.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonaService {

    List<Persona> obtenerTodas();
    Persona obtenerPorId(Long id);
    Persona crearPersona(Persona persona);
    Persona actualizarPersona(Long id,Persona persona);
    void eliminarPersona(Long id);
    long contarPersona();


}
