/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steam_lab7;

import java.util.ArrayList;

/**
 *
 * @author adalb
 */
public class Usuarios {
    public static Usuarios u=new Usuarios("","","",0,0);
    private String user;
    private String name;
    private String password;
    private int edad;
    private int tipo;
    
    public Usuarios(String name,String user,String password,int edad,int tipo){
        this.name=name;
        this.user=user;
        this.password=password;
        this.edad=edad;
        this.tipo=tipo;
    }
    
    public String getName(){
        return name;
    }
    
    public String getUser(){
        return user;
    }
    
    public String getPassword(){
        return password;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public int getTipo(){
        return tipo;
    }
    
    public String setName(String name){
        return this.name=name;
    }
    
    public String setUser(String user){
        return this.user=user;
    }
    
    public String setPassword(String password){
        return this.password=password;
    }
    
    public int setEdad(int edad){
        return this.edad=edad;
    }
    
    public int setTipo(int tipo){
        return this.tipo=tipo;
    }
}
