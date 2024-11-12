package com.timesheet.app.service.impl;

import com.timesheet.app.exception.ClientNotFoundException;
import com.timesheet.app.model.Client;
import com.timesheet.app.repository.ClientRepository;
import com.timesheet.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public Client create(Client client) {
        return repository.save(client);
    }

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return repository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client update(Client client) {
        Client existingClient = repository.findById(client.getId()).orElseThrow(ClientNotFoundException::new);
        existingClient.setName(client.getName());
        existingClient.setAddress(client.getAddress());
        existingClient.setCity(client.getCity());
        existingClient.setPostalCode(client.getPostalCode());
        existingClient.setCountry(client.getCountry());
        return repository.save(existingClient);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
