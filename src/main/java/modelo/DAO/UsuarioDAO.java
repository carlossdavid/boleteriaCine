/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.entidad.*;
import modelo.enums.RolUsuario;

/**
 *
 * @author carlo
 */
public class UsuarioDAO {
    private File archivo;
    
    public UsuarioDAO( ) {
        archivo = new File("src\\main\\resources\\bd\\usuarios.txt");
    }
    
    // Obtener los usuarios como una lista 
    public ArrayList<Usuario> getListaUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        // Verificar si el archivo existe sino devuelve una lista vacía
        if (!archivo.exists()) return usuarios;        
        
        // Leemos el archivo 
        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while((linea = br.readLine()) != null) {
                String [] partes = linea.split(",");
                if (partes.length == 6) {
                    RolUsuario rol = RolUsuario.valueOf(partes[5]);
                    Usuario u = new Usuario(
                            Integer.parseInt(partes[0]),
                            partes[1],
                            partes[2], 
                            partes[3],
                            partes[4],
                            rol
                    );
                    usuarios.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return usuarios;
    }
    
    // BUSQUEDA 
    public Usuario buscarPorCorreo(String correo) {
        ArrayList<Usuario> usuarios = this.getListaUsuarios();

        for (Usuario u: usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                
                return u;
            }
        }
        return null;
    }
    private int getUltimoID () {
        ArrayList<Usuario> usuarios = this.getListaUsuarios();
        
        return usuarios.getLast().getId();
    }
    
    public boolean agregarUsuario(Usuario usuario) {
       usuario.setId(getUltimoID()+1); 
        try(FileWriter fw = new FileWriter(archivo,true);
                BufferedWriter bw = new BufferedWriter(fw)){
            //Escribir el producto usando el método toCSV y añadimos una nueva linea
            bw.write(usuario.toCSV());
            bw.newLine();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    
    }
    
}
