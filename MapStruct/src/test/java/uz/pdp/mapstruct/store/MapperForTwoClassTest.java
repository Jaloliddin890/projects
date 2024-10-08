package uz.pdp.mapstruct.store;

import org.junit.jupiter.api.Test;


import java.util.List;

import static uz.pdp.mapstruct.store.MapperForTwoClass.mapperFor;

class MapperForTwoClassTest {

    @Test
    void toDest() {
        Store store = new Store(1, "Store 1", "About that -> First Store ");
        Destination destination = mapperFor.toDest(store);
        System.out.println("destination = " + destination);
    }

    @Test
    void fromStore(){
        Destination destination = new Destination(2, "Destination 1", "About that -> First Destination");
        Store store = mapperFor.fromStore(destination);
        System.out.println("store = " + store);
    }

    @Test
    void toDestinations(){
        List<Store> stores = List.of(
                new Store(1, "Store 1", "About that -> First Store "),
                new Store(2, "Store 2","About that -> Second Store"),
                new Store(3, "Store 3","About that -> Third Store")
        );
        List<Destination> destinations = mapperFor.toDestinations(stores);
        destinations.forEach(System.out::println);
    }

    @Test
    void fromStores(){
        List<Destination> destination = List.of(
                new Destination(1, "Destination 1", "About that -> First Destination "),
                new Destination(2, "Destination 2","About that -> Second Destination"),
                new Destination(3, "Destination 3","About that -> Third Destination")
        );
        List<Store> stores = mapperFor.fromStores(destination);
        stores.forEach(System.out::println);
    }
}