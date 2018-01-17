package appraamlabs.exceptionHandler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.NestedServletException;

import com.jcraft.jsch.JSchException;

import appraamlabs.service.dtos.ErrorResponseDTO;

@ControllerAdvice  
public class GlobalExceptionHandler {  
  
/*    @ResponseStatus(HttpStatus)  
    @ExceptionHandler(value = BaseException.class)  
    public String handleBaseException(BaseException e){  
        return e.getMessage();  
    }*/
  
    @ResponseStatus(HttpStatus.NOT_FOUND)  
    @ExceptionHandler(value = NoSuchElementException.class)  
    public @ResponseBody ErrorResponseDTO handleBaseException(NoSuchElementException e){  
        return new ErrorResponseDTO(Integer.parseInt(HttpStatus.NOT_FOUND.toString()), e.getMessage());  
    }
  
    @ResponseStatus(HttpStatus.NOT_FOUND)  
    @ExceptionHandler(value = ClassNotFoundException.class)  
    public @ResponseBody ErrorResponseDTO handleClassNotFoundException(ClassNotFoundException e){  
        return new ErrorResponseDTO(Integer.parseInt(HttpStatus.NOT_FOUND.toString()), e.getMessage());  
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)  
    @ExceptionHandler(value = NestedServletException.class)  
    public @ResponseBody ErrorResponseDTO handleNestedServletException(NestedServletException e){  
        return new ErrorResponseDTO(Integer.parseInt(HttpStatus.NOT_FOUND.toString()), e.getMessage());  
    }
    
    @ResponseStatus(HttpStatus.CONFLICT)  
    @ExceptionHandler(value = NumberFormatException.class)  
    public @ResponseBody ErrorResponseDTO handleNumberFormatException(NumberFormatException e){  
        return new ErrorResponseDTO(Integer.parseInt(HttpStatus.CONFLICT.toString()), e.getMessage());  
    }
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = JSchException.class)  
    public @ResponseBody ErrorResponseDTO handleJSchException(JSchException e){  
        return new ErrorResponseDTO(Integer.parseInt(HttpStatus.CONFLICT.toString()), e.getMessage());  
    }
    
/*	 @ExceptionHandler(value = Exception.class)  
	    public String handleException(Exception e){
	    	return e.getMessage();
	    }*/  
}  