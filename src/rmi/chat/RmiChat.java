/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rmi.chat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author Broda
 */
public class RmiChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // 1️⃣ Démarrer le registre RMI
            LocateRegistry.createRegistry(1099);
            System.out.println("Registre RMI démarré.");

            // 2️⃣ Créer le serveur
            ChatServerImpl server = new ChatServerImpl();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("ChatServer", server);
            System.out.println("Serveur prêt.");

            // 3️⃣ Créer deux clients
            ChatClient alice = new ChatClient("Alice", server);
            ChatClient bob   = new ChatClient("Bob", server);

            // 4️⃣ Envoyer quelques messages
            alice.send("Salut tout le monde !");
            bob.send("Salut Alice !");
            alice.send("Comment ça va ?");
            bob.send("Très bien, merci !");

            // 5️⃣ Déconnexion
            alice.disconnect();
            bob.disconnect();

            System.out.println("Test terminé ✅");

        } catch (RemoteException e) {
        }
        
    }
}
