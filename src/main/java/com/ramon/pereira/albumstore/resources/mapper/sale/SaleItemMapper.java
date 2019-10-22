package com.ramon.pereira.albumstore.resources.mapper.sale;

import com.ramon.pereira.albumstore.model.SaleItem;
import com.ramon.pereira.albumstore.resources.v1.dtos.sale.SaleItemRequestDto;
import com.ramon.pereira.albumstore.resources.v1.dtos.sale.SaleItemResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SaleItemMapper {
  public Optional<List<SaleItemResponseDto>> serializeListToDto(@NonNull final Optional<List<SaleItem>> saleItems) {

    final var serializers = new ArrayList<SaleItemResponseDto>();

    saleItems.ifPresent(t -> t.forEach(sale -> {
      serializers.add(serializeToDto(Optional.of(sale)).get());
    }));

    return Optional.of(serializers);
  }

  public Optional<SaleItemResponseDto> serializeToDto(@NonNull final Optional<SaleItem> saleItem) {
    final SaleItem model = saleItem.get();
    return Optional.of(SaleItemResponseDto.builder()
        .discId(model.getDiscId())
        .id(model.getId())
        .genre(model.getGenre())
        .cashBackValue(model.getCashBackValue())
        .name(model.getName())
        .price(model.getPrice())
        .quantity(model.getQuantity())
        .totalPrice(model.getTotalPrice())
        .build());
  }

  public Optional<List<SaleItem>> serializeListToModel(@NonNull final Optional<List<SaleItemRequestDto>> saleItems) {

    final var serializers = new ArrayList<SaleItem>();

    saleItems.ifPresent(t -> t.forEach(saleItem -> {
      serializers.add(serializeToModel(Optional.of(saleItem)).get());
    }));

    return Optional.of(serializers);
  }

  public Optional<SaleItem> serializeToModel(@NonNull final Optional<SaleItemRequestDto> saleItem) {
    final SaleItemRequestDto model = saleItem.get();
    return Optional.of(SaleItem.builder()
        .discId(model.getDiscId())
        .genre(model.getGenre())
        .name(model.getName())
        .price(model.getPrice())
        .quantity(model.getQuantity())
        .totalPrice(model.getTotalPrice())
        .build());
  }
}
