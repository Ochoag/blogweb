package blogweb.blogweb.blogweb.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import blogweb.blogweb.blogweb.constants.Constants;


@Entity(tableName = Constants.NAME_TABLE_WEBBLOG)
public class Webblog {
    @PrimaryKey(autoGenerate = true)
    public int idwebblog;
    @ColumnInfo(name = "titulo")
    public String titulo;
    @ColumnInfo(name = "autor")
    public String autor;
    @ColumnInfo(name = "fechapublicacion")
    public String fechapublicacion;
    @ColumnInfo(name = "contenido")
    public String contenido;
    @ColumnInfo(name = "iduser")
    public int iduser;

    public int getIdwebblog() {
        return idwebblog;
    }

    public void setIdwebblog(int idwebblog) {
        this.idwebblog = idwebblog;
    }

    public String getGetTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGetAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGetFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(String fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public String getGetContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

}
