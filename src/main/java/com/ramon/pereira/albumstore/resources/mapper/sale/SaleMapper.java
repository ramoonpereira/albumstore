package com.ramon.pereira.albumstore.resources.mapper.sale;

import com.ramon.pereira.albumstore.model.Sale;
import com.ramon.pereira.albumstore.model.SaleItem;
import com.ramon.pereira.albumstore.resources.v1.dtos.sale.SaleItemResponseDto;
import com.ramon.pereira.albumstore.resources.v1.dtos.sale.SaleRequestDto;
import com.ramon.pereira.albumstore.resources.v1.dtos.sale.SaleResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {

  @Autowired
  private SaleItemMapper saleItemMapper;

  public Optional<List<SaleResponseDto>> serializeListToDto(@NonNull final Optional<List<Sale>> sales) {

    final var serializers = new ArrayList<SaleResponseDto>();

    sales.ifPresent(t -> t.forEach(sale -> {
      serializers.add(serializeToDto(Optional.of(sale)).get());
    }));

    return Optional.of(serializers);
  }

  public Optional<SaleResponseDto> serializeToDto(@NonNull final Optional<Sale> sale) {
    final var model = sale.get();

    final var items = saleItemMapper.serializeListToDto(Optional.of(model.getItems()));

    return Optional.of(SaleResponseDto.builder()
        .id(model.getId())
        .customerName(model.getCustomerName())
        .customerEmail(model.getCustomerEmail())
        .customerCpf(model.getCustomerCpf())
        .createdAt(model.getCreatedAt())
        .totalPrice(model.getTotalPrice())
        .cashBackTotalValue(model.getCashBackTotalValue())
        .items(items.get())
        .build());
  }

  public Optional<Sale> serializeToModel(@NonNull final Optional<SaleRequestDto> sale) {
    final var dto = sale.get();

    final var items = saleItemMapper.serializeListToModel(Optional.of(dto.getItems()));

    return Optional.of(Sale.builder()
        .customerName(dto.getCustomerName())
        .customerEmail(dto.getCustomerEmail())
        .customerCpf(dto.getCustomerCpf())
        .totalPrice(dto.getTotalPrice())
        .items(items.get())
        .build());
  }
}
