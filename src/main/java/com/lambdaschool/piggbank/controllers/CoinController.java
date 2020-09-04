package com.lambdaschool.piggbank.controllers;

import com.lambdaschool.piggbank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinController
{
    @Autowired
    private CoinRepository coinrepos;
}