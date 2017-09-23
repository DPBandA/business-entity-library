/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "costcode")
public class CostCode implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50)
    private String name;
    private String code;
    private Double cost;
    private Double rate;
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof CostCode)) {
            return false;
        }
        CostCode other = (CostCode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.CostCode[id=" + id + "]";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public static CostCode findCostCodeByCode(EntityManager em, String code) {

        String newCode = code.replaceAll("'", "''");

        try {
            List<CostCode> codes = em.createQuery("SELECT c FROM CostCode c "
                    + "WHERE c.code "
                    + "= '" + newCode + "'", CostCode.class).getResultList();
            if (codes.size() > 0) {
                return codes.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
    
    public static List<CostCode> findAllCostCodes(EntityManager em) {

        try {
            List<CostCode> codes = em.createQuery("SELECT c FROM CostCode c ORDER BY c.code", CostCode.class).getResultList();

            return codes;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<CostCode>();
        }
    }

    public static CostCode findCostCodeById(EntityManager em, Long id) {

        try {
            CostCode code = em.find(CostCode.class, id);

            return code;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
