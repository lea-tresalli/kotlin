package com.example.mobileclientconsultation.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import hepl.faad.Bibliotheque.Reponse;
import hepl.faad.Bibliotheque.Requete;

public class networkConnection {
    public static Reponse contacteServeur(Requete envoie) throws IOException {

        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        Socket s = new Socket(InetAddress.getByName("10.0.2.2"), 5007);
        objectOutputStream = new ObjectOutputStream(s.getOutputStream());
        objectInputStream = new ObjectInputStream(s.getInputStream());


        objectOutputStream.writeObject(envoie);
        Reponse r;
        try {
            try {
                r = (Reponse) objectInputStream.readObject();
                return r;
            }
            catch (EOFException e){
                return null;
            }

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        finally {

            s.close();

        }
    }



}
