/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ujian.demo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Arhab Zuhan Assaifuddin 20200140062
 */
@Entity
@Table(name = "sim")
@NamedQueries({
    @NamedQuery(name = "Sim.findAll", query = "SELECT s FROM Sim s"),
    @NamedQuery(name = "Sim.findById", query = "SELECT s FROM Sim s WHERE s.id = :id"),
    @NamedQuery(name = "Sim.findByNk", query = "SELECT s FROM Sim s WHERE s.nk = :nk"),
    @NamedQuery(name = "Sim.findByNama", query = "SELECT s FROM Sim s WHERE s.nama = :nama"),
    @NamedQuery(name = "Sim.findByTl", query = "SELECT s FROM Sim s WHERE s.tl = :tl"),
    @NamedQuery(name = "Sim.findByTimestamp", query = "SELECT s FROM Sim s WHERE s.timestamp = :timestamp")})
public class Sim implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NK")
    private String nk;
    @Basic(optional = false)
    @Column(name = "NAMA")
    private String nama;
    @Basic(optional = false)
    @Column(name = "TL")
    private String tl;
    @Basic(optional = false)
    @Lob
    @Column(name = "Photo")
    private byte[] photo;
    @Basic(optional = false)
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Sim() {
    }

    public Sim(Integer id) {
        this.id = id;
    }

    public Sim(Integer id, String nk, String nama, String tl, byte[] photo, Date timestamp) {
        this.id = id;
        this.nk = nk;
        this.nama = nama;
        this.tl = tl;
        this.photo = photo;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNk() {
        return nk;
    }

    public void setNk(String nk) {
        this.nk = nk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sim)) {
            return false;
        }
        Sim other = (Sim) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ujian.demo.Sim[ id=" + id + " ]";
    }
    
}
