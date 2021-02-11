/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author jorop
 */
public class urls {
    private int usuario;
    private String url,titulo,idHistorial;

    public urls() {
    }
    
     public urls( String titulo,String url) {
        this.titulo = titulo;
        this.url=url;
    }
    
    public urls(String idHistorial, String titulo,String url) {
        this.idHistorial = idHistorial;
        this.titulo = titulo;
        this.url=url;
    }
    
    public String getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(String idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
