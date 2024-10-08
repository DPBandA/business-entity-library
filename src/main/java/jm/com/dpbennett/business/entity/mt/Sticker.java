/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */

package jm.com.dpbennett.business.entity.mt;

import jm.com.dpbennett.business.entity.hrm.Employee;
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
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.hrm.Manufacturer;
import jm.com.dpbennett.business.entity.fm.Product;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "sticker")
public class Sticker implements Product, BusinessEntity, Comparable {
    
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
    @Transient
    private Boolean isDirty;
    
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
    
    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }
        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
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
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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
            oTime = 0L;
        }
        // other object
        if (this.getDateIssued() != null) {
            thisTime = this.getDateIssued().getTime();
        } else {
            thisTime = 0L;
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
    
    public static List<Sticker> findStickersByNumber(EntityManager em, String value) {
       
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Sticker> stickers =
                    em.createQuery("SELECT s FROM Sticker s where UPPER(s.number) like '"
                    + value.toUpperCase().trim() + "%' ORDER BY s.number", Sticker.class).getResultList();
            
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
    
    public static Sticker findStickerByNumber(EntityManager em, String value) {
               
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Sticker> stickers =
                    em.createQuery("SELECT s FROM Sticker s where UPPER(s.number) like '"
                    + value.toUpperCase().trim() + "%' ORDER BY s.number", Sticker.class).getResultList();
            
            if (!stickers.isEmpty()) {
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

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
