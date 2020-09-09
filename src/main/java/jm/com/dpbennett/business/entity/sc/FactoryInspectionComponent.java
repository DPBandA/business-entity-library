/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2020  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sc;

import java.io.Serializable;
import java.text.Collator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "factoryinspectioncomponent")
public class FactoryInspectionComponent implements BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String item;
    private String category;
    @Column(length = 1024)
    private String comments;
    private Boolean isHeading;
    private String results;
    private Boolean satisfactory;
    @Transient
    private Boolean isDirty;

    public FactoryInspectionComponent() {
        this.name = "";
        this.item = "";
        this.category = "";
        this.comments = "";
        this.isHeading = false;
        this.results = "";
        this.satisfactory = false;
    }

    public FactoryInspectionComponent(FactoryInspectionComponent src) {
        this.name = src.name;
        this.item = src.item;
        this.category = src.category;
        this.comments = src.comments;
        this.isHeading = src.isHeading;
        this.results = src.results;
        this.satisfactory = src.satisfactory;
    }

    public FactoryInspectionComponent(String name, String category, Boolean isHeading) {
        this.name = name;
        this.item = "";
        this.category = category;
        this.comments = "";
        this.isHeading = isHeading;
        this.results = "";
        this.satisfactory = false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Boolean getSatisfactory() {
        if (satisfactory == null) {
            satisfactory = false;
        }
        return satisfactory;
    }

    public void setSatisfactory(Boolean satisfactory) {
        this.satisfactory = satisfactory;
    }

    public String getIsSatisfactory() {
        if (getSatisfactory()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setIsSatisfactory(String satisfactory) {
        if (satisfactory.equals("Yes")) {
            setSatisfactory(true);
        } else {
            setSatisfactory(false);
        }
    }

    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }

        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsHeading() {
        return isHeading;
    }

    public void setIsHeading(Boolean isHeading) {
        this.isHeading = isHeading;
    }

    public String getResults() {

        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryInspectionComponent)) {
            return false;
        }
        FactoryInspectionComponent other = (FactoryInspectionComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FactoryInspectionComponent{" + "id=" + id + '}';
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Factory inspection component not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), ((FactoryInspectionComponent) o).toString());
    }
}
