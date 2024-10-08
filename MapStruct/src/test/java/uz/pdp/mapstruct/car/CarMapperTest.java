package uz.pdp.mapstruct.car;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static uz.pdp.mapstruct.car.CarMapper.carMapper;

class CarMapperTest {

    @Test
    void dto() {
    }

    @Test
    void entity() {
        CarDto carDto = new CarDto("Malibu", "Black");
        Car car = carMapper.entity(carDto);
        System.out.println("car = " + car);
    }

    @Test
    void gatherDto() {

        CarDto carDto = new CarDto("Malibu", "Black");
        AboutDto aboutDto = new AboutDto(25000D, "Faster Car");
        MakerDto makerDto = new MakerDto("GM", "USA");
        Car car = carMapper.gatherDto(carDto, aboutDto, makerDto);
        System.out.println("car = " + car);

    }


}