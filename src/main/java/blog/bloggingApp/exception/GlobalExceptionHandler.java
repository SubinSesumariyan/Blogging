package blog.bloggingApp.exception;

import blog.bloggingApp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message= ex.getMessage();

        ApiResponse apiResponse=new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse> (apiResponse, HttpStatus.NOT_FOUND);
    }


   /* @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        String message=ex.getMessage();
        return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);

    }*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String message=ex.getMessage();
        return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
