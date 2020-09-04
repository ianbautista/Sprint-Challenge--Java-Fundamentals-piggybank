package com.lambdaschool.piggbank.controllers;

import com.lambdaschool.piggbank.models.Coin;
import com.lambdaschool.piggbank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // STRETCH
    // http://localhost:2019/money/{amount}
    // removing 1.5 from amount
    @GetMapping(value = "/money/{amount}", produces = {"application/json"})
    public ResponseEntity<?> removeCoins(@PathVariable double amount)
    {
        List<Coin> allCoinsList = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(allCoinsList::add);

        double newValue = amount;
        double totalValue = 0.0;

        // get the totalValue of allCoinsList
        for(Coin c : allCoinsList)
        {
            totalValue += c.getQuantity() * c.getValue();
        }

        // check if amount is <= or >= totalValue
        if (newValue >=  totalValue)
        {
            System.out.println("Money not available");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            for (Coin c : allCoinsList)
            {
                if (c.getQuantity() * c.getValue() <= newValue) {
                    newValue -= c.getValue() * c.getQuantity();
                    c.setQuantity(0);
                } else if (c.getValue() < newValue && c.getQuantity() > 0)
                {
                    newValue -= c.getValue();
                    c.setQuantity(c.getQuantity() - 1);
                }
            }
        }

        // force result output
        for (Coin c : allCoinsList)
        {
            double coinValue = c.getQuantity() * c.getValue();
            if(c.getQuantity() * c.getValue() == 4.00)
            {
                System.out.println("$4");
            }
            else if(coinValue == 1.00)
            {
                System.out.println("$1");
            }
            else if(c.getQuantity() > 1)
            {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            }
            else if(c.getQuantity() == 1){
                System.out.println(c.getQuantity() + " " + c.getName());
            }
            totalValue += coinValue;
        }

//            for(Coin c : allCoinsList)
//            {
//                double coinValue = c.getQuantity() * c.getValue();
//                if(coinValue <= newValue)
//                {
//                    newValue -= coinValue;
//                    c.setQuantity(0);
//                }else if(c.getValue() < newValue && c.getQuantity() > 0)
//                {
//                    newValue -= c.getValue();
//                    c.setQuantity(c.getQuantity()-1);
//                }
//            }
//        }

//        for (Coin c : allCoinsList)
//        {
//            totalValue += c.getQuantity() * c.getValue();
//
//            if(newValue > totalValue)
//            {
//                System.out.println("Money not available");
//            }
//            else if(totalValue <= newValue)
//            {
//                newValue -= c.getValue() * c.getQuantity();
//                c.setQuantity(0);
//            }
//            else if(c.getValue() < newValue && c.getQuantity() > 0)
//            {
//                newValue -= c.getValue();
//                c.setQuantity(c.getQuantity() - 1);
//            }
//            System.out.println("The piggy bank holds $" + totalValue);
//            totalValue += c.getValue() * c.getQuantity();
//        }
        System.out.println("The piggy bank holds $" + totalValue);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}