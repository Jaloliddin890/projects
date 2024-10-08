package uz.pdp.mapstruct.store;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapperForTwoClass {

    MapperForTwoClass mapperFor = Mappers.getMapper(MapperForTwoClass.class);

    Destination toDest(Store store);

    Store fromStore(Destination destination);

    List<Destination> toDestinations(List<Store> stores);
    List<Store> fromStores(List<Destination> destinations);



}
