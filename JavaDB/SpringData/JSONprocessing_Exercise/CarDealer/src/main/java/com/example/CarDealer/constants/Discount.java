package com.example.CarDealer.constants;

public enum Discount {

    ZERO(0f),
    FIVE (0.05f),
    TEN (0.10f),
    FIFTEEN (0.15f),
    TWENTY (0.2f),
    THIRTY (0.3f),
    FOURTY (0.4f),
    FIFTY (0.5f),
    YOUNG_DRIVER_DISCOUNT(0.05f);

    public float discount;

    private Discount(float discount) {
        this.discount = discount;
    }

    public float getDiscount() {
        return discount;
    }

}
