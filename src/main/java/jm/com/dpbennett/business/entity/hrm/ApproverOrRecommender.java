/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jm.com.dpbennett.business.entity.hrm;

/**
 *
 * @author Desmond Bennett
 */
public class ApproverOrRecommender extends Employee {

    private Boolean approver;

    public Boolean getApprover() {
        return approver;
    }

    public void setApprover(Boolean approver) {
        this.approver = approver;
    }

    public ApproverOrRecommender(Boolean approver, String firstName, String lastName) {
        super(firstName, lastName);
        this.approver = approver;
    }

    public ApproverOrRecommender(Boolean approver, Employee employee) {
        super.setId(employee.getId());
        super.setFirstName(employee.getFirstName());
        super.setLastName(employee.getLastName());
        super.setPositions(employee.getPositions());
        this.approver = approver;
    }

    @Override
    public String getAllPositions() {
        return super.getAllPositions();
    }

    public String getNameWithRole() {
        if (approver) {
            return getName() + " (Appr'er)";
        } else {
            return getName() + " (Rec'er)";
        }
    }

}
