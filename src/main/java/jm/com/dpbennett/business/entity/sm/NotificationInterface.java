/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2022  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sm;

import java.util.Date;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.BusinessEntity;

/**
 *
 * @author Desmond Bennett
 */
public interface NotificationInterface extends BusinessEntity {

    public Long getOwnerId();

    public void setOwnerId(Long ownerId);

    public String getSubject();

    public void setSubject(String subject);

    public String getMessage();

    public void setMessage(String message);
   
    public Boolean getActive();

    public void setActive(Boolean active);

    public String getActionToTake();

    public void setActionToTake(String actionToTake);

    public String getActionTaken();

    public void setActionTaken(String actionTaken);

    public String getType();

    public void setType(String type);

    public String getReference();

    public void setReference(String reference);

    public Date getDueTime();

    public void setDueTime(Date dueTime);

    public String getPeriodType();

    public void setPeriodType(String periodType);

    public Long getRecurrencePeriod();

    public void setRecurrencePeriod(Long recurrencePeriod);

    public String getComment();

    public void setComment(String comment);

    public String getStatus();

    public void setStatus(String status);
    
    public Boolean delete(EntityManager em);

}
