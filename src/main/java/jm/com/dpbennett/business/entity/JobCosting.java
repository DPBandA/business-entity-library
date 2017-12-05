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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "jobcosting")
public class JobCosting implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CostComponent> costComponents;

    public JobCosting() {
        costComponents = new ArrayList<CostComponent>();
    }

    public JobCosting(JobCosting orgJobCosting) {
       this.name =  orgJobCosting.name;
       costComponents = new ArrayList<CostComponent>();
    }

    public JobCosting(String name) {
        this.name = name;
        costComponents = new ArrayList<CostComponent>();
    }

    public JobCosting(Long id, String name) {
        this.id = id;
        this.name = name;
        costComponents = new ArrayList<CostComponent>();
    }

    public Double getTotalCost() {
        Double total = new Double(0.0);

        for (CostComponent costComponent : costComponents) {
            total = total + costComponent.getCost();
        }

        return total;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<CostComponent> getCostComponents() {
        Collections.sort(costComponents);

        return costComponents;
    }

    public void setCostComponents(List<CostComponent> costComponents) {
        this.costComponents = costComponents;
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
        if (!(object instanceof JobCosting)) {
            return false;
        }
        JobCosting other = (JobCosting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.JobCosting[id=" + id + "]";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
