package com.woowacourse.matzip.presentation;

import com.woowacourse.matzip.application.RestaurantService;
import com.woowacourse.matzip.application.response.RestaurantResponse;
import com.woowacourse.matzip.application.response.RestaurantTitleResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(final RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/campuses/{campusId}/restaurants")
    public ResponseEntity<List<RestaurantTitleResponse>> showPage(@PathVariable final Long campusId,
                                                                  @RequestParam(required = false) final Long categoryId,
                                                                  final Pageable pageable) {
        return ResponseEntity.ok(restaurantService.findByCampusIdOrderByIdDesc(campusId, categoryId, pageable));
    }

    @GetMapping("/campuses/{campusId}/restaurants/random")
    public ResponseEntity<List<RestaurantTitleResponse>> showRandom(@PathVariable final Long campusId,
                                                                    @RequestParam final int size) {
        return ResponseEntity.ok(restaurantService.findRandomsByCampusId(campusId, size));
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<RestaurantResponse> showRestaurant(@PathVariable final Long restaurantId) {
        return ResponseEntity.ok(restaurantService.findById(restaurantId));
    }
}
