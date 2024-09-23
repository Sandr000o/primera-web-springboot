package com.example.Practicando1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_clientes")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min =3, max=20)
    @NotBlank
    @NotNull
    private String nombre;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String apellido;


    @NotNull
    private int telefono;

    @NotBlank
    @NotNull
    @Email(message = "Ingrese el correo de manera correcta")
    private String email;
}
