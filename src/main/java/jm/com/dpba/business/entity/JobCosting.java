/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
}
