package com.foliageh.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.foliageh.repository.FlatRepository;
import com.foliageh.repository.entity.*;

import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final FlatRepository flatRepository;

    @Override
    @Transactional
    public void run(String... args) {
        // Очистка таблицы перед инициализацией
        flatRepository.deleteAll();

        // Создание и сохранение тестовых квартир
        List<Flat> flats = java.util.Arrays.asList(
                buildFlat(
                        "Luxury Apartment",
                        new Coordinates(10, -100.0f),
                        new House("Elite Tower", 2020, 25, 4),
                        100, 3, 75.5, 15_000_000, true,
                        Furnish.DESIGNER, Transport.ENOUGH
                ),
                buildFlat(
                        "Comfort Flat",
                        new Coordinates(20, 200.5f),
                        new House("Comfort House", 2015, 9, 2),
                        65, 2, 45.0, 8_000_000, false,
                        Furnish.FINE, Transport.LITTLE
                ),
                buildFlat(
                        "Studio Downtown",
                        new Coordinates(30, -500.0f),
                        new House("Sky Residence", 2022, 30, 6),
                        45, 1, 35.2, 5_000_000, true,
                        Furnish.LITTLE, Transport.FEW
                ),
                buildFlat(
                        "Family Apartment",
                        new Coordinates(40, 300.2f),
                        new House("City View", 2018, 12, 3),
                        120, 4, 90.0, 12_000_000, true,
                        Furnish.FINE, Transport.ENOUGH
                )
        );

        flatRepository.saveAll(flats);
        System.out.println("=== 4 ТЕСТОВЫЕ КВАРТИРЫ СОХРАНЕНЫ В БАЗУ ===");
    }

    private Flat buildFlat(
            String name,
            Coordinates coords,
            House house,
            int area,
            int rooms,
            double livingSpace,
            int price,
            boolean hasBalcony,
            Furnish furnish,
            Transport transport
    ) {
        return Flat.builder()
                .name(name)
                .coordinates(coords)
                .creationDate(LocalDate.now())
                .area(area)
                .numberOfRooms(rooms)
                .livingSpace(livingSpace)
                .price(price)
                .hasBalcony(hasBalcony)
                .furnish(furnish)
                .transport(transport)
                .house(house)
                .build();
    }
}
