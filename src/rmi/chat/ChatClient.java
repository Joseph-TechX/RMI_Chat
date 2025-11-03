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

    // Constructeur : exporte le client et le connecte au serveur
    
    public ChatClient(String username, ChatServer server) throws RemoteException {
        super(); // exportation automatique de l'objet
        this.username = username;
        this.server = server;

        // Enregistre ce client auprès du serveur
        server.register(username, this);
        System.out.println("[" + username + "] connecté au serveur.");
    }

    // Méthode appelée par le serveur pour recevoir un message
    public void receiveMessage(String from, String message) throws RemoteException {
        System.out.println("[" + username + "] ← " + from + " : " + message);
    }

    // Envoyer un message au serveur
    public void send(String message) throws RemoteException {
        server.sendMessage(username, message);
    }

    // Déconnexion propre
    public void disconnect() throws RemoteException {
        server.unregister(username);
        System.out.println("[" + username + "] déconnecté.");
    }

    public String getUsername() {
        return username;
    }
}

