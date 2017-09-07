/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import jm.com.dpbennett.business.utils.BusinessEntityUtils;

/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "sticker")
public class Sticker implements Product, BusinessEntity, Serializable, Comparable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;
    private Boolean valid;
    private Boolean used;
    private String type;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateIssued;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateExpired;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignee;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAssigned;
    
    public Sticker() {
    }
    
    public Sticker(Sticker original) {
        this.name = original.name;
        this.number = original.number;
        this.valid = original.valid;
        this.used = original.used;
        this.type = original.type;
        this.dateIssued = original.dateIssued;
        this.dateExpired = original.dateExpired;
        this.manufacturer = original.manufacturer;
        this.assignee = original.assignee;
        this.dateAssigned = original.dateAssigned;
    }
    
    public Sticker(String number, Date dateAssigned) {
        this.number = number;
        this.dateAssigned = dateAssigned;
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public Boolean getUsed() {
        return used;
    }
    
    public void setUsed(Boolean used) {
        this.used = used;
    }
    
    public Employee getAssignee() {
        if (assignee == null) {
            assignee = new Employee();
        }
        return assignee;
    }
    
    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }
    
    public Date getDateAssigned() {
        return dateAssigned;
    }
    
    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }
    
    @Override
    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    
    @Override
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public Date getDateExpired() {
        return dateExpired;
    }
    
    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }
    
    public Date getDateIssued() {
        return dateIssued;
    }
    
    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public Boolean getValid() {
        return valid;
    }
    
    public void setValid(Boolean valid) {
        this.valid = valid;
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
        if (!(object instanceof Sticker)) {
            return false;
        }
        Sticker other = (Sticker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "jm.org.bsj.entity.Sticker[id=" + id + "]";
    }
    
    @Override
    public int compareTo(Object o) {
        Long oTime, thisTime;
        // get dates as long values for comparison
        // this object
        if (((Sticker) o).getDateIssued() != null) {
            oTime = ((Sticker) o).getDateIssued().getTime();
        } else {
            oTime = new Long(0L);
        }
        // other object
        if (this.getDateIssued() != null) {
            thisTime = this.getDateIssued().getTime();
        } else {
            thisTime = new Long(0L);
        }
        
        return Collator.getInstance().compare(thisTime.toString(), oTime.toString());
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public void setType(String type) {
        this.type = type;
    }
    
    public static List<Sticker> findStickersByNumber(EntityManager em, String number) {
        
        String newNumber = number.replaceAll("'", "''");
        
        try {
            List<Sticker> stickers =
                    em.createQuery("SELECT s FROM Sticker s where UPPER(s.number) like '"
                    + newNumber.toUpperCase().trim() + "%' ORDER BY s.number", Sticker.class).getResultList();
            return stickers;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Sticker>();
        }
    }
    
    public static Sticker findStickerByNumber(List<Sticker> stickers, String number) {
        for (Sticker sticker : stickers) {
            if (sticker.getNumber().equals(number)) {
                return sticker;
            }
        }
        
        return null;
    }
    
    public static Sticker findStickerByNumber(EntityManager em, String number) {
        
        String newNumber = number.replaceAll("'", "''");
        
        try {
            List<Sticker> stickers =
                    em.createQuery("SELECT s FROM Sticker s where UPPER(s.number) like '"
                    + newNumber.toUpperCase().trim() + "%' ORDER BY s.number", Sticker.class).getResultList();
            
            if (stickers.size() > 0) {
                return stickers.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        return null;
    }
    
    public static Sticker getDefaultSticker(EntityManager em, String number) {
        Sticker sticker = Sticker.findStickerByNumber(em, number);

        if (sticker == null) {
            sticker = new Sticker();

            em.getTransaction().begin();
            sticker.setNumber(number);
            BusinessEntityUtils.saveBusinessEntity(em, sticker);
            em.getTransaction().commit();
        }

        return sticker;
    }
}
