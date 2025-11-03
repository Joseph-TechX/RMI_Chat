/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Broda
 */
public interface ChatServer extends Remote {
    
    // Enregistrer un client (username doit être unique)
    void register(String username, ChatClient client) throws RemoteException;

    // Désenregistrer (déconnexion)
    void unregister(String username) throws RemoteException;

    // Envoyer un message (broadcast)
    void sendMessage(String from, String message) throws RemoteException;

    // Lister utilisateurs connectés (utile pour UI)
    List<String> listUsers() throws RemoteException;
    
}
