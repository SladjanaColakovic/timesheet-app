package com.timesheet.app.controller;

import com.timesheet.app.dto.ClientDto;
import com.timesheet.app.dto.NewClientDto;
import com.timesheet.app.dto.UpdateClientDto;
import com.timesheet.app.model.Client;
import com.timesheet.app.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewClientDto newClient){
        Client mappedClient = mapper.map(newClient, Client.class);
        Client result = service.create(mappedClient);
        return new ResponseEntity<>(mapper.map(result, ClientDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Client> result = service.getAll();
        return new ResponseEntity<>(
                result.stream().map(element -> mapper.map(element, ClientDto.class)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Client result = service.getById(id);
        return new ResponseEntity<>(mapper.map(result, ClientDto.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateClientDto client){
        Client mappedClient = mapper.map(client, Client.class);
        Client result = service.update(mappedClient);
        return new ResponseEntity<>(mapper.map(result, ClientDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("The client has been deleted", HttpStatus.OK);
    }
}
