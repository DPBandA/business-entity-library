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
package jm.com.dpbennett.business.entity.fs;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
import java.text.Collator;
import java.time.LocalDateTime;
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.mt.Test;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "foodtest")
public class FoodTest implements Test, Serializable, Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    private static final System.Logger LOG = System.getLogger(FoodTest.class.getName());
    public static FoodTest findByName(EntityManager em, String value,
            Boolean ignoreCase) {

        List<FoodTest> foodTests;

        try {

            if (ignoreCase) {
                foodTests = em.createQuery("SELECT t FROM FoodTest t "
                        + "WHERE UPPER(t.name) "
                        + "= '" + value.toUpperCase() + "'",
                        FoodTest.class).getResultList();
            } else {
                foodTests = em.createQuery("SELECT t FROM FoodTest t "
                        + "WHERE t.name "
                        + "= '" + value + "'",
                        FoodTest.class).getResultList();
            }

            if (!foodTests.isEmpty()) {
                return foodTests.get(0);
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double hourlyRate;
    private String type;
    private LocalDateTime testDate;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee testDoneBy;
    private LocalDateTime reTestDate;
    private String category;
    @Transient
    private Boolean isDirty;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof FoodTest)) {
            return false;
        }
        FoodTest other = (FoodTest) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "jm.com.dpbennett.entity.FoodTest[id=" + id + "]";
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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public LocalDateTime getTestDate() {
        return testDate;
    }

    @Override
    public void setTestDate(LocalDateTime testDate) {
        this.testDate = testDate;
    }

    @Override
    public Employee getTestDoneBy() {

        if (testDoneBy == null) {
            return new Employee();
        }

        return testDoneBy;
    }

    @Override
    public void setTestDoneBy(Employee testDoneBy) {
        this.testDoneBy = testDoneBy;
    }

    @Override
    public LocalDateTime getReTestDate() {
        return reTestDate;
    }

    @Override
    public void setReCalibrationDate(LocalDateTime reTestDate) {
        this.reTestDate = reTestDate;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    @Override
    public String getCateogy() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            if (testDoneBy != null) {
                testDoneBy.save(em);
            }
            
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Food Test not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(LocalDateTime dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(LocalDateTime dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        try {

            if (this.id == null) {
                FoodTest existing = FoodTest.findByName(em, this.name, false);
                if (existing != null) {
                    return new ReturnMessage(false, "Food Test exists");
                } else {
                    return save(em);
                }
            } else {
                return save(em);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Food Test not saved");
    }

    @Override
    public List<SystemOption> getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SystemOption getSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static System.Logger getLOG() {
        return LOG;
    }
    
    

}
