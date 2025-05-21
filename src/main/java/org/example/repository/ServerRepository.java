package org.example.repository;

import org.example.interfaces.repository.IServerRepository;

import java.util.ArrayList;
import java.util.List;

public class ServerRepository<T> implements IServerRepository<T> {
    private final List<T> repository = new ArrayList<>();

    @Override
    public void add(T element) {
        if (element instanceof Number num) {
            if (num.doubleValue() > 100 || num.doubleValue() < 0) {
                throw new IllegalArgumentException(
                        num.doubleValue() > 100 ? "Number must be less than 100" : "Number must be greater than 0"
                );
            }
        }
        repository.add(element);
    }

    @Override
    public List<T> getRepository() {
        return new ArrayList<>(repository);
    }

    @Override
    public T getAtIndex(int index) {
        return repository.get(index);
    }

    @Override
    public T getLast() {
        if (repository.isEmpty()) {
            throw new IllegalStateException("Repository is empty");
        }
        return repository.getLast();
    }

    @Override
    public void clear() {
        repository.clear();
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
    public void removeAtIndex(int index) {
        repository.remove(index);
    }

    @Override
    public boolean contains(T element) {
        return repository.contains(element);
    }
}

