package com.ramon.pereira.albumstore.business.impl;

import com.ramon.pereira.albumstore.business.SalesBusiness;
import com.ramon.pereira.albumstore.exception.IncorrectItemTotalValueException;
import com.ramon.pereira.albumstore.exception.IncorrectTotalValueException;
import com.ramon.pereira.albumstore.exception.SaleNotFoundException;
import com.ramon.pereira.albumstore.model.CashbackByGenreAndDay;
import com.ramon.pereira.albumstore.model.Sale;
import com.ramon.pereira.albumstore.model.SaleItem;
import com.ramon.pereira.albumstore.model.enDay;
import com.ramon.pereira.albumstore.model.enDiscGenre;
import com.ramon.pereira.albumstore.repository.CashbackByGenreAndDayRepository;
import com.ramon.pereira.albumstore.repository.SalesRepository;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SalesBusinessImpl implements SalesBusiness {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CashbackByGenreAndDayRepository cashbackByGenreAndDayRepository;

    @Override
    public Optional<List<Sale>> findByCreatedAtBetweenOrderByCreatedAtDesc(@NonNull final Date startDate,
                                                                           @NonNull final Date endDate,
                                                                           @NonNull final Pageable pageable) {

        return salesRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(startDate, endDate, pageable);

    }

    @Override
    public Optional<Sale> findById(@NonNull final Integer id) {
        return Optional.of(salesRepository.findById(id).orElseThrow(SaleNotFoundException::new));
    }

    @Override
    public Optional<Sale> create(@NonNull final Sale sale) {

        Optional.of(sale.getItems())
                .ifPresent(v -> v.forEach(e -> {
                    valideItemTotalValue(e.getQuantity(), e.getPrice(), e.getTotalPrice());
                    e.setCashBackValue(processCashBackValue(e));
                    e.setSale(sale);
                }));

        valideTotalValueSale(sale.getItems(), sale.getTotalPrice());
        sale.setCashBackTotalValue(calculeTotalCashbackFromSaleBySaleItems(sale.getItems()));

        return Optional.of(salesRepository.saveAndFlush(sale));
    }

    protected void valideItemTotalValue(@NonNull final Integer quantity, @NonNull final BigDecimal price,
                                        @NonNull final BigDecimal total) {

        if (price.multiply(new BigDecimal(quantity)).compareTo(total) != 0)
            throw new IncorrectItemTotalValueException();
    }

    protected void valideTotalValueSale(@NonNull final List<SaleItem> saleItems, @NonNull final BigDecimal totalValue) {
        if (totalValue.compareTo(saleItems.stream().map(SaleItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)) != 0) {
            throw new IncorrectTotalValueException();
        }
    }


    protected BigDecimal calculeTotalCashbackFromSaleBySaleItems(@NonNull final List<SaleItem> saleItems) {
        return saleItems.stream()
                .map(SaleItem::getCashBackValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected BigDecimal processCashBackValue(@NonNull final SaleItem saleItem) {
        return calculeTotalCashbackPerItem(saleItem.getTotalPrice(),
                getCashbackByGenreAndDay(saleItem.getGenre(), enDay.valueOf(ZonedDateTime.now().getDayOfWeek().toString())));
    }

    protected BigDecimal calculeTotalCashbackPerItem(@NonNull final BigDecimal totalItemPrice,
                                                     @NonNull final BigDecimal cashbackPercent) {

        return totalItemPrice.divide(new BigDecimal(100)).multiply(cashbackPercent);
    }


    protected BigDecimal getCashbackByGenreAndDay(@NonNull final enDiscGenre enDiscGenre, @NonNull final enDay enDay) {
        return cashbackByGenreAndDayRepository.findByGenreAndDay(enDiscGenre, enDay)
                .map(CashbackByGenreAndDay::getPercentCashBack)
                .orElse(BigDecimal.ZERO);
    }
}
