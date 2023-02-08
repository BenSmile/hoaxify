package cd.bensmile.hoaxify.controllers;

import cd.bensmile.hoaxify.errors.ApiError;
import cd.bensmile.hoaxify.models.User;
import cd.bensmile.hoaxify.services.UserService;
import cd.bensmile.hoaxify.shared.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return new GenericResponse("User saved");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(400, "Validation error", request.getServletPath());
        BindingResult bindingResult = exception.getBindingResult();
        System.out.println("bindingResult.getFieldErrors() = " + bindingResult.getFieldErrors().get(0).getDefaultMessage());
        Map<String, String> validationErrors= bindingResult.getFieldErrors()
                 .stream()
                 .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage()));
        apiError.setValidationErrors(validationErrors);
        return apiError;
    }

}
