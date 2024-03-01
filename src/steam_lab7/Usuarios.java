/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steam_lab7;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author harle
 */
public class Usuarios {
    private String user;
    private String password;
    private long edad;
    private int tipo;
    
    public Usuarios(String user,String password,long edad,String tipo){
        this.user=user;
        this.password=password;
        this.edad=edad;
        switch(tipo.toLowerCase()){
            case "administrador":
                this.tipo=0;
                break;
            default:
                this.tipo=1;
                break;
        }
        
    }
    
    public String getUser(){
        return user;
    }
    
    public String getPassword(){
        return password;
    }
    
    public long getEdad(){
        return edad;
    }
    
    public int getTipo(){
        return tipo;
    }
    
    public String setUser(String user){
        return this.user=user;
    }
    
    public String setPassword(String password){
        return this.password=password;
    }
    
    public long setEdad(int edad){
        return this.edad=edad;
    }
    
    public int setTipo(int tipo){
        return this.tipo=tipo;
    }
    
    
}
