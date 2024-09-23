package com.example.Practicando1.service;

import com.example.Practicando1.entities.Persona;
import com.example.Practicando1.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Persona> obtenerTodas() {
        List<Persona>personas = personaRepository.findAll();
        return personas;
    }

    @Override
    public Persona obtenerPorId(Long id) {
        Persona persona=personaRepository.findById(id).orElse(null);
        return persona;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);

    }

    @Override
    public Persona actualizarPersona(Long id, Persona persona) {
        Persona personaBd=personaRepository.findById(id).orElse(null);
        if(personaBd!=null){
            personaBd.setNombre(persona.getNombre());
            personaBd.setEdad(persona.getEdad());
            personaBd.setCelular(persona.getCelular());
            return personaRepository.save(personaBd);
        }
        return null;
    }

    @Override
    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public long contarPersona() {
       return personaRepository.count();
    }
}
