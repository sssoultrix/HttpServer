package org.example.service;

import org.example.interfaces.service.IServerService;
import org.example.repository.ServerRepository;

public class ServerService<T> implements IServerService<T> {
    private ServerRepository<T> repository;

    public ServerService(ServerRepository<T> repository) {
        this.repository = repository;
    }
    @Override
    public void add(T element) {
        repository.add(element);
    }
    @Override
    public T getLast() {
        return repository.getLast();
    }
    @Override
    public T getAtIndex(int index) {
        return repository.getAtIndex(index);
    }
    @Override
    public int size() {
        return repository.size();
    }
    @Override
    public boolean isEmpty() {
        return repository.isEmpty();
    }
    @Override
    public void clear() {
        repository.clear();
    }
    @Override
    public boolean contains(T element) {
        return repository.contains(element);
    }
    @Override
    public void removeAtIndex(int index) {
        repository.removeAtIndex(index);
    }
}
