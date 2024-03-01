/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steam_lab7;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
    int code 
    String titulo
    char sistema operativo
    int edad
    double precio
    int contador de downloads
    imagen del juego
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
    
    public Steam() {
        try{
            File mifile = new File("steam");
            mifile.mkdir();
            File download = new File("steam/downloads");
            download.mkdir();
            
            code = new RandomAccessFile("steam/codes.stm", "rw");
            games = new RandomAccessFile("steam/code.stm", "rw");
            players = new RandomAccessFile("steam/players.stm", "rw");
            
            if(code.length() == 0){
                code.writeInt(1);
                code.writeInt(1);
                code.writeInt(1);
            }
            
        }catch(IOException e){
            System.out.println("Error");
        }
    }
    
    public int nextCode() throws IOException{
        code.seek(0);
        int codigo = code.readInt();
        code.seek(0);
        code.writeInt(codigo + 1);
        return codigo;
    }
    public int nextPlayer() throws IOException{
        code.seek(4);
        int codigo = code.readInt();
        code.seek(4);
        code.writeInt(codigo + 1);
        return codigo;
    }
    public int nextDownloads() throws IOException{
        code.seek(8);
        int codigo = code.readInt();
        code.seek(8);
        code.writeInt(codigo + 1);
        return codigo;
    }
    
    public void addGame(String titulo, char SO, int edad, double precio,Image imagen) throws IOException{
        games.seek(games.length());
        games.writeInt(nextCode());
        games.writeUTF(titulo);
        games.writeChar(SO);
        games.writeInt(edad);
        games.writeDouble(precio);
        games.writeInt(0);
         byte[] imageBytes = imageToBytes(imagen);
        // Escribe la longitud de la imagen en el archivo
        games.writeInt(imageBytes.length);
        // Escribe los bytes de la imagen en el archivo
        games.write(imageBytes);
    }
    
    private byte[] imageToBytes(Image imagen) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(toBufferedImage(imagen), "png", baos);
        return baos.toByteArray();
    }
     
    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
      
    public void addPlayer(Calendar nacimiento, String username, String password, Image imagen, String tipo) throws IOException{
        players.seek(players.length());
        players.writeInt(nextPlayer());
        players.writeUTF(username);
        players.writeUTF(password);
        players.writeLong(nacimiento.getTimeInMillis());
        byte[] imageBytes = imageToBytes(imagen);
        // Escribe la longitud de la imagen en el archivo
        games.writeInt(imageBytes.length);
        // Escribe los bytes de la imagen en el archivo
        games.write(imageBytes);
        games.writeUTF(tipo);
    }  
    
    public void downloadGame(int code, int client, char SO) throws IOException{
        if(existCode(code) && existClient(client)){
            players.readUTF();
            players.readUTF();
            games.readUTF();
            games.readChar();
            if(Calendar.getInstance().getTimeInMillis()-players.readLong()/(365 * 24 * 60 * 60 * 1000) > games.readInt()){
                File download = new File("steam/downloads/download_" + nextDownloads() +".stm");
                download.createNewFile();
                FileWriter wr = new FileWriter(download.getAbsolutePath());
                wr.write(Calendar.getInstance().getTime().toString());
            }
        }
        
    }
    
    private boolean existCode(int code) throws IOException{
        games.seek(0);
        while(games.getFilePointer() < games.length()){
            int codigo = games.readInt();
            long pos = games.getFilePointer();
            //Aqui lo que necesito
            games.readUTF();
            games.skipBytes(14);
            games.read(new byte[games.readInt()]);
            if(codigo == code){
                games.seek(pos);
                return true;
            }
        }
        return false;
    }
    
    private boolean existClient(int code) throws IOException{
        players.seek(0);
        while(players.getFilePointer() < players.length()){
            int codigo = players.readInt();
            long pos = players.getFilePointer();
            //Aqui lo que necesito
            players.readUTF();
            players.readUTF();
            players.skipBytes(8);
            players.read(new byte[players.readInt()]);
            if(codigo == code){
                players.seek(pos);
                return true;
            }
        }
        return false;
    }
}
