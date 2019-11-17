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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Manufacturer;
import jm.com.dpbennett.business.entity.Product;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "petrolpumpnozzle")
public class PetrolPumpNozzle implements Product, BusinessEntity, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String serialNumber;
    private String model;
    private String number;
    @Column(length = 1024)
    private String status; 
    /**
     * The test measure is specified in litres
     */
    private String testMeasures;
    private String comments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PetrolPumpNozzleCalibration> calibrations;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Seal> seals;
    @OneToOne(cascade = CascadeType.ALL)
    private Seal lastSealIssued;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Sticker> stickers;
    @OneToOne(cascade = CascadeType.ALL)
    private Sticker lastStickerIssued;
    @OneToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.ALL)
    private PetrolPumpNozzleCalibration lastCalibration;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Certification> certifications;
    @Transient
    private Boolean isDirty;

     public PetrolPumpNozzle(PetrolPumpNozzle src, Long id) {
        this.id = id; 
        this.name = src.name;
        this.type = src.type;
        this.serialNumber = src.serialNumber;
        this.model = src.model;
        this.number = src.number;
        this.status = src.status;
        this.testMeasures = src.testMeasures;
        this.comments = src.comments;
         
        calibrations = new ArrayList<PetrolPumpNozzleCalibration>();
        seals = new ArrayList<Seal>();
        stickers = new ArrayList<Sticker>();
        certifications = new ArrayList<Certification>();
    }
    
    public PetrolPumpNozzle() {
        calibrations = new ArrayList<PetrolPumpNozzleCalibration>();
        seals = new ArrayList<Seal>();
        stickers = new ArrayList<Sticker>();
        certifications = new ArrayList<Certification>();
    }

    public PetrolPumpNozzle(ArrayList<TestMeasure> measures,
            Seal lastSealIssued,
            Sticker lastStickerIssued) {
        calibrations = new ArrayList<PetrolPumpNozzleCalibration>();
        seals = new ArrayList<Seal>();
        stickers = new ArrayList<Sticker>();

        this.lastSealIssued = lastSealIssued;
        this.lastStickerIssued = lastStickerIssued;
        
        // build measure string
        testMeasures = "" + measures.get(0).getCapacity().toString();
        testMeasures = testMeasures + "," + measures.get(1).getCapacity().toString();
        
        lastCalibration = new PetrolPumpNozzleCalibration(measures);
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

    public List<Certification> getCertifications() {
        Collections.sort(certifications);
        
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PetrolPumpNozzleCalibration getLastCalibration() {
        if (lastCalibration == null) {
            lastCalibration = new PetrolPumpNozzleCalibration();
        }
        return lastCalibration;
    }

    public void setLastCalibration(PetrolPumpNozzleCalibration lastCalibration) {
        this.lastCalibration = lastCalibration;
    }

    public Sticker getLastStickerIssued() {
        if (lastStickerIssued == null) {
            lastStickerIssued = new Sticker();
            return lastStickerIssued;
        } else {
            return lastStickerIssued;
        }
    }

    public void setLastStickerIssued(Sticker lastStickerIssued) {
        this.lastStickerIssued = lastStickerIssued;
    }

    public Seal getLastSealIssued() {
        if (lastSealIssued == null) {
            lastSealIssued = new Seal();
            return lastSealIssued;
        } else {
            return lastSealIssued;
        }
    }

    public void setLastSealIssued(Seal lastSealIssued) {
        this.lastSealIssued = lastSealIssued;
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

    public String getTestMeasures() {
        return testMeasures;
    }

    public void setTestMeasures(String testMeasures) {
        this.testMeasures = testMeasures;
    }

    public List<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<PetrolPumpNozzleCalibration> getCalibrations() {
        Collections.sort(calibrations);
        
        return calibrations;
    }

    public void setCalibrations(List<PetrolPumpNozzleCalibration> calibrations) {
        this.calibrations = calibrations;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Seal> getSeals() {
        return seals;
    }

    public void setSeals(List<Seal> seals) {
        this.seals = seals;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PetrolPumpNozzle)) {
            return false;
        }
        PetrolPumpNozzle other = (PetrolPumpNozzle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + id;
    }

    @Override
    public int compareTo(Object o) {
//        return Collator.getInstance().compare(this.toString(), o.toString());
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
    
    public static List<PetrolPumpNozzle> findPetrolPumpNozzlesByCalibrationJobNumber(EntityManager em, String jobNumber) {
        List<PetrolPumpNozzle> foundPetrolPumpNozzles;

        String searchQuery =
                "SELECT PetrolPumpNozzle FROM PetrolPumpNozzle petrolPumpNozzle"
                + " JOIN petrolPumpNozzle.lastCalibration lastCalibration"
                + " WHERE lastCalibration.job.jobNumber = '" + jobNumber + "'"
                + " ORDER BY lastCalibration.id DESC";
        try {
            foundPetrolPumpNozzles = em.createQuery(searchQuery, PetrolPumpNozzle.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundPetrolPumpNozzles;
    }
    
    

    public static PetrolPumpNozzle findPetrolPumpNozzleById(EntityManager em, Long id) {

        try {
            PetrolPumpNozzle nozzle = em.find(PetrolPumpNozzle.class, id);

            return nozzle;
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
