package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.repository.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecepcionistaService {
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
}
