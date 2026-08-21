/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.Transient;
import jm.com.dpbennett.business.entity.hrm.Employee;
import java.text.Collator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.hrm.Manufacturer;
import jm.com.dpbennett.business.entity.fm.Product;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "seal")
public class Seal implements Product, BusinessEntity, Comparable {

    private static final long serialVersionUID = 1L;
    private static final System.Logger LOG = System.getLogger(Seal.class.getName());
    public static Seal findSealByNumber(List<Seal> seals, String number) {
        for (Seal seal : seals) {
            if (seal.getNumber().equals(number)) {
                return seal;
            }
        }
        
        return null;
    }
    public static List<Seal> findSealsByNumber(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Seal> seals
                    = em.createQuery("SELECT s FROM Seal s where UPPER(s.number) like '"
                            + value.toUpperCase().trim() + "%' ORDER BY s.number", Seal.class).getResultList();
            
            return seals;
            
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public static Seal findSealByNumber(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Seal> seals = em.createQuery("SELECT s FROM Seal s "
                    + "WHERE s.number "
                    + "= '" + value + "'", Seal.class).getResultList();
            if (!seals.isEmpty()) {
                return seals.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    public static Seal getDefaultSeal(EntityManager em, String number) {
        Seal seal = Seal.findSealByNumber(em, number);
        
        if (seal == null) {
            seal = new Seal();
            
            em.getTransaction().begin();
            seal.setNumber(number);
            BusinessEntityUtils.saveBusinessEntity(em, seal);
            em.getTransaction().commit();
        }
        
        return seal;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private String name;
    private String number;
    private String type;
    private String note;
    private String serialNumber;
    private String model;
    private Boolean valid;
    private Boolean used;
    private LocalDateTime dateIssued;
    private LocalDateTime dateExpired;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignee;
    private LocalDateTime dateAssigned;
    @Transient
    private Boolean isDirty;

    public Seal() {
    }

    public Seal(Seal original) {
        this.name = original.name;
        this.number = original.number;
        this.type = original.type;
        this.note = original.note;
        this.serialNumber = original.serialNumber;
        this.model = original.model;
        this.dateIssued = original.dateIssued;
        this.dateExpired = original.dateExpired;
        this.manufacturer = original.manufacturer;
        this.assignee = original.assignee;
        this.dateAssigned = original.dateAssigned;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Employee getAssignee() {
        if (assignee == null) {
            return new Employee();
        }

        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(LocalDateTime dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public LocalDateTime getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(LocalDateTime dateExpired) {
        this.dateExpired = dateExpired;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public Manufacturer getManufacturer() {

        if (manufacturer == null) {
            return new Manufacturer();
        }

        return manufacturer;
    }

    @Override
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDateTime getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(LocalDateTime dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Seal)) {
            return false;
        }
        Seal other = (Seal) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "(" + id + ")";
    }

    @Override
    public int compareTo(Object o) {
      if ((((Seal) o).id != null) && (this.id != null)) {
            return Collator.getInstance().compare(
                    Long.toString(((BusinessEntity) o).getId()),
                    Long.toString(this.getId()));
        } else {
            return 0;
        }
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
    public ReturnMessage save(EntityManager em) {
        try {

            if (manufacturer != null) {
                manufacturer.save(em);
            }
            if (assignee != null) {
                assignee.save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Seal not saved");
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
    public LocalDateTime getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(LocalDateTime dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(LocalDateTime dateEdited) {
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

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SystemOption> getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SystemOption getSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
