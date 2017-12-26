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

import java.util.Date;
import java.util.List;

/**
 *
 * @author dbennett
 */
public interface Customer {

    public String getName();

    public void setName(String name);

    public List<Address> getAddresses();

    public void setAddresses(List<Address> addresses);

    public List<Contact> getContacts();

    public void setContacts(List<Contact> contacts);

    public Address getDefaultAddress();
    
    public Contact getDefaultContact();

    public Date getDateLastAccessed();

    public void setDateLastAccessed(Date dateLastAccessed);

    public String getNumber();

    public void setNumber(String number);

    public String getType();

    public void setType(String type);

    public Date getDateFirstReceived();

    public void setDateFirstReceived(Date dateFirstReceived);

    public String getNotes();

    public void setNotes(String notes);

    public String getTaxRegistrationNumber();

    public void setTaxRegistrationNumber(String taxRegistrationNumber);
}
