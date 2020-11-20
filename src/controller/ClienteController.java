/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.bean.Cliente;
import model.dao.ClienteDAO;

/**
 *
 * @author Vanessa
 */
public class ClienteController {
    public void create (String nome, String email, String cpf, String telefone, String dataAniversario) {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setDataAniversario(dataAniversario);
        
        clienteDAO.create(cliente);
    }
    
    public ArrayList<Cliente> read(){
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.read();
    }
    
    public void update (int id, String nome, String email, String cpf, String telefone, String dataAniversario) {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setDataAniversario(dataAniversario);
        
        clienteDAO.update(cliente);
    }
    
    public void delete(int id){
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente.setId(id);
        
        clienteDAO.delete(cliente);
    }
    
    public ArrayList<Cliente> getListaClientePorNome(String nome) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.getListaClientePorNome(nome);
    }
}
