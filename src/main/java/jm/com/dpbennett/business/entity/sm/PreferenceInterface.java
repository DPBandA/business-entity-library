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

import jm.com.dpbennett.business.entity.BusinessEntity;

/**
 *
 * @author Desmond Bennett
 */
public interface PreferenceInterface extends BusinessEntity {

    String getCategory();

    String getDescription();

    String getPreferenceValue();

    String getRoles();

    String getType();

    void setCategory(String category);

    void setDescription(String description);

    void setPreferenceValue(String preferenceValue);

    void setRoles(String roles);

    void setType(String type);
    
}