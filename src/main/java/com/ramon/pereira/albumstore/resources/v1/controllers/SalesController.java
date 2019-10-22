package com.ramon.pereira.albumstore.resources.v1.controllers;

import com.ramon.pereira.albumstore.business.SalesBusiness;
import com.ramon.pereira.albumstore.resources.mapper.sale.SaleMapper;
import com.ramon.pereira.albumstore.resources.v1.dtos.sale.SaleRequestDto;
import com.ramon.pereira.albumstore.resources.v1.dtos.sale.SaleResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albumstore/v1/sales")
@Api(value = "Sales", description = "REST API Sales", tags = {"Sales"})
public class SalesController {

    @Autowired
    private SalesBusiness salesBusiness;

    @Autowired
    private SaleMapper saleMapper;


    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Get By ID", response = SaleResponseDto.class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Optional<SaleResponseDto>> findById(@PathVariable final Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(saleMapper.serializeToDto(salesBusiness.findById(id)));
    }


    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Filter Sales By Range Date", response = SaleResponseDto.class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Optional<List<SaleResponseDto>>> findByCreatedAtBetweenOrderByCreatedAtDesc(@RequestParam(name = "startDate", defaultValue = "1900-01-01")
                                                                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") final Date startDate,
                                                                                                      @RequestParam(name = "endDate", defaultValue = "1900-01-01")
                                                                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") final Date endDate,
                                                                                                      @RequestParam(value = "page", defaultValue = "0") final int page,
                                                                                                      @RequestParam(value = "pagesize", defaultValue = "10") final int pagesize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(saleMapper.serializeListToDto(salesBusiness.findByCreatedAtBetweenOrderByCreatedAtDesc(startDate, endDate, PageRequest.of(page, pagesize))));

    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Create Sale", response = SaleResponseDto.class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Optional<SaleResponseDto>> create(@RequestBody @Valid final SaleRequestDto saleRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
        .body(saleMapper.serializeToDto(salesBusiness.create(saleMapper.serializeToModel(Optional.of(saleRequestDto)).get())));
    }
}
