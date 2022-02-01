package com.adidas.notification.controller;

import com.adidas.notification.dto.ApiResponseDTO;
import com.adidas.notification.dto.EmailRequestDTO;
import com.adidas.notification.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@Api(tags = "Email")
@RestController
public class EmailController {

    private final Logger log = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "Send email", notes = "Sends email notification by custom request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request to send email succeeded.", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Returns ApiResponse with details about bad request", response = ApiResponseDTO.class)
    })
    @PostMapping(value = "/v0/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmail(@RequestBody @Valid EmailRequestDTO emailRequestDTO) {
        try {
            emailService.sendEmail(emailRequestDTO);
            return ResponseEntity.ok().body(new ApiResponseDTO(HttpStatus.OK));
        } catch (Exception e) {
            log.error("Error while creating subscription -> {}", e.getLocalizedMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()));
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO> handleValidationException(MethodArgumentNotValidException e) {
        log.error("Error handling validation -> {}", e.getLocalizedMessage(), e);
        return ResponseEntity.badRequest().body(new ApiResponseDTO(HttpStatus.BAD_REQUEST,
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage()));
    }

}
