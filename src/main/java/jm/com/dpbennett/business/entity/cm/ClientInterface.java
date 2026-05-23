/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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

    void doCopy(Client src);

    @Override
    boolean equals(Object object);

    String getAccountingId();

    @Override
    Boolean getActive();

    @Override
    List<Address> getAddresses();

    Address getBillingAddress();

    List<Address> getBillingAddresses();

    Contact getBillingContact();

    @Override
    List<Contact> getContacts();

    Double getCreditLimit();

    @Override
    LocalDate getDateEdited();

    @Override
    LocalDate getDateEntered();

    @Override
    LocalDate getDateFirstReceived();

    @Override
    LocalDate getDateLastAccessed();

    @Override
    Address getDefaultAddress();

    @Override
    Contact getDefaultContact();

    Tax getDefaultTax();

    Discount getDiscount();

    @Override
    Employee getEditedBy();

    @Override
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

    String getTruncatedName();

    @Override
    String getType();

    String getTypeOfBusiness();

    @Override
    int hashCode();

    @Override
    ReturnMessage save(EntityManager em);

    void setAccountingId(String accountingId);

    @Override
    void setActive(Boolean active);

    @Override
    void setAddresses(List<Address> addresses);

    void setBillingAddress(Address billingAddress);

    void setBillingContact(Contact billingContact);

    @Override
    void setContacts(List<Contact> contacts);

    void setCreditLimit(Double creditLimit);

    @Override
    void setDateEdited(LocalDate dateEdited);

    @Override
    void setDateEntered(LocalDate dateEntered);

    @Override
    void setDateFirstReceived(LocalDate dateFirstReceived);

    @Override
    void setDateLastAccessed(LocalDate dateLastAccessed);

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
