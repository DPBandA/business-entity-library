/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sc;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "entrydocumentinspection")
public class EntryDocumentInspection implements Comparable, BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String entryDocumentNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date entryDocumentReportDate;
    private String containerNumbers;
    private String containerSizes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ShippingContainer> shippingContainers;
    private Double CIF;
    private Double SCF;
    private String documentStamped;
    private String profileFlagged;
    private String countryOfConsignment;
    private String vessel;
    private String waybill;
    private String internalNumber;
    private String invoiceNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoiceDate = null;
    private String SCFFreeCode;
    private Double SCFAmountCalculated;
    @Transient
    private Boolean isDirty;

    public EntryDocumentInspection() {
        shippingContainers = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getContainerSizes() {

        if (containerSizes == null) {
            containerSizes = "";
        }

        return containerSizes;
    }

    public void setContainerSizes(String containerSizes) {
        this.containerSizes = containerSizes;
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

    public String getSCFFreeCode() {
        if (SCFFreeCode == null) {
            SCFFreeCode = null;
        }
        return SCFFreeCode;
    }

    public void setSCFFreeCode(String SCFFreeCode) {
        this.SCFFreeCode = SCFFreeCode;
    }

    public Double getSCFAmountCalculated() {
        return SCFAmountCalculated;
    }

    public void setSCFAmountCalculated(Double SCFAmountCalculated) {
        this.SCFAmountCalculated = SCFAmountCalculated;
    }

    public String getInternalNumber() {
        if (internalNumber == null) {
            internalNumber = "";
        }
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getInvoiceNumber() {
        if (invoiceNumber == null) {
            invoiceNumber = "";
        }
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getContainerNumbers() {
        return containerNumbers;
    }

    public void setContainerNumbers(String containerNumbers) {
        this.containerNumbers = containerNumbers;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel;
    }

    public Date getEntryDocumentReportDate() {
        return entryDocumentReportDate;
    }

    public void setEntryDocumentReportDate(Date entryDocumentReportDate) {
        this.entryDocumentReportDate = entryDocumentReportDate;
    }

    public String getCountryOfConsignment() {
        return countryOfConsignment;
    }

    public void setCountryOfConsignment(String countryOfConsignment) {
        this.countryOfConsignment = countryOfConsignment;
    }

    public String getDocumentStamped() {
        return documentStamped;
    }

    public void setDocumentStamped(String documentStamped) {
        this.documentStamped = documentStamped;
    }

    public String getProfileFlagged() {
        return profileFlagged;
    }

    public void setProfileFlagged(String profileFlagged) {
        this.profileFlagged = profileFlagged;
    }

    public Double getCIF() {
        if (CIF == null) {
            CIF = 0.0;
        }
        return CIF;
    }

    public void setCIF(Double CIF) {
        this.CIF = CIF;
    }

    public Double getSCF() {
        return SCF;
    }

    public void setSCF(Double SCF) {
        this.SCF = SCF;
    }

    public String getEntryDocumentNumber() {
        return entryDocumentNumber;
    }

    public void setEntryDocumentNumber(String entryDocumentNumber) {
        this.entryDocumentNumber = entryDocumentNumber;
    }

    public List<ShippingContainer> getShippingContainers() {
        if (shippingContainers == null) {
            shippingContainers = new ArrayList<>();
        }

        return shippingContainers;
    }

    public void setShippingContainers(List<ShippingContainer> shippingContainers) {
        this.shippingContainers = shippingContainers;
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
        if (!(object instanceof EntryDocumentInspection)) {
            return false;
        }
        EntryDocumentInspection other = (EntryDocumentInspection) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getId().toString(), ((EntryDocumentInspection) o).getId().toString());
    }

    @Override
    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List getContainerNumberList() {
        ArrayList containerNumberList = new ArrayList<>();

        if (containerNumbers != null) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(containerNumbers.split("[,;:|/]")));
            //containerNumberList.add("");
            for (String string : list) {
                containerNumberList.add(string.trim());
            }
            return containerNumberList;
        } else {
            return new ArrayList();
        }

    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            // Save new cost components
            if (!getShippingContainers().isEmpty()) {
                for (ShippingContainer shippingContainer : getShippingContainers()) {
                    if ((shippingContainer.getIsDirty() || shippingContainer.getId() == null)
                            && !shippingContainer.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Shipping container save error occurred",
                                "An error occurred while saving a Shipping container",
                                Message.SEVERITY_ERROR_NAME);
                    }
                }
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Entry Document not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
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
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
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

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
