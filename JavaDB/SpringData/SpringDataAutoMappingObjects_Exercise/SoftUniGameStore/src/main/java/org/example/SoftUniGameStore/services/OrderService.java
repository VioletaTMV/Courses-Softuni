package org.example.SoftUniGameStore.services;

public interface OrderService {
    String addItemToShoppingCart(String[] inputArr);

    String removeItemFromShoppingCart(String[] inputArr);

    String buyItemsInShoppingCart();

}
