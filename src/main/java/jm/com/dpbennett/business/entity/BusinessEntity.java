/*
Business Entity Library (BEL) - A foundational library.
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
package jm.com.dpbennett.business.entity;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 * Common contract implemented by BEL business entities.
 *
 * Audit values include both a date and a time, so LocalDateTime is used for
 * dateEntered and dateEdited. Domain values representing calendar-only dates
 * should use LocalDate instead.
 *
 * @author Desmond Bennett
 */
public interface BusinessEntity extends Serializable {

    Long getId();

    void setId(Long id);

    Boolean getActive();

    void setActive(Boolean active);

    String getName();

    void setName(String name);

    String getType();

    void setType(String type);

    String getCategory();

    void setCategory(String category);

    LocalDateTime getDateEntered();

    void setDateEntered(LocalDateTime dateEntered);

    LocalDateTime getDateEdited();

    void setDateEdited(LocalDateTime dateEdited);

    ReturnMessage save(EntityManager em);

    ReturnMessage saveUnique(EntityManager em);

    ReturnMessage delete(EntityManager em);

    ReturnMessage validate(EntityManager em);

    Boolean getIsDirty();

    void setIsDirty(Boolean isDirty);

    String getDescription();

    void setDescription(String description);

    String getNotes();

    void setNotes(String notes);

    String getComments();

    void setComments(String comments);

    Person getEditedBy();

    void setEditedBy(Person person);

    Person getEnteredBy();

    void setEnteredBy(Person person);

    List<SystemOption> getSettings();

    void setSettings(List<SystemOption> settings);

    SystemOption getSetting(String setting, String settingValue, String type, String category);

    void setSetting(String setting, String settingValue, String type, String category);

    enum Action {
        CREATE, COMPLETE, EDIT, APPROVE, DELETE, CANCEL, PREPARE, INVOICE,
        COSTING, REQUEST, PAYMENT, RECOMMEND
    }
}
