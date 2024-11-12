package com.timesheet.app.service;

import com.timesheet.app.model.Client;

import java.util.List;

public interface ClientService {
    Client create(Client client);
    List<Client> getAll();
    Client getById(Long id);
    Client update(Client client);
    void delete(Long id);
}
