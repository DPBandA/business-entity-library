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

package jm.com.dpbennett.business.entity.cert;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.hrm.Business;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
public interface CertificationInterface extends BusinessEntity, Comparable, Serializable {

    @Override
    int compareTo(Object o);

    @Override
    boolean equals(Object object);

    @Override
    Boolean getActive();

    Client getApplicant();

    String getCertificateNumber();

    Employee getCertificateSignedBy();

    Date getDateIssued();

    Date getExpiryDate();

    Business getGrantedTo();

    @Override
    Long getId();

    @Override
    Boolean getIsDirty();

    @Override
    String getName();

    @Override
    String getNotes();

    String getNumber();

    @Override
    String getType();

    @Override
    int hashCode();

    @Override
    ReturnMessage save(EntityManager em);

    @Override
    void setActive(Boolean active);

    void setApplicant(Client applicant);

    void setCertificateNumber(String certificateNumber);

    void setCertificateSignedBy(Employee certificateSignedBy);

    void setDateIssued(Date dateIssued);

    void setExpiryDate(Date expiryDate);

    void setGrantedTo(Business grantedTo);

    @Override
    void setId(Long id);

    @Override
    void setIsDirty(Boolean isDirty);

    @Override
    void setName(String name);

    @Override
    void setNotes(String notes);

    void setNumber(String number);

    @Override
    void setType(String type);

    @Override
    String toString();

    @Override
    ReturnMessage validate(EntityManager em);
    
}
