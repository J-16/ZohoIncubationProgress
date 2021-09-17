package com.solidprinciple.InterfaceSegregationPrinciple.Interface;

public interface ItemWithPublisher extends Item{
    void setPublisher(String publisher);
    String getPublisher();
}