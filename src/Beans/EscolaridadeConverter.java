/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author flavio
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;


/**
 *
 * @author flavio
 */

/*
@FacesConverter(forClass = Tipo.class)
*/
@FacesConverter("escolaridadeConverter")
public class EscolaridadeConverter implements Converter {
 
     @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        
        if (value != null && !value.isEmpty()) {
            return (Escolaridade) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
     public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Escolaridade) {
        	Escolaridade escolaridade = (Escolaridade) object;
            
                uic.getAttributes().put( String.valueOf(escolaridade.getId()), escolaridade);
                return String.valueOf(escolaridade.getId());
            
        }
        return "";
    }
      
     
}     