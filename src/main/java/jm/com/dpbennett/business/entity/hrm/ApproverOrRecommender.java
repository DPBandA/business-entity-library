/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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
