package Model;

import Model.Topico;
import Model.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADRIANO
 */
public class Comentario {

    private int id;
    private String comentario;
    private Usuario usuario;
    private Topico topico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        //Usuario us = new Usuario();
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", comentario=" + comentario + ", usuario=" + usuario + ", topico=" + topico + '}';
    }

}
