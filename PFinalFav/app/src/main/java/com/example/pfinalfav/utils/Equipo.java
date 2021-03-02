package com.example.pfinalfav.utils;

import java.io.Serializable;

public class Equipo implements Serializable {
    private String nombre, descripcion, escudo, banner, idTeam, twitter, facebook, web, insta;

    public Equipo() {
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", escudo='" + escudo + '\'' +
                ", banner='" + banner + '\'' +
                ", idTeam='" + idTeam + '\'' +
                ", twitter='" + twitter + '\'' +
                ", facebook='" + facebook + '\'' +
                ", web='" + web + '\'' +
                ", insta='" + insta + '\'' +
                '}';
    }

    public Equipo(String nombre, String escudo, String descripcion, String banner, String idTeam, String facebook, String twitter, String insta, String web) {
        this.nombre = nombre;
        this.insta = insta;
        this.descripcion = descripcion;
        this.escudo = escudo;
        this.banner = banner;

        this.twitter = twitter;
        this.facebook = facebook;
        this.web = web;
        this.idTeam = idTeam;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }
}
