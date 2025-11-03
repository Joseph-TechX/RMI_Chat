/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Broda
 */
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    
     private final Map<String, ChatClient> clients = new ConcurrentHashMap<>();

    protected ChatServerImpl() throws RemoteException { super(); }

    @Override
    public void register(String username, ChatClient client) throws RemoteException {
        clients.put(username, client);
        broadcast("SYSTEM", username + " s'est connecté.");
        System.out.println(username + " connecté.");
    }

    @Override
    public void unregister(String username) throws RemoteException {
        clients.remove(username);
        broadcast("SYSTEM", username + " s'est déconnecté.");
        System.out.println(username + " déconnecté.");
    }

    @Override
    public void sendMessage(String from, String message) throws RemoteException {
        broadcast(from, message);
    }

    @Override
    public List<String> listUsers() throws RemoteException {
        return new ArrayList<>(clients.keySet());
    }

    private void broadcast(String from, String message) {
        clients.forEach((name, client) -> {
            try {
                client.receiveMessage(from, message);
            } catch (RemoteException e) {
                System.err.println("Erreur avec " + name);
            }
        });
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ChatServerImpl server = new ChatServerImpl();
            Registry reg = LocateRegistry.getRegistry();
            reg.rebind("ChatServer", server);
            System.out.println("Serveur RMI prêt !");
        } catch (RemoteException e) {
        }
    }
}
