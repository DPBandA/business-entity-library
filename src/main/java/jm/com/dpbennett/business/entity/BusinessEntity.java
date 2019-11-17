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

import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
public interface BusinessEntity {
   
    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public ReturnMessage save(EntityManager em);

    public ReturnMessage validate(EntityManager em);  

    public Boolean getIsDirty();
    
    public void setIsDirty(Boolean isDirty);
    
    /**
     * The actions that can be performed with respect to an entity.
     */
    public enum Action {
        CREATE, COMPLETE, EDIT, APPROVE, DELETE, CANCEL, PREPARE, INVOICE,
        COSTING, REQUEST
    }
    
}
