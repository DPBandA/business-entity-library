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

package jm.com.dpbennett.business.entity;

import jm.com.dpbennett.business.entity.hrm.BusinessOffice;
import java.util.Date;

/**
 *
 * @author Desmond Bennett
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
