package modelo.DAO;

import modelo.entidad.usuarios.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.entidad.usuarios.*;
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
                    Usuario u = crearUsuarioPorRol(rol, partes);
                    if (u != null) usuarios.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return usuarios;
    }
    
    // Devolver usuario por tipo de instancia 
    public Usuario crearUsuarioPorRol(RolUsuario rol, String[] datos) {
        return switch(rol) {
            case CLIENTE->
                new Cliente(datos[0], datos[1], datos[2], datos[3], datos[4]);
            case ADMIN->
                new Trabajador(datos[0], datos[1], datos[2], datos[3], datos[4], String.valueOf(rol));
            case VENDEDOR->
                new Trabajador(datos[0], datos[1], datos[2], datos[3], datos[4], String.valueOf(rol));
            default->
                null;
        };       
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
    
    private String getUltimoID () {
        ArrayList<Usuario> usuarios = this.getListaUsuarios();
        
        return usuarios.getLast().getId();
    }
    
    private int idANumero(String id){
        return Integer.parseInt(id.substring(1,id.length()));
    }
    
    public boolean agregarUsuario(Usuario usuario) {
       usuario.setId("U" + (idANumero(getUltimoID())+1)); 
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

    private boolean guardarTodos(ArrayList<Usuario> usuarios) {
        try (FileWriter fw = new FileWriter(archivo, false);
                BufferedWriter bw = new BufferedWriter(fw)) {
            for (Usuario u : usuarios) {
                bw.write(u.toCSV());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
   
    //Elimina un Usiario por su Id
    public boolean eliminarUsuario(int idUsuario) {
        ArrayList<Usuario> usuarios = getListaUsuarios();
        boolean eliminado = usuarios.removeIf(u -> u.getId().equals(idUsuario));
        if (eliminado) {
            return guardarTodos(usuarios);
        }
        return false;
    }
    
     // 1. Método que usa ControladorGestionEmpleados (recibe el objeto Usuario completo)
    //Actualiza la información de un usuario existente buscando su ID
       public boolean actualizarUsuario(Usuario usuarioEditado) {
           ArrayList<Usuario> usuarios = getListaUsuarios();
           for (int i = 0; i < usuarios.size(); i++) {
               if (usuarios.get(i).getId() == usuarioEditado.getId()) {
                   usuarios.set(i, usuarioEditado);
                   return guardarTodos(usuarios);
               }
           }
           return false;
       }
    

    // 2. Método que usa ControladorVistaCuenta (para editar perfil del cliente usando el correo original)
    public boolean actualizarUsuario(String correoOriginal, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevaContra) {
        ArrayList<Usuario> usuarios = getListaUsuarios();
        boolean encontrado = false;

        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correoOriginal)) {
                u.setNombre(nuevoNombre);
                u.setApellido(nuevoApellido);
                u.setCorreo(nuevoCorreo);
                u.setContrasena(nuevaContra);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            return guardarTodos(usuarios);
        }
        return false;
    }
}
