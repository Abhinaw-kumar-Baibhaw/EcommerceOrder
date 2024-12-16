package com.example.FullFledgedOrderPart.exception;


public class NoEnoughInventoryException extends RuntimeException{
    public NoEnoughInventoryException(String message){
               super(message);
    }
}
