package com.myhangars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderException {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class UnableModifyQuantityProducts_Hangar extends RuntimeException {

        public UnableModifyQuantityProducts_Hangar(long hangarId, long productId) {
            super(String.format("Unable to modifiy the quantity con product { id = %d } in hangar { id = %d", productId, hangarId));
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class NotEnoughtStock extends RuntimeException {

        public NotEnoughtStock(long demandQuantity, long stockQuantity) {
            super(String.format("There is not enough stock { demand = %d; stock = %d }", demandQuantity, stockQuantity));
        }
    }
}
