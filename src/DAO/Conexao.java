/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection; // Inclui a classe de conexão em Java.
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Gunner Correa
 */
public class Conexao {

    private String usuario;
    private String senha;
    private String banco;
    private String servidor;
    public Connection con;

    public Connection conexao() {

        /*
         Inclui o JOptionPane apenas para exibir algun erro no BD caso exista, depois do programa quase pronto, gravamos isto em um relatório.
         */

        this.usuario = "root";
        this.senha = "";
        this.servidor = "localhost";
        this.banco = "sgli";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + servidor + "/" + banco, usuario, senha);


        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Classe não encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão: " + ex.getMessage());
        }
        return con;
    }
}
