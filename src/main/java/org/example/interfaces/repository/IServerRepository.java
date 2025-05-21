package org.example.interfaces.repository;

import java.util.List;

public interface IServerRepository<T> {
    void add(T element);

    List<T> getRepository();

    T getLast();

    T getAtIndex(int index);

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(T element);

    void removeAtIndex(int index);
}
