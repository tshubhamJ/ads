package com.auth0.ads;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/v1")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @GetMapping(value = "/ad/{adId}")
    @Transactional
    public @ResponseBody Ad getAd(@PathVariable Long adId) {
        Optional<Ad> opObj = adRepository.findById(adId);
        if (opObj.isPresent())
            return opObj.get();
        else {
            return new Ad();
        }

    }


    @GetMapping(value = "/ad")
    @Transactional
    public @ResponseBody List<Ad> getAds() {

        Iterable opObj = adRepository.findAll();

        List<Ad> list = new ArrayList<>();

        Iterator<Ad> ite = opObj.iterator();
        while (ite.hasNext()) {
            list.add(ite.next());
            }

        return list;
    }

    @DeleteMapping(value = "/ad/{id}",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @Transactional
    public ResponseEntity<String> deleteArticle(@PathVariable("id") Long id) {
        Optional<Ad> opObj = adRepository.findById(id);
        if (opObj.isPresent()) {
            adRepository.deleteById(id);
            String responseMessage = "Your id" + " " + id + " is succesfully Deleted with statuscode";
            return new ResponseEntity<String>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "Invalid id";
            return new ResponseEntity<String>(responseMessage,HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/ad/{id}")
    @Transactional
    public @ResponseBody ResponseEntity updateAd(@PathVariable("id") Long id, @RequestBody Ad ad)
    {
        Optional<Ad> adObj = adRepository.findById(id);
        if (adObj.isPresent()) {
            ad.setId(id);

            System.out.println("updated data at location id"+"="+ id);
            adRepository.save(ad);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

/*    @PutMapping(value = "/ad/{id}")
    @Transactional
    public @ResponseBody ResponseEntity updateAd(@PathVariable("id") Long id, @RequestParam String owner)
    {
        Optional<Ad> adObj = adRepository.findById(id);
        if (adObj.isPresent()) {
            Ad adData = adObj.get();
            adData.setOwner(owner);
            adRepository.save(adData);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }*/


    @PostMapping(value = "/ad")
    @Transactional
    public @ResponseBody Ad postAd(@RequestBody Ad ad) {
        System.out.println(ad.description);
        System.out.println(ad.owner);
//
//        Ad ad = new Ad();
//        ad.owner = "Vikash";
//        ad.price = new BigDecimal(10);
//        ad.title = "Learn Spring - 2";
        adRepository.save(ad);
//        return ad;
         return ad;
    }



}
