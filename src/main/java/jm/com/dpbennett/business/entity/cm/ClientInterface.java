/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2023  D P Bennett & Associates Limited

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

package jm.com.dpbennett.business.entity.cm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.fm.AccPacCustomer;
import jm.com.dpbennett.business.entity.fm.Discount;
import jm.com.dpbennett.business.entity.fm.Tax;
import jm.com.dpbennett.business.entity.hrm.Address;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Internet;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
public interface ClientInterface extends BusinessEntity, Comparable, Customer, Serializable {

    Address addAddress(Address address);

    Contact addContact(Contact contact);

    @Override
    int compareTo(Object o);

    /**
     * Copy the client without copying the id field
     *
     * @param src
     */
    void doCopy(Client src);

    @Override
    boolean equals(Object object);

    String getAccountingId();

    Boolean getActive();

    @Override
    List<Address> getAddresses();

    Address getBillingAddress();

    List<Address> getBillingAddresses();

    Contact getBillingContact();

    @Override
    List<Contact> getContacts();

    Double getCreditLimit();

    Date getDateEdited();

    Date getDateEntered();

    @Override
    Date getDateFirstReceived();

    @Override
    Date getDateLastAccessed();

    /**
     * Returns the first found address with billing type "Billing" as the main
     * billing address.
     *
     * @return
     */
    @Override
    Address getDefaultAddress();

    /**
     * Get the first main contact which is treated as the main contact in the
     * list of contacts.
     *
     * @return
     */
    @Override
    Contact getDefaultContact();

    Tax getDefaultTax();

    Discount getDiscount();

    Employee getEditedBy();

    Employee getEnteredBy();

    AccPacCustomer getFinancialAccount();

    @Override
    Long getId();

    String getIdentification();

    String getIdentificationType();

    Boolean getInternal();

    Boolean getInternational();

    Internet getInternet();

    String getIsActive();

    @Override
    Boolean getIsDirty();

    /**
     * Get the main contact which is treated as the main contact in the list of
     * contacts.
     *
     * @return
     */
    Contact getMainContact();

    @Override
    String getName();

    @Override
    String getNotes();

    @Override
    String getNumber();

    String getStringListOfContactPhoneNumbers();

    Boolean getTag();

    Boolean getTaxExempt();

    @Override
    String getTaxRegistrationNumber();

    /**
     * This method guards against returning very long names. This is used in an
     * autocomplete JSF component for instance to prevent the list of clients
     * from extending beyond the screen. In the future, the maximum length of
     * say 50 will be a value stored in the resource bundle of the BEL.
     *
     * @return
     */
    String getTruncatedName();

    @Override
    String getType();

    String getTypeOfBusiness();

    @Override
    int hashCode();

    @Override
    ReturnMessage save(EntityManager em);

    void setAccountingId(String accountingId);

    void setActive(Boolean active);

    @Override
    void setAddresses(List<Address> addresses);

    void setBillingAddress(Address billingAddress);

    void setBillingContact(Contact billingContact);

    @Override
    void setContacts(List<Contact> contacts);

    void setCreditLimit(Double creditLimit);

    void setDateEdited(Date dateEdited);

    void setDateEntered(Date dateEntered);

    @Override
    void setDateFirstReceived(Date dateFirstReceived);

    @Override
    void setDateLastAccessed(Date dateLastAccessed);

    void setDefaultTax(Tax defaultTax);

    void setDiscount(Discount discount);

    void setEditedBy(Employee editedBy);

    void setEnteredBy(Employee enteredBy);

    void setFinancialAccount(AccPacCustomer financialAccount);

    @Override
    void setId(Long id);

    void setIdentification(String identification);

    void setIdentificationType(String identificationType);

    void setInternal(Boolean internal);

    void setInternational(Boolean international);

    void setInternet(Internet internet);

    void setIsActive(String active);

    @Override
    void setIsDirty(Boolean isDirty);

    @Override
    void setName(String name);

    @Override
    void setNotes(String notes);

    @Override
    void setNumber(String number);

    void setTag(Boolean tag);

    void setTaxExempt(Boolean taxExempt);

    @Override
    void setTaxRegistrationNumber(String taxRegistrationNumber);

    void setTruncatedName(String name);

    @Override
    void setType(String type);

    void setTypeOfBusiness(String typeOfBusiness);

    @Override
    String toString();

    @Override
    ReturnMessage validate(EntityManager em);
    
}
