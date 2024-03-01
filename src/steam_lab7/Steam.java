/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steam_lab7;
import java.io.File;
import java.io.RandomAccessFile;

/**
 *
 * @author jomel
 */
public class Steam {
    /*
    int code games
    int code clients
    int downloads
    */
    RandomAccessFile code;
    /*
    int code games
    int code clients
    int downloads
    */
    RandomAccessFile games;
    /*
    int code 
    String username 
    String Password
    long nacimiento
    int contador downloads
    imagen foto player
    String tipo de usuario
    */
    RandomAccessFile players;
    
    public Steam(){
        code = new RandomAccessFile("", "rw");
    }
}
