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

package jm.com.dpbennett.business.entity.dm;

import jm.com.dpbennett.business.entity.fm.Classification;

/**
 *
 * @author dbennett
 */
public interface Document {

    public String getDescription();

    public void setDescription(String description);

    public String getNotes();

    public void setNotes(String notes);

    public String getNumber();

    public void setNumber(String number);

    public DocumentType getDocumentType();

    public void setDocumentType(DocumentType documentType);

    public Classification getClassification();
    
    public void setClassification(Classification classification);

    public String getUrl();

    public void setUrl(String url);
}
