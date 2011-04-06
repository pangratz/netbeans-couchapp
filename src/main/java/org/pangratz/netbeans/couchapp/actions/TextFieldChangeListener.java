/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

public interface TextFieldChangeListener {

    /**
     *
     * @param value value which shall be checked for validity
     * @return either null if the value is value or an error message
     */
    public String isValid(String value);
}
