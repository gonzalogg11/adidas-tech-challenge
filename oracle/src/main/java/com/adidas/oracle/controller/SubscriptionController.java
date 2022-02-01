package com.adidas.oracle.controller;

import com.adidas.oracle.dto.ApiResponseDTO;
import com.adidas.oracle.dto.CreateSubscriptionDTO;
import com.adidas.oracle.dto.SubscriptionDTO;
import com.adidas.oracle.exception.UserNotAuthorizedException;
import com.adidas.oracle.service.AuthService;
import com.adidas.oracle.service.SubscriptionService;
import com.adidas.oracle.util.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Api(tags = "Subscription")
@RestController
public class SubscriptionController {

    private final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Create subscription", notes = "Creates subscription to newsletter campaign")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns id of subscription created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Returns ApiResponse with details about bad request", response = ApiResponseDTO.class),
            @ApiResponse(code = 401, message = "User not authorized to use the API", response = ApiResponseDTO.class)
    })
    @PostMapping(value = "/v0/subscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO> createSubscription(@RequestBody @Valid CreateSubscriptionDTO createSubscriptionDTO,
                                                             @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authToken) {
        try {
            authService.auth(authToken);
            Long subscriptionId = subscriptionService.saveSubscription(createSubscriptionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO(HttpStatus.CREATED, subscriptionId));
        } catch (UserNotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDTO(HttpStatus.UNAUTHORIZED,
                    e.getMessage()));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(JSONUtil.loadJson(e.getResponseBodyAsString(),
                    ApiResponseDTO.class));
        } catch (Exception e) {
            log.error("Error while creating subscription -> {}", e.getLocalizedMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()));
        }
    }

    @ApiOperation(value = "Get subscriptions", notes = "Gets single or multiple subscriptions of newsletter campaign by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns single or multiple subscriptions", response = ApiResponseDTO.class),
            @ApiResponse(code = 204, message = "No content for request", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Returns ApiResponse with details about bad request", response = ApiResponseDTO.class),
            @ApiResponse(code = 401, message = "User not authorized to use the API", response = ApiResponseDTO.class)
    })
    @GetMapping(value = "/v0/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO> getSubscriptions(@RequestParam(value = "ids", required = false) List<Long> subscriptionIds,
                                                           @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authToken) {
        try {
            authService.auth(authToken);
            List<SubscriptionDTO> subscriptions = subscriptionService.getSubscriptions(subscriptionIds);
            if (CollectionUtils.isEmpty(subscriptions)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseDTO(HttpStatus.NO_CONTENT,
                        subscriptions));
            }
            return ResponseEntity.ok(new ApiResponseDTO(HttpStatus.OK, subscriptions));
        } catch (UserNotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDTO(HttpStatus.UNAUTHORIZED,
                    e.getMessage()));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(JSONUtil.loadJson(e.getResponseBodyAsString(),
                    ApiResponseDTO.class));
        } catch (Exception e) {
            log.error("Error while creating subscription -> {}", e.getLocalizedMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()));
        }
    }

    @ApiOperation(value = "Delete subscription", notes = "Deletes subscription of newsletter campaign by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subscription deleted successfully", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Returns ApiResponse with details about bad request", response = ApiResponseDTO.class),
            @ApiResponse(code = 401, message = "User not authorized to use the API", response = ApiResponseDTO.class)
    })
    @DeleteMapping(value = "/v0/subscription/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO> cancelSubscription(@PathVariable(value = "id") Long subscriptionId,
                                                             @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authToken) {
        try {
            authService.auth(authToken);
            subscriptionService.cancelSubscription(subscriptionId);
            return ResponseEntity.ok().body(new ApiResponseDTO(HttpStatus.OK));
        } catch (UserNotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDTO(HttpStatus.UNAUTHORIZED,
                    e.getMessage()));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(JSONUtil.loadJson(e.getResponseBodyAsString(),
                    ApiResponseDTO.class));
        } catch (Exception e) {
            log.error("Error while canceling subscription -> {}", e.getLocalizedMessage(), e);
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
