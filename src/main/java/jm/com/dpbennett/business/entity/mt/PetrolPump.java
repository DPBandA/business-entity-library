/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

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

import jm.com.dpbennett.business.entity.cert.Certification;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.hrm.Manufacturer;
import jm.com.dpbennett.business.entity.sm.Product;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "petrolpump")
public class PetrolPump implements Product, BusinessEntity, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;
    private String type;
    private String serialNumber;
    private String model;
    private String status;
    private Double rate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateScheduledForTest;
    private String name;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Certification certification;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<PetrolPumpNozzle> nozzles;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Sticker> stickers;
    @Transient
    private Boolean isDirty;

    public PetrolPump(PetrolPump src, Long id) {
        this.id = id;
        this.number = src.number;
        this.type = src.type;
        this.serialNumber = src.serialNumber;
        this.model = src.model;
        this.status = src.status;
        this.rate = src.rate;
        this.dateScheduledForTest = src.dateScheduledForTest;
        this.name = src.name;
        this.nozzles = new ArrayList<>();
        this.stickers = new ArrayList<>();
    }

    public PetrolPump() {
        nozzles = new ArrayList<>();
        stickers = new ArrayList<>();
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

    public Date getDateScheduledForTest() {
        return dateScheduledForTest;
    }

    public void setDateScheduledForTest(Date dateScheduledForTest) {
        this.dateScheduledForTest = dateScheduledForTest;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Certification getCertification() {
        if (certification == null) {
            certification = new Certification();
        }
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<PetrolPumpNozzle> getNozzles() {
        Collections.sort(nozzles);
        return nozzles;
    }

    public Integer getNumberOfNozzles() {
        return getNozzles().size();
    }

    public void setNozzles(List<PetrolPumpNozzle> nozzles) {
        this.nozzles = nozzles;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
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
        if (!(object instanceof PetrolPump)) {
            return false;
        }
        PetrolPump other = (PetrolPump) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String identification = "";

        if (serialNumber != null) {
            identification = serialNumber;
        } else if (model != null) {
            identification = model;
        } else {
            identification = "";
        }

        if (id != null) {
            return "(" + id + ") " + identification;
        } else {
            return identification;
        }
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    
    public static PetrolPump findPetrolPumpById(EntityManager em, Long id) {

        try {
            PetrolPump pump = em.find(PetrolPump.class, id);

            return pump;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
