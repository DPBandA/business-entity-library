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
package jm.com.dpbennett.business.entity.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author desbenn
 */
@FacesValidator("turnaroundTimeValidator")
public class TurnaroundTimeValidator extends ValidatorAdapter {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Number tat = (Number) value;
        Boolean tatRequired = (Boolean) component.getAttributes().get("tatRequired");

        if (tat != null) {
            if ((tat.intValue() <= 0) && tatRequired) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Estimated Turnaround Time", "A valid estimated turnaround time (TAT) is required and must be provided."));
            } else if (tat.intValue() < 0) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Estimated Turnaround Time", "A valid estimated turnaround time (TAT) is required and must be provided."));
            }
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Estimated Turnaround Time", "A valid estimated turnaround time (TAT) is required and must be provided."));
        }
    }
}
