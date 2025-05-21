package org.example.interfaces.service;

public interface IServerService<T> {
    void add(T element);
    T getLast();
    T getAtIndex(int index);
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(T element);
    void removeAtIndex(int index);
}
