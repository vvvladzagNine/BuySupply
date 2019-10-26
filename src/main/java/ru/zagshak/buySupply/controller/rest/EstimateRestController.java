package ru.zagshak.buySupply.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.domain.to.estimateTO.EstimateTO;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.util.EstimateUtil;

import java.util.List;

import static ru.zagshak.buySupply.util.ValidationUtil.assureIdConsistent;
import static ru.zagshak.buySupply.util.ValidationUtil.checkNew;

@RestController
public class EstimateRestController {

    @Autowired
    private EstimateService estimateService;

    /*
    curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/100001/estimates
     */
    @GetMapping("/rest/{userId}/estimates")
    private List<EstimateTO> getAllForEstimated(@PathVariable int userId) {
        return EstimateUtil.makeTO(estimateService.getAllForEstimated(userId));
    }

    /*
    curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/estimates/100010
     */
    @GetMapping("/rest/estimates/{id}")
    private EstimateTO get(@PathVariable int id) {
        return EstimateUtil.makeTO(estimateService.get(id));
    }

    /*
    curl --user userS@yandex.ru:passwordS -X DELETE http://localhost:8080/rest/estimates/100011
    curl --user u:p -X DELETE http://localhost:8080/rest/estimates/100013
     */
    @DeleteMapping("/rest/estimates/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @AuthenticationPrincipal User user) {
        estimateService.delete(id, user.getId());
    }

    /*
    curl --user u:p --data '{"id":100010,"stars":5,"comment":"Updated comment, coz i was wrong","estimatedId":100001,"estimatorId":100000}' -H "Content-Type: application/json" -X PUT http://localhost:8080/rest/estimates/100010
    */
    @PutMapping(value = "rest/estimates/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(
            @RequestBody EstimateTO estimateTO,
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ) {
        Estimate es = new Estimate(estimateTO);
        assureIdConsistent(es, id);

        estimateService.update(es, estimateTO.getEstimatedId(), estimateTO.getEstimatorId());
    }

    /*
    curl --user u:p --data '{"id":null,"stars":9,"comment":"Good shop, definitely recommend!","estimatedId":null,"estimatorId":null}' -H "Content-Type: application/json" -X POST http://localhost:8080/rest/100001/estimates
    */
    @PostMapping(value = "rest/{userId}/estimates",consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstimateTO save(
            @RequestBody EstimateTO estimateTO,
            @PathVariable int userId,
            @AuthenticationPrincipal User user
    ) {
        Estimate es = new Estimate(estimateTO);
        checkNew(es);
        Estimate created = estimateService.create(es,userId,user.getId());

        return EstimateUtil.makeTO(created);
    }


}
