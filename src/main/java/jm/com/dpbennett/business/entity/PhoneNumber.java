/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "phonenumber")
@XmlRootElement
public class PhoneNumber implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String countryOrRegionCode;
    private String cityOrAreaCode;
    private String localNumber;
    private String extension;

    public PhoneNumber() {
    }

    public PhoneNumber(PhoneNumber src) {
        doCopy(src);
    }

    public final void doCopy(PhoneNumber src) {
        type = src.type;
        countryOrRegionCode = src.countryOrRegionCode;
        cityOrAreaCode = src.cityOrAreaCode;
        localNumber = src.localNumber;
        extension = src.extension;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCityOrAreaCode() {
        if (cityOrAreaCode == null) {
            cityOrAreaCode = "";
        }
        return cityOrAreaCode;
    }

    public void setCityOrAreaCode(String cityOrAreaCode) {
        this.cityOrAreaCode = cityOrAreaCode;
    }

    public String getCountryOrRegionCode() {
        if (countryOrRegionCode == null) {
            countryOrRegionCode = "";
        }
        return countryOrRegionCode;
    }

    public void setCountryOrRegionCode(String countryOrRegionCode) {
        this.countryOrRegionCode = countryOrRegionCode;
    }

    public String getExtension() {
        if (extension == null) {
            extension = "";
        }
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getLocalNumber() {
        if (localNumber == null) {
            localNumber = "";
        }
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public String getType() {
        if (type == null) {
            type = "";
        }
        return type;
    }

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
        if (!(object instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber other = (PhoneNumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (localNumber == null) {
            return "";
        } else {
            return localNumber;
        }
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {
        
    }
}
