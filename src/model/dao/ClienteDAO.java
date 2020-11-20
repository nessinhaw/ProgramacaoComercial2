/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

/**
 *
 * @author Vanessa
 */
public class ClienteDAO {
    public void create(Cliente cliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, email, cpf, telefone, dataaniversario) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getDataAniversario());
            
            con.setAutoCommit(false);
            
            stmt.executeUpdate();
            con.commit();
            
            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Cadastro Cliente", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar operação!" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (SQLException ex2) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar operação!" + ex2, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            //ConnectionFactory.closeConnection(con, stmt);
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex3) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex3);
            }
        }
    }
    
    public ArrayList<Cliente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente ORDER BY id");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setDataAniversario(rs.getString("dataaniversario"));
                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes!", "Erro", JOptionPane.ERROR_MESSAGE);
            try {
                con.rollback();
            } catch (SQLException ex2) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar operação!" + ex2, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
        
        return listaClientes;
    }
    
    public void update(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE cliente set nome = ?, email = ?, cpf = ?, telefone = ?, dataaniversario = ? WHERE id = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getDataAniversario());
            stmt.setInt(6, cliente.getId());
            
            con.setAutoCommit(false);
            
            stmt.executeUpdate();
            con.commit();
            
            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Atualizar Cadastro", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar operação!" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //ConnectionFactory.closeConnection(con, stmt);
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex3) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex3);
            }   
        }
    }
    
    public void delete(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setInt(1, cliente.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Excluir Cadastro", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar operação!" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
    
    public ArrayList <Cliente> getListaClientePorNome(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try {
           stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ? ORDER BY id");
           stmt.setString(1, nome);
           rs = stmt.executeQuery();
           
           while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setDataAniversario(rs.getString("dataaniversario"));
                listaClientes.add(cliente);
            }
           
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao buscar cliente!" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return listaClientes;
    }
}
