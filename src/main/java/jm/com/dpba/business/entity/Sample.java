/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.util.Date;

/**
 *
 * @author dbennett
 */
public interface Sample {

    public Employee getReceivedBy();

    public void setReceivedBy(Employee receivedBy);

    public Employee getSampledBy();

    public void setSampledBy(Employee sampledBy);

    public String getCode();

    public void setCode(String code);

    public Date getDateReturned();

    public void setDateReturned(Date dateReturned);

    public Date getDateSampled();

    public void setDateSampled(Date dateSampled);

    public BusinessOffice getRegulatoryOffice();

    public void setRegulatoryOffice(BusinessOffice regulatoryOffice);

    public Long getSampleQuantity();

    public void setSampleQuantity(Long sampleQuantity);

    public Integer getMethodOfDisposal();

    public void setMethodOfDisposal(Integer methodOfDisposal);

    public String getReference();

    public void setReference(String reference);

    public Long getReferenceIndex();

    public void setReferenceIndex(Long referenceIndex);

    public Date getDateReceived();

    public void setDateReceived(Date dateReceived);

    public String getDescription();

    public void setDescription(String description);

    public Long getQuantity();

    public void setQuantity(Long quantity);

    public String getUnitOfMeasure();

    public void setUnitOfMeasure(String unitOfMeasure);

    public String getName();

    public void setName(String name);

    public Manufacturer getManufacturer();

    public void setManufacturer(Manufacturer manufacturer);

    public String getType();

    public void setType(String type);
}
