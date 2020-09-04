package com.lambdaschool.piggbank.controllers;

import com.lambdaschool.piggbank.models.Coin;
import com.lambdaschool.piggbank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController
{
    @Autowired
    private CoinRepository coinrepos;

    // http://localhost:2019/total
    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> totalCoins()
    {
        List<Coin> allCoinsList = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(allCoinsList::add);

        double totalValue = 0;
        for (Coin c : allCoinsList)
        {
            if(c.getQuantity() > 1)
            {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            }
            else
            {
                System.out.println(c.getQuantity() + " " + c.getName());
            }
            totalValue += c.getValue() * c.getQuantity();
        }

        System.out.println("The piggy bank holds " + totalValue);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}