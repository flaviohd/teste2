/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author flavio
 */
@FacesValidator("SenhaValidador")
public class SenhaValidador implements Validator{

	
        private static final String EMAIL_PATTERN1 = "(?=.*[a-z])";
         private static final String EMAIL_PATTERN2 = "(?=.*[A-Z])";
         private static final String EMAIL_PATTERN3 = "(?=.*[0-9])";
          private static final String EMAIL_PATTERN4 =  "(?=.*[@#$%^&+=])";
        


        private final Pattern pattern1;
        private final Pattern pattern2;
        private final Pattern pattern3;
        private final Pattern pattern4;

	private Matcher matcher;
   
	
	public SenhaValidador(){
		  pattern1 = Pattern.compile(EMAIL_PATTERN1);
                   pattern2 = Pattern.compile(EMAIL_PATTERN2);
                    pattern3 = Pattern.compile(EMAIL_PATTERN3);
                     pattern4 = Pattern.compile(EMAIL_PATTERN4);
          
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		
		if(value.toString().length()<8){
		FacesMessage msg = 
		new FacesMessage(null, "A senha deve possuir no mínimo 8 caracteres.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
                
                
                  //matcher = pattern3.matcher(value.toString().find());
		
                matcher = pattern1.matcher(value.toString());
                 System.out.println("Value: " + value.toString());
                System.out.println("Matcher: " + matcher.matches());
                System.out.println("Pattern1: " + pattern1.matcher(value.toString()).matches());
                System.out.println("Pattern2: " + pattern1.matcher("hhhh").matches());
                 System.out.println("Pattern2: " + pattern2.matcher("HH").matches());
		if(!pattern1.matcher(value.toString()).find()){
                    System.out.println("Matcher Encontrou!!");
		FacesMessage msg = 
		new FacesMessage(null, "A senha deve possuir pelo menos uma letra minúscula.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
                
                matcher = pattern2.matcher(value.toString());
		if(!pattern2.matcher(value.toString()).find()){
		FacesMessage msg = 
		new FacesMessage(null, "A senha deve possuir pelo menos uma letra maiúscula.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
                
                if(!pattern3.matcher(value.toString()).find()){
		FacesMessage msg = 
		new FacesMessage(null, "A senha deve possuir pelo menos um número.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
                
               
                
                 matcher = pattern4.matcher(value.toString());
		if(!pattern4.matcher(value.toString()).find()){
		FacesMessage msg = 
		new FacesMessage(null, "A senha deve possuir pelo menos um caractere especial.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}



	}
}