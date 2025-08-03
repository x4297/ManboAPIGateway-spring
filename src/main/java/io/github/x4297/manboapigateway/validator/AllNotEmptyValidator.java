package io.github.x4297.manboapigateway.validator;


import io.github.x4297.manboapigateway.annotation.AllNotEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.Map;


public class AllNotEmptyValidator implements ConstraintValidator<AllNotEmpty, Object>{
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext){
        if(obj == null){
            return false;
        }

        var fields = obj.getClass().getDeclaredFields();
        for(var field : fields){
            field.setAccessible(true);
            try{
                var value = field.get(obj);
                if(
                        value == null || (value instanceof String && ((String)value).isBlank())
                        || (value instanceof Collection<?> && ((Collection<?>)value).isEmpty())
                        || (value instanceof Map<?,?> && ((Map<?, ?>)value).isEmpty())
                ){
                    return false;
                }
            }
            catch(IllegalAccessException e){
                return false;
            }
        }
        return true;
    }
}
