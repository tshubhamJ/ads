package com.auth0.ads;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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


    @GetMapping(value = "/ads")
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
