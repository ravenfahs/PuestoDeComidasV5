package atl.bootcamp.e9.savorspot.controllers;

import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.MessageDto;
import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.RegisterUserFoodStallDto;
import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.UserFoodStallDto;
import atl.bootcamp.e9.savorspot.service.foodStall.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stalls")
public class FoodStallController {

    private final RegisterFoodStallService registerFoodStallService;
    private final ListAllFoodStallService listAllFoodStallService;
    private final FindUserFoodStallByIdService findUserFoodStallByIdService;
    private final FindFoodStallByIdService findFoodStallByIdService;
    private final UpdateFoodStallService updateFoodStallService;
    private final DeleteFoodStallByIdService deleteFoodStallByIdService;

    public FoodStallController(
            RegisterFoodStallService registerFoodStallService,
            ListAllFoodStallService listAllFoodStallService,
            FindUserFoodStallByIdService findUserFoodStallByIdService,
            FindFoodStallByIdService findFoodStallByIdService,
            UpdateFoodStallService updateFoodStallService,
            DeleteFoodStallByIdService deleteFoodStallByIdService) {
        this.registerFoodStallService = registerFoodStallService;
        this.listAllFoodStallService = listAllFoodStallService;
        this.findUserFoodStallByIdService = findUserFoodStallByIdService;
        this.findFoodStallByIdService = findFoodStallByIdService;
        this.updateFoodStallService = updateFoodStallService;
        this.deleteFoodStallByIdService = deleteFoodStallByIdService;
    }

    @GetMapping
    public ResponseEntity<?> list(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(listAllFoodStallService.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findFoodStallById(@PathVariable Long id) {
        return ResponseEntity.ok(findFoodStallByIdService.findWith(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserFoodStallById(@PathVariable Long id) {
        return ResponseEntity.ok(findUserFoodStallByIdService.findWith(id));
    }


    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterUserFoodStallDto registerUserFoodStallDto) {
        registerFoodStallService.register(registerUserFoodStallDto);
        return ResponseEntity.ok(new MessageDto("Food stall user registered successfully"));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserFoodStallDto userFoodStallDto) {
        updateFoodStallService.update(userFoodStallDto);
        return ResponseEntity.ok(new MessageDto("Food stall user updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        deleteFoodStallByIdService.deleteWith(id);
        return ResponseEntity.ok(new MessageDto("Food stall user deleted successfully"));
    }

}
