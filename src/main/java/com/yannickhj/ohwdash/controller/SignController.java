package com.yannickhj.ohwdash.controller;

import com.yannickhj.ohwdash.exception.ResourceNotFoundException;
import com.yannickhj.ohwdash.model.Sign;
import com.yannickhj.ohwdash.repository.SignRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SignController {

    @Autowired
    SignRepository signRepository;

    @GetMapping("/objecten")
    public List<Sign> getAllSigns() {
        return signRepository.findAll();
    }

    @PostMapping("/objecten")
    public Sign createSign(@Valid @RequestBody Sign sign) {
        return signRepository.save(sign);
    }

    @GetMapping("/objecten/{id}")
    public Sign getSignById(@PathVariable(value = "id") Long signId) {
        return signRepository.findById(signId)
                .orElseThrow(() -> new ResourceNotFoundException("Sign", "id", signId));
    }

    @PutMapping("/objecten/{id}")
    public Sign updateSign(@PathVariable(value = "id") Long signId,
                                           @Valid @RequestBody Sign signDetails) {

        Sign sign = signRepository.findById(signId)
                .orElseThrow(() -> new ResourceNotFoundException("Sign", "id", signId));

        sign.setLocatienummer(signDetails.getLocatienummer());
        sign.setSoort(signDetails.getSoort());
        sign.setGrootte(signDetails.getGrootte());
        sign.setBiestekst(signDetails.getBiestekst());
        sign.setPlaats(signDetails.getPlaats());
        sign.setGemeente(signDetails.getGemeente());
        sign.setStraatnaam(signDetails.getStraatnaam());
        sign.setRoute(signDetails.getRoute());
        sign.setXcord(signDetails.getXcord());
        sign.setYcord(signDetails.getYcord());
        sign.setOnderhoud(signDetails.getOnderhoud());
        sign.setControleur(signDetails.getControleur());
        sign.setActies(signDetails.getActies());

        Sign updatedSign = signRepository.save(sign);
        return updatedSign;
    }

    @DeleteMapping("/objecten/{id}")
    public ResponseEntity<?> deleteSign(@PathVariable(value = "id") Long signId) {
        Sign sign = signRepository.findById(signId)
                .orElseThrow(() -> new ResourceNotFoundException("Sign", "id", signId));

        signRepository.delete(sign);

        return ResponseEntity.ok().build();
    }
}
