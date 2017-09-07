/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

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
import jm.com.dpba.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "seal")
public class Seal implements Product, BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;
    private String type;
    private String note;
    private String serialNumber;
    private String model;
    private Boolean valid;
    private Boolean used;
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

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
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
        return manufacturer;
    }

    @Override
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seal)) {
            return false;
        }
        Seal other = (Seal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + id + ")";
    }

    @Override
    public int compareTo(Object o) {
        Long oTime, thisTime;
        // get dates as long values for comparison
        // this object
        if (((Seal) o).getDateIssued() != null) {
            oTime = ((Seal) o).getDateIssued().getTime();
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
    
    
    public static Seal findSealByNumber(List<Seal> seals, String number) {
        for (Seal seal : seals) {
            if (seal.getNumber().equals(number)) {
                return seal;
            }
        }

        return null;
    }
    
     public static List<Seal> findSealsByNumber(EntityManager em, String number) {

        String newNumber = number.replaceAll("'", "''");

        try {
            List<Seal> seals =
                    em.createQuery("SELECT s FROM Seal s where UPPER(s.number) like '"
                    + newNumber.toUpperCase().trim() + "%' ORDER BY s.number", Seal.class).getResultList();
            return seals;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Seal>();
        }
    }

   
    public static Seal findSealByNumber(EntityManager em, String number) {

        String newNumber = number.replaceAll("'", "''");

        try {
            List<Seal> seals = em.createQuery("SELECT s FROM Seal s "
                    + "WHERE s.number "
                    + "= '" + newNumber + "'", Seal.class).getResultList();
            if (seals.size() > 0) {
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

    
}
