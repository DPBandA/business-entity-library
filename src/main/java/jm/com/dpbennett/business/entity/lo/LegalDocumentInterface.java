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
package jm.com.dpbennett.business.entity.lo;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.dm.Document;
import jm.com.dpbennett.business.entity.dm.DocumentType;
import jm.com.dpbennett.business.entity.fm.Classification;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
public interface LegalDocumentInterface extends BusinessEntity, Comparable, Document {

    @Override
    int compareTo(Object o);

    @Override
    ReturnMessage delete(EntityManager em);

    @Override
    boolean equals(Object object);

    @Override
    Boolean getActive();

    Integer getActualTurnaroundTime();

    Boolean getAutoGenerateNumber();

    @Override
    String getCategory();

    @Override
    Classification getClassification();

    @Override
    String getComments();

    Integer getCurrentDocumentActualTurnaroundTime();

    @Override
    Date getDateEdited();

    @Override
    Date getDateEntered();

    Date getDateOfCompletion();

    Date getDateReceived();

    @Override
    String getDescription();

    String getDocumentForm();

    @Override
    DocumentType getDocumentType();

    @Override
    Employee getEditedBy();

    @Override
    Person getEnteredBy();

    Date getExpectedDateOfCompletion();

    Client getExternalClient();

    String getGoal();

    @Override
    Long getId();

    @Override
    Boolean getIsDirty();

    Integer getMonthReceived();

    @Override
    String getName();

    @Override
    String getNotes();

    @Override
    String getNumber();

    Long getNumberOfDocuments();

    String getPriorityLevel();

    Department getRequestingDepartment();

    Department getResponsibleDepartment();

    Employee getResponsibleOfficer();

    String getRowStyle();

    Long getSequenceNumber();

    @Override
    SystemOption getSetting(String setting, String settingValue, String type, String category);

    @Override
    List<SystemOption> getSettings();

    String getStatus();

    String getStrategicPriority();

    Employee getSubmittedBy();

    Integer getTurnaroundTime();

    @Override
    String getType();

    @Override
    String getUrl();

    Boolean getVisited();

    String getWorkPerformedOnDocument();

    Integer getYearReceived();

    @Override
    int hashCode();

    @Override
    ReturnMessage save(EntityManager em);

    @Override
    ReturnMessage saveUnique(EntityManager em);

    @Override
    void setActive(Boolean active);

    void setActualTurnaroundTime(Integer actualTurnaroundTime);

    void setAutoGenerateNumber(Boolean autoGenerateNumber);

    @Override
    void setCategory(String category);

    @Override
    void setClassification(Classification classification);

    @Override
    void setComments(String comments);

    @Override
    void setDateEdited(Date dateEdited);

    @Override
    void setDateEntered(Date dateEntered);

    void setDateOfCompletion(Date dateOfCompletion);

    void setDateReceived(Date dateReceived);

    @Override
    void setDescription(String description);

    void setDocumentForm(String documentForm);

    @Override
    void setDocumentType(DocumentType documentType);

    @Override
    void setEditedBy(Person person);

    @Override
    void setEnteredBy(Person person);

    void setExpectedDateOfCompletion(Date expectedDateOfCompletion);

    void setExternalClient(Client externalClient);

    void setGoal(String goal);

    @Override
    void setId(Long id);

    @Override
    void setIsDirty(Boolean isDirty);

    void setMonthReceived(Integer monthReceived);

    @Override
    void setName(String name);

    @Override
    void setNotes(String notes);

    @Override
    void setNumber(String number);

    void setNumberOfDocuments(Long numberOfDocuments);

    void setPriorityLevel(String priorityLevel);

    void setRequestingDepartment(Department requestingDepartment);

    void setResponsibleDepartment(Department responsibleDepartment);

    void setResponsibleOfficer(Employee responsibleOfficer);

    void setSequenceNumber(Long sequenceNumber);

    @Override
    void setSetting(String setting, String settingValue, String type, String category);

    @Override
    void setSettings(List<SystemOption> settings);

    void setStatus(String status);

    void setStrategicPriority(String strategicPriority);

    void setSubmittedBy(Employee submittedBy);

    void setTurnaroundTime(Integer turnaroundTime);

    @Override
    void setType(String type);

    @Override
    void setUrl(String url);

    void setVisited(Boolean visited);

    void setWorkPerformedOnDocument(String workPerformedOnDocument);

    void setYearReceived(Integer yearReceived);

    @Override
    String toString();

    @Override
    ReturnMessage validate(EntityManager em);
    
}
