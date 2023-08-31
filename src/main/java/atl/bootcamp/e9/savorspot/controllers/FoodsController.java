package atl.bootcamp.e9.savorspot.controllers;

import atl.bootcamp.e9.savorspot.DTO.FoodDTO.FoodDTO;
import atl.bootcamp.e9.savorspot.security.JWTUtil;
import atl.bootcamp.e9.savorspot.security.ValidateToken;
import atl.bootcamp.e9.savorspot.service.food.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodsController {


    //injection service dependency
    private CreateFoodService createFoodService;
    private GetAllFoodService getAllFoodService;
    private GetFoodByIdService getFoodByIdService;
    private UpdateFoodService updateFoodService;
    private DeleteFoodService deleteFoodService;
    private final ValidateToken validateToken;

    //  -----------------------// -------------------------//

    public FoodsController(CreateFoodService createFoodService,
                           GetAllFoodService getAllFoodService,
                           GetFoodByIdService getFoodByIdService,
                           UpdateFoodService updateFoodService,
                           DeleteFoodService deleteFoodService,
                           JWTUtil jwtUtil) {

        this.createFoodService = createFoodService;
        this.getAllFoodService = getAllFoodService;
        this.getFoodByIdService = getFoodByIdService;
        this.updateFoodService = updateFoodService;
        this.deleteFoodService = deleteFoodService;
        validateToken = new ValidateToken(jwtUtil);
    }


    //build save Employee REST API
    @PostMapping
    public ResponseEntity<FoodDTO>createFood(@RequestHeader(value="Authorization") String token,
                                                                        @RequestBody FoodDTO foodDto) {

        if (!validateToken.isValidToken(token)) return null;

        Long foodStallID = validateToken.getUserID();

        return new ResponseEntity<>(createFoodService.createFood(foodDto, foodStallID), HttpStatus.CREATED);
    }

    //build get all Foods REST API
    @GetMapping
    //Tenemos que especificar que recibimos el token por el header
    public List<FoodDTO> getAllFood(@RequestHeader(value="Authorization") String token){

        //validamos que el token sea válido
        if (!validateToken.isValidToken(token)) return null;

        Long foodStallId = validateToken.getUserID();

        return getAllFoodService.getAllFoods(foodStallId);
    }
    //build getProductById REST API
    @GetMapping("{id}")
    public ResponseEntity<FoodDTO>getFoodById(@RequestHeader(value="Authorization") String token,
                                                                        @PathVariable("id") long id) {

        if (!validateToken.isValidToken(token)) return null;

        Long foodStallId = validateToken.getUserID();

        return new ResponseEntity<>(getFoodByIdService.getFoodById(id, foodStallId), HttpStatus.OK);
    }

    //build updateProductById REST API
    @PutMapping("{id}")
    public ResponseEntity<FoodDTO>updateFoodById(@RequestHeader(value="Authorization") String token,
                                                                            @PathVariable("id")long id,
                                                                            @RequestBody FoodDTO food){


        if (!validateToken.isValidToken(token)) return null;

        Long foodStallID = validateToken.getUserID();

        return new ResponseEntity<>(updateFoodService.updateFood(food,id,foodStallID), HttpStatus.OK);
    }
    //build deleteProduct REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteFood(@RequestHeader(value="Authorization") String token,
                                                                @PathVariable("id")long id) {

        if (!validateToken.isValidToken(token)) return null;

        Long foodStallID = validateToken.getUserID();

        deleteFoodService.deleteFood(id, foodStallID);

        return new ResponseEntity<>("Foods deleted successfully!.", HttpStatus.OK);
    }
}

