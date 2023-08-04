package com.saga.ecommerce.core.services.stock;

import com.saga.ecommerce.core.services.stock.models.DecreaseProductStock;
import com.saga.ecommerce.core.services.stock.models.IncreaseProductStock;

public interface StockProductService {
    
    boolean increaseStockAmount(IncreaseProductStock params);

    boolean decreaseStockAmount(DecreaseProductStock params);
}
