/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author Broda
 */
public class ChatClient extends UnicastRemoteObject {

    private final String username;
    private final ChatServer server;

    
    public ChatClient(String username, ChatServer server) throws RemoteException {
        super(); 
        this.username = username;
        this.server = server;

        server.register(username, this);
        System.out.println("[" + username + "] connecté au serveur.");
    }


    public void receiveMessage(String from, String message) throws RemoteException {
        System.out.println("[" + username + "] ← " + from + " : " + message);
    }

    
    public void send(String message) throws RemoteException {
        server.sendMessage(username, message);
    }

    public void disconnect() throws RemoteException {
        server.unregister(username);
        System.out.println("[" + username + "] déconnecté.");
    }

    public String getUsername() {
        return username;
    }
}

